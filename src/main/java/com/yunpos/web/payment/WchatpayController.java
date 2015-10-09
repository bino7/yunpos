package com.yunpos.web.payment;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysTransaction;
import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.payment.wxpay.common.Signature;
import com.yunpos.payment.wxpay.common.Util;
import com.yunpos.payment.wxpay.protocol.close_protocol.CloseOrderReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanCodePayReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundReqData;
import com.yunpos.payment.wxpay.protocol.refund_query_protocol.RefundQueryReqData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseReqData;
import com.yunpos.payment.wxwap.model.WapPayReqData;
import com.yunpos.payment.wxwap.utils.HttpTool;
import com.yunpos.payment.wxwap.utils.Sha1Util;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysTransactionService;
import com.yunpos.service.SysWechatConfigService;
import com.yunpos.service.payment.WechatPayService;
import com.yunpos.service.payment.WechatWapPayService;
import com.yunpos.utils.AmountUtils;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.MD5Utils;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;
import com.yunpos.utils.XMLUtil;
import com.yunpos.web.BaseController;

import net.sf.json.JSONObject;

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
public class WchatpayController extends BaseController{
	Logger log = LoggerFactory.getLogger(WchatpayController.class);

	@Autowired
	private WechatPayService wechatPayService;
	@Autowired
	private SysMerchantService sysMerchantService;
	@Autowired
	private SysTransactionService sysTransactionService;
	@Autowired
	private SysWechatConfigService sysWechatConfigService;
	@Autowired
	private WechatWapPayService wechatWapPayService;
	
	IdWorker iw = new IdWorker();

	/**
	 * 微信支付订单生成接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/wechatpay/create")
	@ResponseBody
	public Object barCreate(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String dynamic_id = request.getParameter("dynamic_id"); // 支付码（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String user_order_no = request.getParameter("user_order_no"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 商户订单号
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）
		String body = request.getParameter("body"); //商品描述信息，支付成功时用户在支付结果中看到

		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee) || Strings.isNullOrEmpty(dynamic_id)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)||Strings.isNullOrEmpty(user_order_no)||Strings.isNullOrEmpty(body)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "MERCHANT_NOT_FIND", "该商户号不存在", null);
			}
			
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "PAYCONFIG_NOT_FIND", "支付信息未配置", null);
			}
			SysTransaction temSysTransaction = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no, merchant_num);
			if(temSysTransaction!=null){
				return new Message(ResultCode.FAIL.name(), "USER_ORDER_NOT_EXIST", "商户订单号已存在", null);
			}
			// 生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";

			SysTransaction sysTransaction = new SysTransaction();
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			// 1支付宝，2微信，3银联，4：预存款
			if (pay_channel.trim().equalsIgnoreCase("alipay")) {
				sysTransaction.setChannel(1);
			} else if (pay_channel.trim().equalsIgnoreCase("wechat")) {
				sysTransaction.setChannel(2);
			} else if (pay_channel.trim().equalsIgnoreCase("bill")) {
				sysTransaction.setChannel(3);
			} else if (pay_channel.trim().equalsIgnoreCase("prepay")) {
				sysTransaction.setChannel(4);
			} else {
				return new Message("error", "pay_channel_unkonw", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信线下条码支付");
			sysTransaction.setSubject(body);
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			sysTransaction.setScanType(0); // 正扫：1// QR_CODE_OFFLIN，反扫：0// BARCODE_PAY_OFFLINE
			sysTransaction.setUser_order_no(user_order_no);
			if (!Strings.isNullOrEmpty(cashier_num)) {
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus(1); // 付款状态， 0：未付款，1：付款中，2：已付款
												// ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType(0); // 交易类型，0:支付，1:退款
			sysTransaction.setInfo("微信线下条码支付");
			sysTransactionService.save(sysTransaction);

			// 调用支付接口发起支付请求
			int totalFee = Integer.valueOf(AmountUtils.changeY2F(total_fee));
			String ip = InetAddress.getLocalHost().getHostAddress();
			// 支付请求
			ScanPayReqData scanPayReqData = new ScanPayReqData(dynamic_id,body, "attach data",
					orderNo, totalFee, terminal_unique_no, ip, "bar pay", sysWechatConfig);
			
			Map<String ,String> dtoMap = new HashMap<>();
			dtoMap.put("merchant_name", sysMerchant.getCompanyName());
			dtoMap.put("merchant_num", sysMerchant.getSerialNo());
			dtoMap.put("terminal_unique_no", terminal_unique_no);
			dtoMap.put("user_order_no", user_order_no);
			
			payMsg = wechatPayService.barPay(scanPayReqData, sysWechatConfig,dtoMap);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	@RequestMapping(value = "/pay/wechatpay/scan/create")
	@ResponseBody
	public Object scanCreate(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）
		String user_order_no = request.getParameter("user_order_no"); // 商户订单号

		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)|| Strings.isNullOrEmpty(user_order_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "MERCHANT_NOT_FIND", "该商户号不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "PAYCONFIG_NOT_FIND", "支付信息未配置", null);
			}
			SysTransaction temSysTransaction = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no, merchant_num);
			if(temSysTransaction!=null){
				return new Message(ResultCode.FAIL.name(), "USER_ORDER_NOT_EXIST", "商户订单号已存在", null);
			}
			// 生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";

			SysTransaction sysTransaction = new SysTransaction();
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			// 1支付宝，2微信，3银联，4：预存款
			if (pay_channel.trim().equalsIgnoreCase("alipay")) {
				sysTransaction.setChannel(1);
			} else if (pay_channel.trim().equalsIgnoreCase("wechat")) {
				sysTransaction.setChannel(2);
			} else if (pay_channel.trim().equalsIgnoreCase("bill")) {
				sysTransaction.setChannel(3);
			} else if (pay_channel.trim().equalsIgnoreCase("prepay")) {
				sysTransaction.setChannel(4);
			} else {
				return new Message("error", "pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信线下扫码支付");
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			sysTransaction.setScanType(1); // 正扫：1// QR_CODE_OFFLIN，反扫：0// BARCODE_PAY_OFFLINE
			sysTransaction.setUser_order_no(user_order_no);
			if (!Strings.isNullOrEmpty(cashier_num)) {
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus(1); // 付款状态， 0：未付款，1：付款中，2：已付款
												// ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType(0); // 交易类型，0:支付，1:退款
			sysTransaction.setInfo("微信线下扫码支付");
			sysTransactionService.save(sysTransaction);

			// 调用支付接口发起支付请求
			int totalFee = Integer.valueOf(AmountUtils.changeY2F(total_fee));
			String ip = InetAddress.getLocalHost().getHostAddress();
			// 支付请求
			ScanCodePayReqData scanCodePayReqData = new ScanCodePayReqData("wechat pay", orderNo, totalFee,
					terminal_unique_no, ip, "wechat pay", "wechat pay test", sysWechatConfig);
			payMsg = wechatPayService.unifiedOrder(scanCodePayReqData, sysWechatConfig,user_order_no);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	
	/**
	 * 流程：
	 * 1、用户同意授权，获取code （https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect）
	 * 2、通过code换取网页授权access_token  （https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code）
	 * 3、
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pay/wechatpay/wap/create")
	public Object wapCreate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("wechatpay");
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）
		String user_order_no = request.getParameter("user_order_no"); // 商户订单号
		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)||Strings.isNullOrEmpty(user_order_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			SysTransaction temSysTransaction = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no, merchant_num);
			if(temSysTransaction!=null){
				return new Message(ResultCode.FAIL.name(), "USER_ORDER_NOT_EXIST", "商户订单号已存在", null);
			}
			//前段页面授权跳转到该地址，应用获取授权code发起
			String code = request.getParameter("code");
			log.info("#####code="+code);
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			//获取access_token 和openid
			String returnJSON= HttpTool.getToken(sysWechatConfig.getAppId(), sysWechatConfig.getAppSecret(), "authorization_code", code);
			log.info("#####returnJSON="+returnJSON);
			JSONObject obj = JSONObject.fromObject(returnJSON);
			String openid=obj.get("openid").toString();
			
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			// 生成流水表信息
			String orderNo = iw.getId() + "";
			SysTransaction sysTransaction = new SysTransaction();
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			// 1支付宝，2微信，3银联，4：预存款
			if (pay_channel.trim().equalsIgnoreCase("alipay")) {
				sysTransaction.setChannel(1);
			} else if (pay_channel.trim().equalsIgnoreCase("wechat")) {
				sysTransaction.setChannel(2);
			} else if (pay_channel.trim().equalsIgnoreCase("bill")) {
				sysTransaction.setChannel(3);
			} else if (pay_channel.trim().equalsIgnoreCase("prepay")) {
				sysTransaction.setChannel(4);
			} else {
				log.error("微信支付出现异");
				return new Message("error", "pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信wap支付");
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			sysTransaction.setScanType(1); 					// 正扫：1// QR_CODE_OFFLIN，反扫：0// BARCODE_PAY_OFFLINE
			if (!Strings.isNullOrEmpty(cashier_num)) {
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus(1); // 付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType(0); // 交易类型，0:支付，1:退款
			sysTransaction.setInfo("微信wap支付");
			sysTransaction.setUser_order_no(user_order_no);
			sysTransactionService.save(sysTransaction);
			
			// 调用支付接口发起支付请求
			int totalFee = Integer.valueOf(AmountUtils.changeY2F(total_fee));
			String ip = InetAddress.getLocalHost().getHostAddress();
			// 支付请求
			WapPayReqData  wapPayReqData =new WapPayReqData(orderNo, "wechat pay test", totalFee, terminal_unique_no, ip,openid, sysWechatConfig);
			payMsg = wechatWapPayService.unifiedOrder(wapPayReqData,sysWechatConfig);
			if(payMsg.getResult_code().equals("SUCCESS")){
				payMsg.getLists().put("package", "prepay_id="+payMsg.getLists().get("prepay_id"));
				Map<String ,String> reMap = payMsg.getLists();
				
				Map<String,Object> wxPayParamMap = new HashMap<>(); 
				wxPayParamMap.put("appId", reMap.get("appid"));
				wxPayParamMap.put("timeStamp",Sha1Util.getTimeStamp() );		
				wxPayParamMap.put("nonceStr", reMap.get("nonce_str"));
				wxPayParamMap.put("package", "prepay_id="+reMap.get("prepay_id"));
				wxPayParamMap.put("signType", "MD5");
				String paySign=  Signature.getSign(wxPayParamMap, sysWechatConfig.getApiSecret());
				wxPayParamMap.put("paySign", paySign);
				
				modelAndView.addObject("appId", wxPayParamMap.get("appId"));
				modelAndView.addObject("timeStamp", wxPayParamMap.get("timeStamp"));
				modelAndView.addObject("nonceStr", wxPayParamMap.get("nonceStr"));
				modelAndView.addObject("packagess", wxPayParamMap.get("package"));
				modelAndView.addObject("signType", wxPayParamMap.get("signType"));
				modelAndView.addObject("paySign", wxPayParamMap.get("paySign"));
				modelAndView.addObject("id", sysTransaction.getId());
			}else{
				return payMsg;
			}
		} catch (Exception e) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return modelAndView;
	}
	

	/**
	 * 关闭订单： 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，
	 * 系统退出不再受理，避免用户继续，请调用关单接口。 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/wechatpay/scan/close")
	@ResponseBody
	public Object closeOrder(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		String user_order_no = request.getParameter("user_order_no"); // 商户订单号

		if (Strings.isNullOrEmpty(pay_channel) ||Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(user_order_no)
				|| Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			
			String out_trade_no="";
			if(Strings.isNullOrEmpty(trace_num)){
				SysTransaction sysTransaction  = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no,merchant_num);
				if(sysTransaction!=null){
					out_trade_no = sysTransaction.getTransNum();
				}
			}else{
				out_trade_no = trace_num;
			}
			
			// 支付请求
			CloseOrderReqData closeOrderReqData = new CloseOrderReqData(merchant_num, out_trade_no, sysWechatConfig);
			payMsg = wechatPayService.closeOrder(closeOrderReqData, sysWechatConfig,user_order_no);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	
	/**
	 * 微信条码支付撤销订单
	 * @param request
	 * @param response
	 * @return
	 * https://api.mch.weixin.qq.com/secapi/pay/reverse
	 */
	@RequestMapping(value = "/pay/wechatpay/reverse")
	@ResponseBody
	public Object reverse(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		String user_order_no =  request.getParameter("user_order_no");

		if (Strings.isNullOrEmpty(pay_channel) ||Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(user_order_no)
				|| Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			String out_trade_no="";
			if(Strings.isNullOrEmpty(trace_num)){
				SysTransaction sysTransaction  = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no,merchant_num);
				if(sysTransaction!=null){
					out_trade_no = sysTransaction.getTransNum();
				}
			}else{
				out_trade_no = trace_num;
			}
			// 支付请求
			ReverseReqData reverseReqData = new ReverseReqData(merchant_num, out_trade_no, sysWechatConfig);
			payMsg = wechatPayService.reverse(reverseReqData, sysWechatConfig,user_order_no);
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/wechatpay/query")
	@ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		String user_order_no =  request.getParameter("user_order_no");
		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(merchant_num)
				|| Strings.isNullOrEmpty(user_order_no) || Strings.isNullOrEmpty("terminal_unique_no")) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}
		Message payMsg = null;
		try {
			SysMerchant	sysMerchant =sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			String out_trade_no="";
			if(Strings.isNullOrEmpty(trace_num)){
				SysTransaction sysTransaction  = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no,merchant_num);
				if(sysTransaction!=null){
					out_trade_no = sysTransaction.getTransNum();
				}
			}else{
				out_trade_no = trace_num;
			}
			
			Map<String,String> dtoMap = new HashMap<>();
			dtoMap.put("trace_num", out_trade_no);
			dtoMap.put("merchant_num", merchant_num);
			dtoMap.put("merchant_name", sysMerchant.getCompanyName());
			dtoMap.put("terminal_unique_no", terminal_unique_no);
			dtoMap.put("user_order_no", user_order_no);
			payMsg = wechatPayService.query(dtoMap);
		} catch (Exception e) {
			log.error("微信退款出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	/**
	 * 微信支付退款申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/wechatpay/refund")
	@ResponseBody
	public Object refund(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String pay_channel = request.getParameter("pay_channel");
		String merchant_num = request.getParameter("merchant_num");
		String trace_num = request.getParameter("trace_num");
		String transactionID = request.getParameter("transactionID");
		String refund_amount = request.getParameter("refund_amount");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		String user_order_no =  request.getParameter("user_order_no"); 
		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(merchant_num)
				|| Strings.isNullOrEmpty(user_order_no) || Strings.isNullOrEmpty(refund_amount)
				|| Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}

		Message payMsg = null;
		try {
			SysMerchant	sysMerchant =sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			// 获取原支付流水
			SysTransaction sysTransaction = sysTransactionService.findByTransNum(trace_num);
			if (sysTransaction == null) {
				return new Message(ResultCode.FAIL.name(), "original_order_not_fund", "找不到原支付流水号！", null);
			}
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			
			String out_trade_no="";
			if(Strings.isNullOrEmpty(trace_num)){
				SysTransaction temp  = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no,merchant_num);
				if(temp!=null){
					out_trade_no = sysTransaction.getTransNum();
				}
			}else{
				out_trade_no = trace_num;
			}
			
			// 生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//			IdWorker iw = new IdWorker(idepo);
			String refundNo = "R" + iw.getId();
			sysTransaction.setId(null);
			// 1支付宝，2微信，3银联，4：预存款
			if (pay_channel.trim().equalsIgnoreCase("alipay")) {
				sysTransaction.setChannel(1);
			} else if (pay_channel.trim().equalsIgnoreCase("wechat")) {
				sysTransaction.setChannel(2);
			} else if (pay_channel.trim().equalsIgnoreCase("bill")) {
				sysTransaction.setChannel(3);
			} else if (pay_channel.trim().equalsIgnoreCase("prepay")) {
				sysTransaction.setChannel(4);
			} else {
				return new Message("error", "pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("退款申请");
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(refundNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(refund_amount));
			sysTransaction.setStatus(4); // 付款状态， 0：未付款，1：付款中，2：已付款
												// ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType(1); // 交易类型，0:支付，1:退款
			sysTransaction.setInfo("退款");
			sysTransaction.setUser_order_no(user_order_no);
			sysTransactionService.save(sysTransaction);

			RefundReqData refundReqData = new RefundReqData(transactionID, out_trade_no, terminal_unique_no, refundNo,
					Integer.valueOf(AmountUtils.changeY2F(refund_amount)),
					Integer.valueOf(AmountUtils.changeY2F(refund_amount)), merchant_num, "CNY", sysWechatConfig);

			payMsg = wechatPayService.refund(refundReqData, sysWechatConfig,user_order_no);
		} catch (Exception e) {
			log.error("微信退款出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	
	

	/**
	 * 退款查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/wechatpay/refundQuery")
	@ResponseBody
	public Object refundQuery(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> reqParamMap = this.getRequestParams(request);
		String merchant_num = request.getParameter("merchant_num");
		String terminal_unique_no = request.getParameter("terminal_unique_no");
		String user_order_no =  request.getParameter("user_order_no"); 
		String trace_num = request.getParameter("trace_num");

		if (Strings.isNullOrEmpty(merchant_num)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "商户号不能为空！", null);
		}
		if (Strings.isNullOrEmpty(user_order_no)&&Strings.isNullOrEmpty(terminal_unique_no)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "商户订单号、平台流水号不能同时为空！", null);
		}
		if(Strings.isNullOrEmpty(reqParamMap.get("sign"))){
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "签名为空!！", null);
		}

		Message payMsg = null;
		try {
			SysMerchant	sysMerchant =sysMerchantService.findBySerialNo(merchant_num);
			if (sysMerchant == null) {
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户不存在", null);
			}
			if(!MD5Utils.verify(reqParamMap, reqParamMap.get("sign"), sysMerchant.getMd5Key(), "utf-8")){
				return new Message(ResultCode.FAIL.name(), "ILLEGAL_SIGN", "验签错误，请求数据可能被篡改", null);
			}
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchant_num);
			if (sysWechatConfig == null) {
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			
			String out_trade_no="";
			if(Strings.isNullOrEmpty(trace_num)){
				SysTransaction sysTransaction  = sysTransactionService.findbyOrderNoAndMerchantNo(user_order_no,merchant_num);
				if(sysTransaction!=null){
					out_trade_no = sysTransaction.getTransNum();
				}
			}else{
				out_trade_no = trace_num;
			}
			
			RefundQueryReqData refundQueryReqData = new RefundQueryReqData(out_trade_no, terminal_unique_no,sysWechatConfig);
			reqParamMap.put("merchant_name", sysMerchant.getMerchantName());
			payMsg = wechatPayService.refundQuery(refundQueryReqData, sysWechatConfig,reqParamMap);

		} catch (Exception e) {
			log.error("微信退款出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	/**
	 * 微信扫码支付异步回调
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/pay/wechatpay/scan/notify")
	public void scanNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("receive wechatpay notify");
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			String xml = Util.inputStreamToString(request.getInputStream());
			Map<String, String> responseXml = XMLUtil.parse(xml);
			log.info("支付宝异步通知参数：", xml);
			// 商户网站唯一订单号
			String trade_status = responseXml.get("trade_status");
			if (!Objects.equal("TRADE_CLOSED", trade_status)) {
				wechatPayService.scanNotify(responseXml, true, "");
			} else {
				wechatPayService.scanNotify(responseXml, false, "TRADE_CLOSED");
			}
			writer.write("success");
			writer.flush();
		} catch (Exception e) {
			log.error("处理支付宝异步通知异常", e);
			writer.write("fail");
			writer.flush();
		}
	}
	
	
	@RequestMapping("/pay/wechatpay/wap/notify")
	public void wapNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("###########receive wechatpay wap notify");
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
			String xml = Util.inputStreamToString(request.getInputStream());
			Map<String, String> responseXml = XMLUtil.parse(xml);
			log.info("支付宝异步通知参数：", xml);
			// 商户网站唯一订单号
			String return_code = responseXml.get("return_code");
			String result_code = responseXml.get("result_code");
			if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
				wechatPayService.scanNotify(responseXml, true, "");
			}else{
				wechatPayService.scanNotify(responseXml, false, "");
			}
			writer.write("success");
			writer.flush();
		} catch (Exception e) {
			log.error("处理支付宝异步通知异常", e);
			writer.write("fail");
			writer.flush();
		}
	}

}
