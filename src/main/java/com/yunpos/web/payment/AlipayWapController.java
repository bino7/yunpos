package com.yunpos.web.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.payment.alipay.config.AlipayConfig;
import com.yunpos.payment.alipay.model.AlipayWapPayReqData;
import com.yunpos.payment.alipay.util.AlipayNotify;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.AlipayWapService;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;

/**
 * 
 * 功能描述：支付宝手机wap支付控制类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
@Controller
@RequestMapping("/pay/alipay/wap")
public class AlipayWapController {
	Logger log = LoggerFactory.getLogger(AlipayWapController.class);

	@Autowired
	private SysPayOrderService sysPayOrderService;

	@Autowired
	private AlipayWapService alipayWapService;

	/**
	 * 支付宝订单生成接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public Object alipayWapCreate(HttpServletRequest request, HttpServletResponse response) {
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）

		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
		String htmlStr = "";
		try {

			SysPayOrder payOrder = new SysPayOrder();
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";
			System.out.println("支付宝生成订单号：" + orderNo);
			// payOrder.setPayOrderNo(orderNo + "");
			// payOrder.setStatus(Byte.parseByte("2"));// 支付中
			// payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
			// payOrder.setUserId(Integer.getInteger(param.getUserId()));
			// payOrder.setPrice(1l);
			// payOrder.setBarCode(dynamic_id);
			// payOrder.setImei(terminal_unique_no);
			// payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
			// payOrder.setCreateAt(new Date());
			// payOrder.setCreateBy(Integer.getInteger(param.getUserId()));
			// 保存支付流水
			// sysPayOrderService.save(payOrder);
			AlipayWapPayReqData payReqData = new AlipayWapPayReqData(orderNo, AlipayConfig.partner, "支付宝手机wap测试", total_fee, AlipayConfig.partner);
			payReqData.setPay_channel(pay_channel);
			payReqData.setTerminal_unique_no(terminal_unique_no);
			payReqData.setMerchant_num(merchant_num);
			
			htmlStr = alipayWapService.pay(payReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			//return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return htmlStr;
	}
	
	
	/**
	 * 支付宝手机wap支付同步通知
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/synNotify")
	@ResponseBody
	public Object synNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("收到支付宝手机支付wap支付同步通知!");
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			log.info("支付宝同步通知参数：", params);
			String trade_status = request.getParameter("trade_status");
			if (AlipayNotify.verify(params)) {// 验证成功
				if(!request.getParameter("is_success").equals("T")){
					return new Message(ResultCode.FAIL.name(), "FAIL","接口调用失败" , null);
				}
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					//message = alipayWapService.synWapPayment(params, true, "");
					return new Message(ResultCode.SUCCESS.name(), "SUCCESS","支付成功！" , null);
				} else {
					return new Message(ResultCode.FAIL.name(), trade_status,"支付失败" , null);
				}
			} else {// 验证失败
				log.info("支付宝手机wap同步通知请求验证失败...");
				return new Message(ResultCode.FAIL.name(), "", "支付宝手机wap同步通知请求验证失败", null);
			}
		} catch(Exception e) {
			log.error("支付宝手机wap同步通知异常", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "系统异常", null);
		}
	}


	/**
	 * 支付宝异步通知
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/asynNotify")
	public void asynNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("收到支付宝wap支付异步通知");
		try {
			PrintWriter writer = response.getWriter();
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			log.info("收到支付宝wap支付异步通知参数：", params);
			String trade_status = request.getParameter("trade_status");
			if (AlipayNotify.verify(params)) {// 验证成功
				if (trade_status.equals("TRADE_SUCCESS")) {
					alipayWapService.asyWapPayment(params, true, "");
				} else {
					alipayWapService.asyWapPayment(params, false,trade_status);
				}
				writer.write("success");
				writer.flush();
			} else {// 验证失败
				log.info("支付宝wap支付异步通知验证失败...");
				writer.write("fail");
				writer.flush();
			}
		} catch (IOException e) {
			log.error("处理支付宝wap支付异常", e);
		}
	}
	
	
	
	

	
}
