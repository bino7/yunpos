package com.yunpos.payment.alipay.util;

import java.util.ResourceBundle;

/**
 * 
 * 功能描述：异常常量工具类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class Constant {
	
	private static ResourceBundle err_msg_alipay = null;
	private static ResourceBundle err_msg_wxpay = null;
	private static ResourceBundle alipay_trade_status = null;
	
	/**
	 * 获取支付异常信息
	 * @param errorCode
	 * @return
	 */
	public static String getAlipayErrMsg(String errorCode){
		if(null == err_msg_alipay){
			err_msg_alipay = ResourceBundle.getBundle("paymsg/alipay_error_msg");
		}
		return err_msg_alipay.containsKey(errorCode)?err_msg_alipay.getString(errorCode):"";
	}
	
	/**
	 * 获取微信支付异常信息
	 * @param errorCode
	 * @return
	 */
	public static String getWechatPayErrMsg(String errorCode){
		if(null == err_msg_wxpay){
			err_msg_wxpay = ResourceBundle.getBundle("paymsg/wxpay_error_msg");
		}
		return err_msg_wxpay.containsKey(errorCode)?err_msg_wxpay.getString(errorCode):"";
	}
	
	public static String getAlipayTradeStatus(String errorCode){
		if(null == alipay_trade_status){
			alipay_trade_status = ResourceBundle.getBundle("paymsg/alipay_trade_state");
		}
		return alipay_trade_status.containsKey(errorCode)?alipay_trade_status.getString(errorCode):"";
	}

}
