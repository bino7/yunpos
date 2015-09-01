package com.yunpos.payment.alipayV2.config;

import java.util.ResourceBundle;

import com.google.common.base.Strings;
import com.yunpos.utils.AESUtils;

/**
 * 
 * 功能描述：支付宝线下支付V2配置信息
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月31日
 * @author Devin_Yang 修改日期：2015年8月31日
 *
 */

public class AlipayV2Config {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "";
	// 商户的私钥
	public static String key = "";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "MD5";
	public static String service = "alipay.acquire.createandpay";
	public static String query = "";
	public static String refund = "";
	public static String notify_url = "";
	public static String pay_time_out="";
	
	//支付宝手机wap支付
	public static String wap_return = "";
	public static String wap_notify_url = "";
	public static String wap_service="";
	public static String show_url ="";
	
	
	//支付宝预下单参数
	public static String alipay_acquire_precreate = "";
	public static String alipay_acquire_precreate_notify_url = "";
	
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("payconfig/alipay");
		//获取加密配置信息
		AlipayV2Config.partner = getDecryptString(rb.getString("partner").trim());
		AlipayV2Config.key = getDecryptString(rb.getString("key").trim());
		AlipayV2Config.sign_type = getDecryptString(rb.getString("sign_type").trim());
		//获取明文配置信息
		AlipayV2Config.service = rb.getString("service").trim();
		AlipayV2Config.query = rb.getString("query").trim();
		AlipayV2Config.refund = rb.getString("refund").trim();
		AlipayV2Config.input_charset = rb.getString("input_charset").trim();
		AlipayV2Config.log_path = rb.getString("log_path").trim();
		AlipayV2Config.notify_url = rb.getString("notify_url").trim();
		AlipayV2Config.pay_time_out = rb.getString("pay_time_out").trim();
		
		//支付宝手机wap支付
		AlipayV2Config.wap_return = rb.getString("wap_return").trim();
		AlipayV2Config.wap_notify_url = rb.getString("wap_notify_url").trim();
		AlipayV2Config.wap_service = rb.getString("wap_service").trim();
		AlipayV2Config.show_url = rb.getString("show_url");
		
		//支付宝预下单请求接口
		AlipayV2Config.alipay_acquire_precreate = rb.getString("alipay_acquire_precreate");
		AlipayV2Config.alipay_acquire_precreate_notify_url = rb.getString("alipay_acquire_precreate_notify_url");
	}

	private static String getDecryptString(String value) {
		return  Strings.isNullOrEmpty(value)?value : AESUtils.decrypt(AESUtils.key, value);
	}
}
