package com.yunpos.web.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.yunpos.model.SysAlipayConfig;
import com.yunpos.model.SysAlipayConfigWithBLOBs;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysTransaction;
import com.yunpos.payment.alipay.model.AlipayPrecreateReqData;
import com.yunpos.payment.alipay.model.AlipayQueryReqData;
import com.yunpos.payment.alipay.model.AlipayRefundReqData;
import com.yunpos.payment.alipay.model.AlipayScanPayReqData;
import com.yunpos.payment.alipay.model.AlipayWapPayReqData;
import com.yunpos.payment.alipay.util.AlipayNotify;
import com.yunpos.service.SysAlipayConfigService;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysTransactionService;
import com.yunpos.service.payment.AlipayService;
import com.yunpos.service.payment.AlipayWapService;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;
import com.yunpos.web.BaseController;

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
public class AlipayController extends BaseController{
	Logger log = LoggerFactory.getLogger(AlipayController.class);
	
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private SysTransactionService sysTransactionService;
	@Autowired
	private SysMerchantService sysMerchantService;
	@Autowired
	private AlipayWapService alipayWapService;
	@Autowired
	private SysAlipayConfigService sysAlipayConfigService;
	
	 ObjectMapper mapper = new ObjectMapper(); 

	/**
	 * 支付宝条码支付-统一下单并支付
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/alipay/create")
	@ResponseBody
	public Object barCreate(HttpServletRequest request, HttpServletResponse response) {
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
			//获取商户信息
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num.trim());
			if(sysMerchant ==null){
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(merchant_num.trim());
			if(sysAlipayConfig == null){
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";

			SysTransaction sysTransaction = new SysTransaction();
			//sysTransaction.setOrderId(orderNo);
			sysTransaction.setTransCardNum(sysAlipayConfig.getSellerEmail());
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			//1支付宝，2微信，3银联，4：预存款
			if(pay_channel.trim().equals("alipay")){
				sysTransaction.setChannel(Byte.valueOf("1"));
			}else if(pay_channel.trim().equals("wechat")){
				sysTransaction.setChannel(Byte.valueOf("2"));
			}else if(pay_channel.trim().equals("bill")){
				sysTransaction.setChannel(Byte.valueOf("3"));
			}else if(pay_channel.trim().equals("prepay")){
				sysTransaction.setChannel(Byte.valueOf("4"));
			}else{
				return new Message("error","pay_channel_unkonw", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信线下条码支付");
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			sysTransaction.setScanType(Byte.valueOf("0"));		//正扫：1 QR_CODE_OFFLIN，反扫：0 BARCODE_PAY_OFFLINE
			if(!Strings.isNullOrEmpty(cashier_num)){
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus((byte)1); 		//付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType((byte)0);	//交易类型，0:支付，1:退款
			sysTransaction.setInfo("支付宝线下条码支付");
			sysTransactionService.save(sysTransaction);
			Map<String,String> map = new HashMap<>();
			map.put("AGENT_ID", sysMerchant.getAgentSerialNo());
			//代理商需要分润，支付时需要带入分润ID
			String extend_params = mapper.writeValueAsString(map);
			
			AlipayScanPayReqData payReqData = new AlipayScanPayReqData(orderNo, sysAlipayConfig.getPid(), "支付宝条码支付",
					"BARCODE_PAY_OFFLINE", total_fee, dynamic_id,extend_params);
			payReqData.setPay_channel(pay_channel);
			payReqData.setTerminal_unique_no(terminal_unique_no);
			payReqData.setMerchant_num(sysAlipayConfig.getPid());
			
			payMsg = alipayService.pay(payReqData,sysAlipayConfig);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	
	/**
	 * 支付宝扫码支付-统一预下单
	 * 注意事项： 支付需要分润，需要带入代理商ID（agent_id）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/alipay/scan/create")
	@ResponseBody
	public Object scanCreate(HttpServletRequest request, HttpServletResponse response) {
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）

		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)) {
			return new Message(ResultCode.FAIL.name(), ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		Message payMsg = null;
		try {
			
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num.trim());
			if(sysMerchant ==null){
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(merchant_num.trim());
			if(sysAlipayConfig == null){
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";

			SysTransaction sysTransaction = new SysTransaction();
			//sysTransaction.setOrderId(orderNo);
			sysTransaction.setTransCardNum(sysAlipayConfig.getSellerEmail());
			//1支付宝，2微信，3银联，4：预存款
			if(pay_channel.trim().equals("alipay")){
				sysTransaction.setChannel(Byte.valueOf("1"));
			}else if(pay_channel.trim().equals("wechat")){
				sysTransaction.setChannel(Byte.valueOf("2"));
			}else if(pay_channel.trim().equals("bill")){
				sysTransaction.setChannel(Byte.valueOf("3"));
			}else if(pay_channel.trim().equals("prepay")){
				sysTransaction.setChannel(Byte.valueOf("4"));
			}else{
				return new Message("error","pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信线下扫码支付");
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			sysTransaction.setScanType(Byte.valueOf("1"));		//正扫：1 QR_CODE_OFFLIN，反扫：0 BARCODE_PAY_OFFLINE
			if(!Strings.isNullOrEmpty(cashier_num)){
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus((byte)1); 		//付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType((byte)0);	//交易类型，0:支付，1:退款
			sysTransaction.setInfo("线下扫码支付");
			sysTransactionService.save(sysTransaction);
			Map<String,String> map = new HashMap<>();
			map.put("AGENT_ID", sysMerchant.getAgentSerialNo());
			String extend_params = mapper.writeValueAsString(map);
			
			AlipayPrecreateReqData alipayPrecreateReqData = new AlipayPrecreateReqData(orderNo, sysAlipayConfig.getPid(),"扫码预支付", total_fee,extend_params);

			payMsg = alipayService.preCreate(alipayPrecreateReqData,sysAlipayConfig);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	
	@RequestMapping(value = "/pay/alipay/wap/create")
	@ResponseBody
	public Object wapCreate(HttpServletRequest request, HttpServletResponse response) {
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
		try {
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if(sysMerchant ==null){
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(sysMerchant.getSerialNo());
			if(sysAlipayConfig == null){
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";

			SysTransaction sysTransaction = new SysTransaction();
			//sysTransaction.setOrderId(orderNo);
			//1支付宝，2微信，3银联，4：预存款
			if(pay_channel.trim().equals("alipay")){
				sysTransaction.setChannel(Byte.valueOf("1"));
			}else if(pay_channel.trim().equals("wechat")){
				sysTransaction.setChannel(Byte.valueOf("2"));
			}else if(pay_channel.trim().equals("bill")){
				sysTransaction.setChannel(Byte.valueOf("3"));
			}else if(pay_channel.trim().equals("prepay")){
				sysTransaction.setChannel(Byte.valueOf("4"));
			}else{
				return new Message("error","pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("微信线下条码支付");
			sysTransaction.setMerchantName(sysMerchant.getCompanyName());
			sysTransaction.setSerialNo(merchant_num);
			sysTransaction.setAgentSerialNo(sysMerchant.getAgentSerialNo());
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(orderNo);
			sysTransaction.setTransTime(new Date());
			
			sysTransaction.setTotalPrice(Float.valueOf(total_fee));
			//sysTransaction.setScanType(Byte.valueOf("0"));		//正扫：1 QR_CODE_OFFLIN，反扫：0 BARCODE_PAY_OFFLINE
			if(!Strings.isNullOrEmpty(cashier_num)){
				sysTransaction.setCouponCode(cashier_num);
			}
			sysTransaction.setStatus((byte)1); 		//付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType((byte)0);	//交易类型，0:支付，1:退款
			sysTransaction.setInfo("手机网站（手机wap）在线支付");
			sysTransactionService.save(sysTransaction);
			
			AlipayWapPayReqData payReqData = new AlipayWapPayReqData(orderNo, sysAlipayConfig.getPid(), "支付宝手机wap支付", total_fee, sysAlipayConfig.getPid());
			payReqData.setPay_channel(pay_channel);
			payReqData.setTerminal_unique_no(terminal_unique_no);
			payReqData.setMerchant_num(sysAlipayConfig.getPid());
			
			alipayWapService.pay(payReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return null;
	}
	

	/**
	 * 支付宝支付查询接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/pay/alipay/query")
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
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(merchant_num);
			if(sysMerchant ==null){
				return new Message(ResultCode.FAIL.name(), "merchant_not_find", "该商户号不存在", null);
			}
			//获取配置信息
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(sysMerchant.getSerialNo());
			if(sysAlipayConfig == null){
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			
			AlipayQueryReqData alipayQueryReqData = new AlipayQueryReqData(trace_num, sysAlipayConfig.getPid());
			alipayQueryReqData.setPay_channel(pay_channel);
			alipayQueryReqData.setTerminal_unique_no(terminal_unique_no);
			alipayQueryReqData.setMerchant_num(sysAlipayConfig.getPid());
			
			payMsg = alipayService.query(alipayQueryReqData,sysAlipayConfig);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}

	@RequestMapping(value = "/pay/alipay/refund")
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
			//获取原支付流水
			SysTransaction sysTransaction = sysTransactionService.findByTransNum(trace_num);
			if(sysTransaction == null){
				return new Message(ResultCode.FAIL.name(),"original_order_not_fund", "找不到原支付流水号！", null);
			}
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(merchant_num);
			if(sysAlipayConfig == null){
				return new Message(ResultCode.FAIL.name(), "payconfig_not_find", "支付信息未配置", null);
			}
			
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String refundOrderNo = "R"+iw.getId();
			sysTransaction.setId(null);
		
			//1支付宝，2微信，3银联，4：预存款
			if(pay_channel.trim().equals("alipay")){
				sysTransaction.setChannel(Byte.valueOf("1"));
			}else if(pay_channel.trim().equals("wechat")){
				sysTransaction.setChannel(Byte.valueOf("2"));
			}else if(pay_channel.trim().equals("bill")){
				sysTransaction.setChannel(Byte.valueOf("3"));
			}else if(pay_channel.trim().equals("prepay")){
				sysTransaction.setChannel(Byte.valueOf("4"));
			}else{
				return new Message("error","pay_channel_unknow", "未知支付方式！", null);
			}
			sysTransaction.setTitle("退款申请");
			sysTransaction.setTerminalNum(terminal_unique_no);
			sysTransaction.setTransNum(refundOrderNo);
			sysTransaction.setTransTime(new Date());
			sysTransaction.setTotalPrice(Float.valueOf(refund_amount));
			sysTransaction.setStatus((byte)4); 		//付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败
			sysTransaction.setTransType((byte)1);	//交易类型，0:支付，1:退款
			sysTransaction.setInfo("退款");
			sysTransactionService.save(sysTransaction);
			
			AlipayRefundReqData alipayRefundReqData = new AlipayRefundReqData(sysAlipayConfig.getPid(), trace_num,
					refund_amount);
			alipayRefundReqData.setPay_channel(pay_channel);
			alipayRefundReqData.setTerminal_unique_no(terminal_unique_no);
			alipayRefundReqData.setMerchant_num(sysAlipayConfig.getPid());
			
			payMsg = alipayService.refund(alipayRefundReqData,sysTransaction);
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
	@RequestMapping("/pay/alipay/notify")
	public void barNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("receive bar notyfy message");
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
			log.info("bar notyfy param：", params);
			
			SysTransaction sysTransaction= sysTransactionService.findByTransNum(params.get("out_trade_no"));
			SysAlipayConfigWithBLOBs sysAlipayConfig= sysAlipayConfigService.findByMerchantNo(sysTransaction.getSerialNo());
			
			if (AlipayNotify.verify(params,sysAlipayConfig.getKey())) {// 验证成功
				if (!Objects.equal("TRADE_CLOSED",  params.get("trade_status"))) {
					alipayService.notify(params, true, "","bar");
				} else {
					alipayService.notify(params, false, "TRADE_CLOSED","bar");
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
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pay/alipay/scan/notify")
	public void scanNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("receive alipay scan notify");
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
			log.info("alipay scn notify param：", params.toString());
			
		
			SysTransaction sysTransaction= sysTransactionService.findByTransNum(params.get("out_trade_no"));
			SysAlipayConfigWithBLOBs sysAlipayConfig= sysAlipayConfigService.findByMerchantNo(sysTransaction.getSerialNo());
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			if (AlipayNotify.verify(params,sysAlipayConfig.getKey())) {// 验证成功
				if (!Objects.equal("TRADE_CLOSED", trade_status)) {
					alipayService.notify(params, true, "","scan");
				} else {
					alipayService.notify(params, false, "TRADE_CLOSED","scan");
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
	
	
	/**
	 * 支付宝手机网站支付同步跳转地址
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pay/alipay/wap/synNotify")
	@ResponseBody
	public Object wapSynNotify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("notify");
		log.info("receive alipay wap synnotify message!");
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
			
			SysTransaction sysTransaction= sysTransactionService.findByTransNum(params.get("out_trade_no"));
			SysAlipayConfig sysAlipayConfig= sysAlipayConfigService.findByMerchantNo(sysTransaction.getSerialNo());
			
			log.info("alipay wap synnotify param：", params.toString());
			if (AlipayNotify.verify(params,sysAlipayConfig.getKey())) {// 验证成功
				model.addObject(params);
			} else {// 验证失败
				log.info("支付宝手机wap同步通知请求验证失败...");
				return new Message(ResultCode.FAIL.name(), "", "支付宝手机wap同步通知请求验证失败", null);
			}
		} catch(Exception e) {
			log.error("支付宝手机wap同步通知异常", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "系统异常", null);
		}
		return model;
	}


	/**
	 * 支付宝手机网站支付异步通知
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pay/alipay/wap/asynNotify")
	public void wapNotify(HttpServletRequest request, HttpServletResponse response) {
		log.info("call wap notify");
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
			SysTransaction sysTransaction= sysTransactionService.findByTransNum(params.get("out_trade_no"));
			SysAlipayConfig sysAlipayConfig= sysAlipayConfigService.findByMerchantNo(sysTransaction.getSerialNo());
			
			log.info("wap notify param：", params);
			String trade_status = request.getParameter("trade_status");
			if (AlipayNotify.verify(params,sysAlipayConfig.getKey())) {// 验证成功
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
