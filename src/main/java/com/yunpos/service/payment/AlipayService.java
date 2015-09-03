package com.yunpos.service.payment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.yunpos.model.SysAlipayConfig;
import com.yunpos.model.SysAlipayConfigWithBLOBs;
import com.yunpos.model.SysTransaction;
import com.yunpos.payment.PayResData;
import com.yunpos.payment.PreCreateResData;
import com.yunpos.payment.QueryResData;
import com.yunpos.payment.RefundResData;
import com.yunpos.payment.RefundResData.PayChannel;
import com.yunpos.payment.alipay.model.AlipayPrecreateReqData;
import com.yunpos.payment.alipay.model.AlipayQueryReqData;
import com.yunpos.payment.alipay.model.AlipayRefundReqData;
import com.yunpos.payment.alipay.model.AlipayScanPayReqData;
import com.yunpos.payment.alipay.util.AlipaySubmit;
import com.yunpos.payment.alipay.util.Constant;
import com.yunpos.service.SysAlipayConfigService;
import com.yunpos.service.SysTransactionService;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;
import com.yunpos.utils.XMLUtil;

/**
 * 
 * 功能描述：支付宝支付业务类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
@Service
public class AlipayService {
	private final static Logger log = LoggerFactory.getLogger(AlipayService.class);
	public static final String FLAG_NAME = "isSuccess";
	public static final String MSG_NAME = "msg";

	@Autowired
	private SysTransactionService sysTransactionService;
	@Autowired
	private SysAlipayConfigService sysAlipayConfigService;

	/**
	 * 支付宝支付同步方法业务逻辑处理
	 * 
	 * @param param
	 * @return
	 */
	public Message pay(AlipayScanPayReqData payReqData,SysAlipayConfig sysAlipayConfig) {
		log.info("支付宝条码支付请求参数:" + payReqData.toMap().toString());
		try {// 建立请求
			Map<String,String> payMap = new HashMap<>();
			payMap.put("key", sysAlipayConfig.getKey());
			String responseXml = AlipaySubmit.buildRequest("", "", payReqData.toMap(),payMap);
			Map<String, String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果：" + result.toString());
			if ("T".equalsIgnoreCase(result.get("is_success"))) {// T代表成功
				if (result.get("result_code").equals("ORDER_SUCCESS_PAY_SUCCESS")) {
					PayResData payResData = new PayResData(PayChannel.ALIPAY, result, payReqData.toMap());
					return new Message(ResultCode.SUCCESS.name(), "", "支付成功", payResData.toMap()); // 支付宝交易流水号
				} else {
					String detail_error_code = result.get("detail_error_code");
					String detail_error_des = result.get("detail_error_des");
					return new Message(ResultCode.FAIL.name(), detail_error_code, detail_error_des, null);
				}
			} else {
				String errorCode = result.get("error");
				return new Message(ResultCode.FAIL.name(), errorCode, Constant.getAlipayErrMsg(errorCode), null);
			}
		} catch (Exception e) {
			log.error("支付宝支会异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付异常!", null);
		}
	}

	/**
	 * 支付宝支付查询
	 * 
	 * @param param
	 * @return
	 */
	public Message query(AlipayQueryReqData alipayQueryReqData,SysAlipayConfig sysAlipayConfig) {
		// sParaTemp.put(PayConst.IT_B_PAY, AlipayConfig.pay_time_out);
		log.info("查询请求参数:" + alipayQueryReqData.toMap());
		try {// 建立请求
			Map<String,String> payMap = new HashMap<>();
			payMap.put("key", sysAlipayConfig.getKey());
			String responseXml = AlipaySubmit.buildRequest("", "", alipayQueryReqData.toMap(),payMap);
			Map<String, String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果：" + result.toString());
			if ("T".equalsIgnoreCase(result.get("is_success"))) {// T代表成功
				if (result.get("result_code").equals("SUCCESS")) {
					QueryResData queryResData = new QueryResData(PayChannel.ALIPAY, result, alipayQueryReqData.toMap());
					return new Message(ResultCode.SUCCESS.name(), "", "支付成功", queryResData.toMap()); // 支付宝交易流水号
				} else {
					String detail_error_code = result.get("detail_error_code");
					String detail_error_des = result.get("detail_error_des");
					return new Message(ResultCode.FAIL.name(), detail_error_code, detail_error_des, null);
				}
			} else {
				String error = result.get("error");
				return new Message(ResultCode.FAIL.name(), error, Constant.getAlipayErrMsg(error), null);
			}
		} catch (Exception e) {
			log.error("查询异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "查询异常!", null);
		}
	}

	/**
	 * 支付宝退款
	 * 
	 * @param param
	 * @return
	 */
	public Message refund(AlipayRefundReqData alipayRefundReqData,SysTransaction sysTransaction) {
		// 把请求参数打包成数组

		log.info("支付宝条码支付请求参数:" + alipayRefundReqData.toMap());
		try {// 建立请求
			SysAlipayConfigWithBLOBs sysAlipayConfig = sysAlipayConfigService.findByMerchantNo(sysTransaction.getSerialNo());
			Map<String,String> payMap = new HashMap<>();
			payMap.put("key", sysAlipayConfig.getKey());
			String responseXml = AlipaySubmit.buildRequest("", "", alipayRefundReqData.toMap());
			Map<String, String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果：" + result.toString());
			if ("T".equalsIgnoreCase(result.get("is_success"))) {// T代表成功
				if (result.get("result_code").equals("SUCCESS")) {
					sysTransaction.setStatus(Byte.valueOf("3"));//已退款
					sysTransactionService.update(sysTransaction);
					RefundResData refundResData = new RefundResData(PayChannel.ALIPAY, result,alipayRefundReqData.toMap());
					return new Message(ResultCode.SUCCESS.name(), "", "退款成功", refundResData.toMap()); // 支付宝交易流水号
				} else {
					sysTransaction.setStatus(Byte.valueOf("5"));//退款失败
					sysTransactionService.update(sysTransaction);
					String detail_error_code = result.get("detail_error_code");
					String detail_error_des = result.get("detail_error_des");
					return new Message(ResultCode.FAIL.name(), detail_error_code, detail_error_des, null);
				}
			} else {
				String errorCode = result.get("error");
				return new Message(ResultCode.FAIL.name(), errorCode, Constant.getAlipayErrMsg(errorCode), null);
			}
		} catch (Exception e) {
			log.error("支付宝退款异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "查询异常!", null);
		}
	}

	/**
	 * 支付宝扫码预下单接口
	 * 
	 * @param alipayPrecreateReqData
	 * @return
	 */
	public Message preCreate(AlipayPrecreateReqData alipayPrecreateReqData,SysAlipayConfig sysAlipayConfig) {
		log.info("支付宝统一预下单请求参数:" + alipayPrecreateReqData.toMap());
		try {// 建立请求
			Map<String,String> payMap = new HashMap<>();
			payMap.put("key", sysAlipayConfig.getKey());
			String responseXml = AlipaySubmit.buildRequest("", "", alipayPrecreateReqData.toMap(),payMap);
			Map<String, String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果：" + result.toString());
			if ("T".equalsIgnoreCase(result.get("is_success"))) {// T代表成功
				if (result.get("result_code").equals("SUCCESS")) {
					PreCreateResData preCreateResData = new PreCreateResData(result);
					return new Message(ResultCode.SUCCESS.name(), "", "预下单成功", preCreateResData.toMap());
				} else {
					String detail_error_code = result.get("detail_error_code");
					String detail_error_des = result.get("detail_error_des");
					return new Message(ResultCode.FAIL.name(), detail_error_code, detail_error_des, null);
				}
			} else {
				String errorCode = result.get("error");
				return new Message(ResultCode.FAIL.name(), errorCode, Constant.getAlipayErrMsg(errorCode), null);
			}
		} catch (Exception e) {
			log.error("支付宝退款异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "查询异常!", null);
		}

	}

	/**
	 * 支付宝异条码、扫码支付步回调处理函数
	 * 
	 * @param orderNo
	 * @param isSuccess
	 * @param paymentDate
	 * @param resultMsg
	 * @return
	 */

	public Map<String, Object> notify(Map<String, String> params, boolean isSuccess, String resultMsg,String flag) {
		Map<String, Object> returnMap = Maps.newHashMap();
		String orderNo = params.get("out_trade_no");

		if (Strings.isNullOrEmpty(orderNo)) {
			String msg = "无法完成支付，支付接口的回调没有订单号信息！";
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		
		SysTransaction  sysTransaction = sysTransactionService.findByTransNum(orderNo);
		if (sysTransaction == null) {
			String msg = "无法完成支付，支付接口返回的订单号[" + orderNo + "]无效！";
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		if (sysTransaction.getStatus() == Byte.valueOf("2")) {
			String msg = "支付接口返回的订单流水[" + orderNo + "]已经完成支付，无需再次处理！";
			log.warn(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		if (isSuccess) {
			sysTransaction.setStatus(Byte.valueOf("2"));// 支付成功
			if(flag.equals("bar")){//条码支付
				sysTransaction.setTransCardNum(params.get("buyer_logon_id")); //买家支付宝账号
			}else if(flag.equals("scan")){//扫码支付
				sysTransaction.setTransCardNum(params.get("buyer_id")); //买家支付宝账号
			}
			sysTransaction.setTransPrice(Float.valueOf(params.get("total_fee"))); //实际交易金额
			sysTransactionService.update(sysTransaction);
			
		
			String msg = "订单[" + orderNo+ "]支付交易成功！";
			log.info(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		} else {
			sysTransaction.setStatus(Byte.valueOf("6"));// 付款失败
			sysTransactionService.update(sysTransaction);
			String msg = "支付接口返回的订单[" + orderNo+ "]支付失败！";
			log.info(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
	}

}
