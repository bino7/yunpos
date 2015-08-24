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

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.payment.alipay.config.AlipayConfig;
import com.yunpos.payment.alipay.model.AlipayQueryReqData;
import com.yunpos.payment.alipay.model.AlipayRefundReqData;
import com.yunpos.payment.alipay.model.AlipayScanPayReqData;
import com.yunpos.payment.alipay.util.AlipayNotify;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.AlipayService;
import com.yunpos.service.payment.PayParam;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;

/**
 * 
 * 功能描述：支付宝异步回调Controller
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
@RequestMapping("/pay/alipay")
public class AlipayController {
	Logger log = LoggerFactory.getLogger(AlipayController.class);

	@Autowired
	private SysPayOrderService sysPayOrderService;

	@Autowired
	private AlipayService alipayService;

	/**
	 * 支付宝订单生成接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public Object alipayCreate(HttpServletRequest request, HttpServletResponse response) {
		// PayParam param = buildPayParam(request);
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String dynamic_id = request.getParameter("dynamic_id"); // 支付码（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）

		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee) || Strings.isNullOrEmpty(dynamic_id)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
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
			AlipayScanPayReqData payReqData = new AlipayScanPayReqData(orderNo, AlipayConfig.partner, "支付宝条码支付",
					"BARCODE_PAY_OFFLINE", total_fee, dynamic_id);
			payReqData.setPay_channel(pay_channel);
			payReqData.setTerminal_unique_no(terminal_unique_no);
			payReqData.setMerchant_num(merchant_num);
			
			payMsg = alipayService.pay(payReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	/**
	 * 支付宝支付查询接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response) {
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		if (Strings.isNullOrEmpty("pay_channel")||Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(trace_num)
				|| Strings.isNullOrEmpty("terminal_unique_no")) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
		try {
			// 商户信息从数据库配置中获取
			// -------------------------------
			AlipayQueryReqData alipayQueryReqData = new AlipayQueryReqData(trace_num, AlipayConfig.partner);
			alipayQueryReqData.setPay_channel(pay_channel);
			alipayQueryReqData.setTerminal_unique_no(terminal_unique_no);
			alipayQueryReqData.setMerchant_num(merchant_num);
			
			payMsg = alipayService.query(alipayQueryReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	@RequestMapping(value = "/refund")
	@ResponseBody
	public Object refund(HttpServletRequest request, HttpServletResponse response) {
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String refund_amount = request.getParameter("refund_amount");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(merchant_num)
				|| Strings.isNullOrEmpty(trace_num) || Strings.isNullOrEmpty(refund_amount)
				|| Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
		try {
			AlipayRefundReqData alipayRefundReqData = new AlipayRefundReqData(AlipayConfig.partner, trace_num,
					refund_amount);
			alipayRefundReqData.setPay_channel(pay_channel);
			alipayRefundReqData.setTerminal_unique_no(terminal_unique_no);
			alipayRefundReqData.setMerchant_num(merchant_num);
			
			payMsg = alipayService.refund(alipayRefundReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	/**
	 * 支付宝异步通知
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notify")
	public void asynNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("收到支付宝支付异步通知");
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
			log.info("支付宝异步通知参数：", params);
			// 商户网站唯一订单号
			String out_trade_no = request.getParameter("out_trade_no");
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			if (AlipayNotify.verify(params)) {// 验证成功
				if (!Objects.equal("TRADE_CLOSED", trade_status)) {
					alipayService.notifyPayment(params, true, "");
				} else {
					alipayService.notifyPayment(params, false, "TRADE_CLOSED");
				}
				writer.write("success");
				writer.flush();
			} else {// 验证失败
				log.info("支付宝异步通知请求验证失败...");
				writer.write("fail");
				writer.flush();
			}
		} catch (IOException e) {
			log.error("处理支付宝异步通知异常", e);
		}
	}

	private PayParam buildPayParam(HttpServletRequest request) {
		PayParam param = new PayParam();
		param.setBarCode(request.getParameter("barCode"));
		param.setChannel(request.getParameter("channel"));
		param.setDeviceType(request.getParameter("deviceType"));
		param.setImei(request.getParameter("imei"));
		param.setPrice(request.getParameter("price"));
		param.setRoleId(request.getParameter("roleId"));
		param.setUserId(request.getParameter("userId"));
		log.info("支付传递参数：" + param.toString());
		return param;
	}

}
