package com.yunpos.service.payment;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.payment.alipay.model.AlipayWapPayReqData;
import com.yunpos.payment.alipay.util.AlipaySubmit;
import com.yunpos.service.SysPayOrderService;
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
	private SysPayOrderService sysPayOrderService;

	/**
	 * 支付宝手机wap支付
	 * 
	 * @param params
	 * @param isSuccess
	 * @param resultMsg
	 */
	public String pay(AlipayWapPayReqData alipayWapPayReqData) {
		log.info("支付宝条码支付请求参数:" + alipayWapPayReqData.toMap().toString());
		String sHtmlText ="";
		try {// 建立请求
			sHtmlText = AlipaySubmit.buildRequest(alipayWapPayReqData.toMap(),"get", "确认");
		} catch (Exception e) {
			log.error("支付宝支会异常:", e);
			//return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付异常!", null);
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
		
		SysPayOrder sysPayOrder = sysPayOrderService.findByPayOrderNo(orderNo);
		if(sysPayOrder == null){
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),"无法完成支付，支付接口返回的订单号["+orderNo+"]无效！" , null);
		}
		
		if(sysPayOrder.getStatus() ==Byte.valueOf("1")){
			String msg = "支付接口返回的订单流水["+sysPayOrder+"]已经完成支付，无需再次处理！"; 
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),msg , null);
		}
		
		if(isSuccess){
			sysPayOrder.setStatus(Byte.valueOf("1"));//支付成功
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);
			String msg = "订单["+sysPayOrder.getPayOrderNo()+"]支付交易成功！";
			return new Message(ResultCode.SUCCESS.name(), "SUCCESS",msg , null);
		}else{
			sysPayOrder.setStatus(Byte.valueOf("0"));//支付失败
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);
			
			String msg = "支付接口返回的订单["+sysPayOrder.getPayOrderNo()+"]支付失败！"; 
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
		
		SysPayOrder sysPayOrder = sysPayOrderService.findByPayOrderNo(orderNo);
		if(sysPayOrder == null){
			log.error("无法完成支付，支付接口返回的订单号["+orderNo+"]无效！");
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),"无法完成支付，支付接口返回的订单号["+orderNo+"]无效！" , null);
		}
		
		if(sysPayOrder.getStatus() ==Byte.valueOf("1")){
			String msg = "支付接口返回的订单流水["+sysPayOrder+"]已经完成支付，无需再次处理！"; 
			log.error(msg);
			return new Message(ResultCode.FAIL.name(), ErrorCode.ORDER_NOT_EXIST.name(),msg , null);
		}
		
		if(isSuccess){
			sysPayOrder.setStatus(Byte.valueOf("1"));//支付成功
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);
			String msg = "订单["+sysPayOrder.getPayOrderNo()+"]支付交易成功！";
			return new Message(ResultCode.SUCCESS.name(), "SUCCESS",msg , null);
		}else{
			sysPayOrder.setStatus(Byte.valueOf("0"));//支付失败
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);
			
			String msg = "支付接口返回的订单["+sysPayOrder.getPayOrderNo()+"]支付失败！"; 
			return new Message(ResultCode.FAIL.name(), "支付失败",msg , null);
		}
	}

}
