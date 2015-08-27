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
import com.yunpos.payment.alipay.util.AlipayNotify;
import com.yunpos.payment.wxpay.protocol.close_protocol.CloseOrderReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanCodePayReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundReqData;
import com.yunpos.payment.wxpay.protocol.refund_query_protocol.RefundQueryReqData;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.PayParam;
import com.yunpos.service.payment.WechatPayService;
import com.yunpos.utils.AmountUtils;
import com.yunpos.utils.DateUtil;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;

/**
 * 
 * 功能描述：微信异步回调Controller
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
@RequestMapping("/pay/wechatpay/scan")
public class WchatScanCodepPayController {
	Logger log = LoggerFactory.getLogger(WchatScanCodepPayController.class);

	@Autowired
	private WechatPayService wechatPayService;
	@Autowired
	private SysPayOrderService sysPayOrderService;

	/**
	 * 微信支付订单生成接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public Object wechatCreate(HttpServletRequest request, HttpServletResponse response) {
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
		try {
			SysPayOrder payOrder = new SysPayOrder();
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId()+"";
			System.out.println("微信生成订单号：" + orderNo);
			// String price =
			// AmountUtils.changeY2F(request.getParameter("price"));
			// payOrder.setPayOrderNo(orderNo + "");
			// payOrder.setStatus(Byte.parseByte("2"));// 支付中
			// payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
			// payOrder.setUserId(Integer.getInteger(param.getUserId()));
			// payOrder.setPrice(Long.valueOf(price)); // 微信支付单位分
			// payOrder.setBarCode(param.getBarCode());
			// payOrder.setImei(param.getImei());
			// payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
			// payOrder.setCreateAt(new Date());
			// payOrder.setCreateBy(Integer.getInteger(param.getUserId()));
			//
			// // 保存支付流水
			// sysPayOrderService.save(payOrder);

			// 调用支付接口发起支付请求
			int totalFee = Integer.valueOf(AmountUtils.changeY2F(total_fee));
			String goodsTag = "微信扫码支付";
			String body = "微信扫码支付测试";
			String attach = "附加数据";
			// 支付请求
			ScanCodePayReqData scanCodePayReqData = new ScanCodePayReqData(body, orderNo, totalFee, terminal_unique_no, "192.168.0.116", goodsTag, attach);
			payMsg = wechatPayService.unifiedOrder(scanCodePayReqData);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(),"支付出现异常！", null);
		}
		return payMsg;
	}
	
	/**
	 * 关闭订单：
	 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	        注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/closeOrder")
	@ResponseBody
	public Object closeOrder(HttpServletRequest request, HttpServletResponse response) {
		String merchant_num = request.getParameter("merchant_num"); 
		String out_trade_no = request.getParameter("out_trade_no");
		String terminal_unique_no = request.getParameter("terminal_unique_no"); 

		if (Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(out_trade_no)
				|| Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
		try {
		
			// 支付请求
			CloseOrderReqData closeOrderReqData = new CloseOrderReqData(merchant_num, out_trade_no);
			payMsg = wechatPayService.closeOrder(closeOrderReqData);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(),"支付出现异常！", null);
		}
		return payMsg;
	}
	
	
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
					//alipayService.notifyPayment(params, true, "");
				} else {
					//alipayService.notifyPayment(params, false, "TRADE_CLOSED");
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

	

}
