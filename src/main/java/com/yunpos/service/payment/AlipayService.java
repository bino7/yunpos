package com.yunpos.service.payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.yunpos.model.SysPayOrder;
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
import com.yunpos.service.SysPayOrderService;
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
	private SysPayOrderService sysPayOrderService;

	/**
	 * 支付宝支付同步方法业务逻辑处理
	 * 
	 * @param param
	 * @return
	 */
	public Message pay(AlipayScanPayReqData payReqData) {
		log.info("支付宝条码支付请求参数:" + payReqData.toMap().toString());
		try {// 建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", payReqData.toMap());
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
	public Message query(AlipayQueryReqData alipayQueryReqData) {
		// sParaTemp.put(PayConst.IT_B_PAY, AlipayConfig.pay_time_out);
		log.info("查询请求参数:" + alipayQueryReqData.toMap());
		try {// 建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", alipayQueryReqData.toMap());
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
	public Message refund(AlipayRefundReqData alipayRefundReqData) {
		// 把请求参数打包成数组

		log.info("支付宝条码支付请求参数:" + alipayRefundReqData.toMap());
		try {// 建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", alipayRefundReqData.toMap());
			Map<String, String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果：" + result.toString());
			if ("T".equalsIgnoreCase(result.get("is_success"))) {// T代表成功
				if (result.get("result_code").equals("SUCCESS")) {
					RefundResData refundResData = new RefundResData(PayChannel.ALIPAY, result,
							alipayRefundReqData.toMap());
					return new Message(ResultCode.SUCCESS.name(), "", "退款成功", refundResData.toMap()); // 支付宝交易流水号
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
	 * 支付宝扫码预下单接口
	 * 
	 * @param alipayPrecreateReqData
	 * @return
	 */
	public Message preCreate(AlipayPrecreateReqData alipayPrecreateReqData) {
		log.info("支付宝统一预下单请求参数:" + alipayPrecreateReqData.toMap());
		try {// 建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", alipayPrecreateReqData.toMap());
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
	 * 支付宝异步回调处理函数
	 * 
	 * @param orderNo
	 * @param isSuccess
	 * @param paymentDate
	 * @param resultMsg
	 * @return
	 */

	public Map<String, Object> notifyPayment(Map<String, String> params, boolean isSuccess, String resultMsg) {
		Map<String, Object> returnMap = Maps.newHashMap();
		String orderNo = params.get("out_trade_no");

		if (Strings.isNullOrEmpty(orderNo)) {
			String msg = "无法完成支付，支付接口的回调没有订单号信息！";
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		SysPayOrder sysPayOrder = sysPayOrderService.findByPayOrderNo(orderNo);
		if (sysPayOrder == null) {
			String msg = "无法完成支付，支付接口返回的订单号[" + orderNo + "]无效！";
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		if (sysPayOrder.getStatus() == Byte.valueOf("1")) {
			String msg = "支付接口返回的订单流水[" + sysPayOrder + "]已经完成支付，无需再次处理！";
			log.warn(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}

		if (isSuccess) {
			sysPayOrder.setStatus(Byte.valueOf("1"));// 支付成功
			sysPayOrder.setNotify_time(new Date());

			sysPayOrderService.update(sysPayOrder);

			String msg = "订单[" + sysPayOrder.getPayOrderNo() + "]支付交易成功！";
			log.info(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		} else {
			sysPayOrder.setStatus(Byte.valueOf("0"));// 支付失败
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);

			String msg = "支付接口返回的订单[" + sysPayOrder.getPayOrderNo() + "]支付失败！";
			log.info(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
	}

}
