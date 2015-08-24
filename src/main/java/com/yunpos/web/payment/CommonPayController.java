//package com.yunpos.web.payment;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.common.base.Strings;
//import com.yunpos.model.SysPayOrder;
//import com.yunpos.payment.RefundResData.PayChannel;
//import com.yunpos.payment.alipay.config.AlipayConfig;
//import com.yunpos.payment.alipay.model.AlipayQueryReqData;
//import com.yunpos.payment.alipay.model.AlipayScanPayReqData;
//import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
//import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundReqData;
//import com.yunpos.payment.wxpay.protocol.refund_query_protocol.RefundQueryReqData;
//import com.yunpos.service.SysPayOrderService;
//import com.yunpos.service.payment.AlipayService;
//import com.yunpos.service.payment.PayParam;
//import com.yunpos.service.payment.WechatPayService;
//import com.yunpos.utils.AmountUtils;
//import com.yunpos.utils.DateUtil;
//import com.yunpos.utils.IdWorker;
//import com.yunpos.utils.Message;
//import com.yunpos.utils.Message.ResultCode;
//
///**
// * 
// * 功能描述：通用支付订单创建Controller
// * <p>
// * 版权所有：小牛信息科技有限公司
// * <p>
// * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
// * 
// * @author Devin_Yang 新增日期：2015年8月12日
// * @author Devin_Yang 修改日期：2015年8月12日
// *
// */
//@Controller
//@RequestMapping("/pay")
//public class CommonPayController {
//	Logger log = LoggerFactory.getLogger(WchatpayController.class);
//
//	@Autowired
//	private WechatPayService wechatPayService;
//	@Autowired
//	private SysPayOrderService sysPayOrderService;
//	@Autowired
//	private AlipayService alipayService;
//
//	/**
//	 * 微信支付订单生成接口
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/create")
//	@ResponseBody
//	public Object wechatCreate(HttpServletRequest request, HttpServletResponse response) {
//		String pay_channel = request.getParameter("pay_channel");
//		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
//		String dynamic_id = request.getParameter("dynamic_id"); // 支付码（非空）
//		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
//		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
//		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
//		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）
//
//		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee) || Strings.isNullOrEmpty(dynamic_id)
//				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
//				|| Strings.isNullOrEmpty(client_type)) {
//			return new Message(ResultCode.FAIL.name(), "传递参数为空！", null);
//		}
//		Message payMsg = null;
//		try {
//			SysPayOrder payOrder = new SysPayOrder();
//			// 生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//			IdWorker iw = new IdWorker(idepo);
//			String orderNo = iw.getId()+"";
//			// String price =
//			// AmountUtils.changeY2F(request.getParameter("price"));
//			// payOrder.setPayOrderNo(orderNo + "");
//			// payOrder.setStatus(Byte.parseByte("2"));// 支付中
//			// payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
//			// payOrder.setUserId(Integer.getInteger(param.getUserId()));
//			// payOrder.setPrice(Long.valueOf(price)); // 微信支付单位分
//			// payOrder.setBarCode(param.getBarCode());
//			// payOrder.setImei(param.getImei());
//			// payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
//			// payOrder.setCreateAt(new Date());
//			// payOrder.setCreateBy(Integer.getInteger(param.getUserId()));
//			//
//			// // 保存支付流水
//			// sysPayOrderService.save(payOrder);
//			
//			if(pay_channel.equals(PayChannel.ALIPAY.name())){
//				AlipayScanPayReqData payReqData = new AlipayScanPayReqData(orderNo, AlipayConfig.partner, "支付宝条码支付",
//						"BARCODE_PAY_OFFLINE", total_fee, dynamic_id);
//				payMsg = alipayService.pay(payReqData);
//			}else if(pay_channel.equals(PayChannel.WECHAT.name())){
//				// 调用支付接口发起支付请求
//				int totalFee = Integer.valueOf(AmountUtils.changeY2F(total_fee));
//				String timeStart = DateUtil.getNow("yyyyMMddHHmmss");
//				String timeExpire = DateUtil.getDateAfter(timeStart, "yyyyMMddHHmmss", 1);
//				// 支付请求
//				ScanPayReqData scanPayReqData = new ScanPayReqData(dynamic_id.trim(), "微信条码支付测试", "测试附件", orderNo + "",
//						totalFee, terminal_unique_no, request.getLocalAddr(), timeStart, timeExpire, "条码支付");
//				payMsg = wechatPayService.scanPay(scanPayReqData);
//			}else{
//				return new Message(ResultCode.FAIL.name(), "未知的支付渠道", null);
//			}
//		} catch (Exception e) {
//			log.error("微信支付出现异常：", e);
//			return new Message(ResultCode.FAIL.name(), "支付出现异常！", null);
//		}
//		return payMsg;
//	}
//
//	/**
//	 * 查询订单
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/query")
//	@ResponseBody
//	public Object query(HttpServletRequest request, HttpServletResponse response) {
//		// 参数获取
//		String pay_channel = request.getParameter("pay_channel");
//		String merchant_num = request.getParameter("merchant_num");		//商户号
//		String trace_num = request.getParameter("trace_num");		//订单号
//		String terminal_unique_no = request.getParameter("terminal_unique_no");//终端编号
//		
//		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(trace_num)
//				|| Strings.isNullOrEmpty(terminal_unique_no)) {
//			return new Message(ResultCode.FAIL.name(), "传递参数为空！", null);
//		}
//		Message payMsg = null;
//		try {
//			if(pay_channel.equals(PayChannel.ALIPAY.name())){
//				AlipayQueryReqData alipayQueryReqData = new AlipayQueryReqData(trace_num,AlipayConfig.partner);
//				payMsg = alipayService.query(alipayQueryReqData);
//			}else if(pay_channel.equals(PayChannel.WECHAT.name())){
//				
//			}else{
//				
//			}
//			
//			payMsg = wechatPayService.query(trace_num);
//		} catch (Exception e) {
//			log.error("微信退款出现异常：", e);
//			return new Message(ResultCode.FAIL.name(), "支付出现异常！", null);
//		}
//		return payMsg;
//	}
//
//	/**
//	 * 微信支付退款申请
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/refund")
//	@ResponseBody
//	public Object refund(HttpServletRequest request, HttpServletResponse response) {
//		PayParam param = buildPayParam(request);
//		/*
//		 * if (Strings.isNullOrEmpty(param.getRoleId()) ||
//		 * Strings.isNullOrEmpty(param.getUserId()) ||
//		 * Strings.isNullOrEmpty(param.getPrice()) ||
//		 * Strings.isNullOrEmpty(param.getChannel()) ||
//		 * Strings.isNullOrEmpty(param.getImei()) ||
//		 * Strings.isNullOrEmpty(param.getDeviceType()) ||
//		 * Strings.isNullOrEmpty(param.getBarCode())) { return new
//		 * Message(false, "", "传递参数为空！"); }
//		 */
//		Message payMsg = null;
//		try {
//			String transactionID = "1002120689201508180645805301"; // 微信订单号
//			String outTradeNo = "15099500695552"; // 系统订单号
//			String deviceInfo = "设备信息"; // 设备信息
//			String outRefundNo = "0000000003"; // 商户退款单号
//			int totalFee = 1;
//			int refundFee = 1;
//			String opUserID = "1";
//			String refundFeeType = "CNY";
//
//			RefundReqData refundReqData = new RefundReqData(transactionID, outTradeNo, deviceInfo, outRefundNo,
//					totalFee, refundFee, opUserID, refundFeeType);
//			payMsg = wechatPayService.refund(refundReqData);
//		} catch (Exception e) {
//			log.error("微信退款出现异常：", e);
//			return new Message(ResultCode.FAIL.name(), "支付出现异常！", null);
//		}
//		return payMsg;
//	}
//
//	/**
//	 * 退款查询
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/refundQuery")
//	@ResponseBody
//	public Object refundQuery(HttpServletRequest request, HttpServletResponse response) {
//		PayParam param = buildPayParam(request);
//		if (Strings.isNullOrEmpty(param.getRoleId()) || Strings.isNullOrEmpty(param.getUserId())
//				|| Strings.isNullOrEmpty(param.getPrice()) || Strings.isNullOrEmpty(param.getChannel())
//				|| Strings.isNullOrEmpty(param.getImei()) || Strings.isNullOrEmpty(param.getDeviceType())
//				|| Strings.isNullOrEmpty(param.getBarCode())) {
//			return new Message(ResultCode.FAIL.name(), "传递参数为空！", null);
//		}
//		Message payMsg = null;
//		try {
//			String transactionID = "1002120689201508180645805301"; // 微信订单号
//			String outTradeNo = "15099500695552"; // 系统订单号
//			String deviceInfo = "设备信息"; // 设备信息
//			String outRefundNo = "0000000003"; // 商户退款单号
//			String refundID = "2002120689201508190029977856";//
//
//			RefundQueryReqData refundQueryReqData = new RefundQueryReqData(transactionID, outTradeNo, deviceInfo,
//					outRefundNo, refundID);
//			payMsg = wechatPayService.refundQuery(refundQueryReqData);
//
//		} catch (Exception e) {
//			log.error("微信退款出现异常：", e);
//			return new Message(ResultCode.FAIL.name(), "支付出现异常！", null);
//		}
//		return payMsg;
//	}
//	
//	private PayParam buildPayParam(HttpServletRequest request) {
//		PayParam param = new PayParam();
//		param.setBarCode(request.getParameter("barCode"));
//		param.setChannel(request.getParameter("channel"));
//		param.setDeviceType(request.getParameter("deviceType"));
//		param.setImei(request.getParameter("imei"));
//		param.setPrice(request.getParameter("price"));
//		param.setRoleId(request.getParameter("roleId"));
//		param.setUserId(request.getParameter("userId"));
//		log.info("支付传递参数：" + param.toString());
//		return param;
//	}
//}
