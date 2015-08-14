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
import com.yunpos.payment.alipay.config.AlipayConfig;
import com.yunpos.payment.alipay.util.AlipaySubmit;
import com.yunpos.payment.alipay.util.Constant;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.Message.PayStatus;
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
	 * @param param
	 * @return
	 */
	public Message pay(PayParam param) {
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put(PayConst.SERVICE, AlipayConfig.service);
        sParaTemp.put(PayConst.PARTNER, AlipayConfig.partner);
        sParaTemp.put(PayConst._INPUT_CHARSET, AlipayConfig.input_charset);
        sParaTemp.put(PayConst.SIGN_TYPE, AlipayConfig.sign_type);
        sParaTemp.put(PayConst.PRODUCT_CODE, "BARCODE_PAY_OFFLINE");//条码支付
        
        sParaTemp.put(PayConst.NOTIFY_URL, AlipayConfig.notify_url);
        sParaTemp.put(PayConst.OUT_TRADE_NO, param.getOrderNo().trim());
        sParaTemp.put(PayConst.SUBJECT, param.getOrderTitle().trim());
        sParaTemp.put(PayConst.TOTAL_FEE, param.getPrice());
        sParaTemp.put(PayConst.DYNAMIC_ID_TYPE, "bar_code");
        sParaTemp.put(PayConst.DYNAMIC_ID, param.getBarCode());//条码
        //sParaTemp.put(PayConst.IT_B_PAY, AlipayConfig.pay_time_out);
        log.info("支付宝条码支付请求参数:"+sParaTemp.toString());
		try {//建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", sParaTemp);
			Map<String,String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			log.info("同步返回结果："+result.toString());
			if("T".equalsIgnoreCase(result.get("is_success"))){//T代表成功
				return new Message(PayStatus.REQUEST_SUCCESS,result.get("trade_no")); //支付宝交易流水号
			}else{
				String errorCode = result.get("error");
				return new Message(PayStatus.FAILD,Constant.getAlipayErrMsg(errorCode));
			}
		} catch (Exception e) {
			log.error("支付宝支会异常:",e);
			return new Message(PayStatus.FAILD,e.getMessage());
		}
	}
	
	

	/**
	 * 支付宝异步回调处理函数
	 * @param orderNo
	 * @param isSuccess
	 * @param paymentDate
	 * @param resultMsg
	 * @return
	 */

	public Map<String,Object> notifyPayment(Map<String, String> params,boolean isSuccess, String resultMsg){
		Map<String,Object> returnMap = Maps.newHashMap();
		String orderNo = params.get("out_trade_no");

		if(Strings.isNullOrEmpty(orderNo)){
			String msg = "无法完成支付，支付接口的回调没有订单号信息！"; 
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
		
		SysPayOrder sysPayOrder = sysPayOrderService.findByPayOrderNo(orderNo);
		if(sysPayOrder == null){
			String msg = "无法完成支付，支付接口返回的订单号["+orderNo+"]无效！"; 
			log.error(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
		
		if(sysPayOrder.getStatus() ==Byte.valueOf("1")){
			String msg = "支付接口返回的订单流水["+sysPayOrder+"]已经完成支付，无需再次处理！"; 
			log.warn(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
		
		if(isSuccess){
			sysPayOrder.setStatus(Byte.valueOf("1"));//支付成功
			sysPayOrder.setNotify_time(new Date());
			
			sysPayOrderService.update(sysPayOrder);
			
			String msg = "订单["+sysPayOrder.getPayOrderNo()+"]支付交易成功！";
			log.info(msg);
			returnMap.put(FLAG_NAME, true);
			returnMap.put(MSG_NAME,msg);
			return returnMap;
		}else{
			sysPayOrder.setStatus(Byte.valueOf("0"));//支付失败
			sysPayOrder.setNotify_time(new Date());
			sysPayOrderService.update(sysPayOrder);
			
			String msg = "支付接口返回的订单["+sysPayOrder.getPayOrderNo()+"]支付失败！"; 
			log.info(msg);
			returnMap.put(FLAG_NAME, false);
			returnMap.put(MSG_NAME, msg);
			return returnMap;
		}
	}


}
