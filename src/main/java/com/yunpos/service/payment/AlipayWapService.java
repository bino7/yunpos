package com.yunpos.service.payment;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.model.SysTransaction;
import com.yunpos.payment.alipay.model.AlipayWapPayReqData;
import com.yunpos.payment.alipay.util.AlipaySubmit;
import com.yunpos.service.SysTransactionService;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;

/**
 * 
 * 功能描述：支付宝手机wap支付service
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月24日
 * @author Devin_Yang 修改日期：2015年8月24日
 *
 */
@Service
public class AlipayWapService {
	private final static Logger log = LoggerFactory.getLogger(AlipayWapService.class);
	@Autowired
	private SysTransactionService sysTransactionService;

	/**
	 * 支付宝手机wap支付
	 * 
	 * @param params
	 * @param isSuccess
	 * @param resultMsg
	 */
	public String pay(AlipayWapPayReqData alipayWapPayReqData) {
		log.info("支付宝条码支付请求参数:" + alipayWapPayReqData.toMap().toString());
		String sHtmlText = "";
		try {// 建立请求
			sHtmlText = AlipaySubmit.buildRequest(alipayWapPayReqData.toMap(),"get", "确认");
		} catch (Exception e) {
			log.error("支付宝支会异常:", e);
		}
		return sHtmlText;
	}

	/**
	 * 支付宝手机wap支付同步返回处理
	 * 
	 * @param params
	 * @param isSuccess
	 * @param resultMsg
	 */
	public Message synWapPayment(Map<String, String> params, boolean isSuccess, String resultMsg) {
		String orderNo = params.get("out_trade_no");
		if(Strings.isNullOrEmpty(orderNo)){
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(), "返回信息错误", null);
		}
		
		SysTransaction  sysTransaction = sysTransactionService.findByTransNum(orderNo);
		if(sysTransaction == null){
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),"无法完成支付，支付接口返回的订单号["+orderNo+"]无效！" , null);
		}
		
		if(sysTransaction.getStatus() ==Byte.valueOf("2")){
			String msg = "支付接口返回的订单流水["+orderNo+"]已经完成支付，无需再次处理！"; 
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),msg , null);
		}
		
		if(isSuccess){
			sysTransaction.setStatus(Byte.valueOf("2"));// 支付成功
			sysTransaction.setTransCardNum(params.get("buyer_logon_id")); //买家支付宝账号
			sysTransaction.setTransPrice(Float.valueOf(params.get("total_fee"))); //实际交易金额
			sysTransactionService.update(sysTransaction);
			String msg = "订单["+orderNo+"]支付交易成功！";
			return new Message(ResultCode.SUCCESS.name(), "SUCCESS",msg , null);
		}else{
			sysTransaction.setStatus(Byte.valueOf("6"));// 付款失败
			sysTransactionService.update(sysTransaction);
			String msg = "支付接口返回的订单[" + orderNo+ "]支付失败！";
			
			return new Message(ResultCode.FAIL.name(), "支付失败",msg , null);
		}
	}

	/**
	 * 支付宝异步回调处理逻辑
	 * 
	 * @param params
	 * @param isSuccess
	 * @param resultMsg
	 */
	public Message asyWapPayment(Map<String, String> params, boolean isSuccess, String resultMsg) {
		String orderNo = params.get("out_trade_no");
		if(Strings.isNullOrEmpty(orderNo)){
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(), "返回信息错误", null);
		}
		
		SysTransaction  sysTransaction = sysTransactionService.findByTransNum(orderNo);
		if(sysTransaction == null){
			log.error("无法完成支付，支付接口返回的订单号["+orderNo+"]无效！");
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),"无法完成支付，支付接口返回的订单号["+orderNo+"]无效！" , null);
		}
		
		if(sysTransaction.getStatus() ==Byte.valueOf("2")){
			String msg = "支付接口返回的订单流水["+orderNo+"]已经完成支付，无需再次处理！"; 
			log.error(msg);
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),msg , null);
		}
		
		if(isSuccess){
			sysTransaction.setStatus(Byte.valueOf("2"));// 支付成功
			sysTransaction.setTransCardNum(params.get("buyer_logon_id")); //买家支付宝账号
			sysTransaction.setTransPrice(Float.valueOf(params.get("total_fee"))); //实际交易金额
			sysTransactionService.update(sysTransaction);
			String msg = "订单["+orderNo+"]支付交易成功！";
			return new Message(ResultCode.SUCCESS.name(), "SUCCESS",msg , null);
		}else{
			sysTransaction.setStatus(Byte.valueOf("6"));// 付款失败
			sysTransactionService.update(sysTransaction);
			String msg = "支付接口返回的订单[" + orderNo+ "]支付失败！";
			return new Message(ResultCode.FAIL.name(), "支付失败",msg , null);
		}
	}

}
