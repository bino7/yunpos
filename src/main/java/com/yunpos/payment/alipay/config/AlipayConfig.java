package com.yunpos.payment.alipay.config;

import java.util.ResourceBundle;

import com.google.common.base.Strings;
import com.yunpos.utils.AESUtils;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

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
	public static String cancel = "";
	public static String refund = "";
	public static String notify_url = "";
	public static String pay_time_out="";
	
	//支付宝手机wap支付
	public static String wap_return = "";
	public static String wap_notify_url = "";
	public static String wap_service="";
	public static String wap_show_url ="";
	// 商户的私钥
	public static String wap_private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANfZ77vXGVfN0+DgfQmfio9aEQVnft8LzW+/+tyjiU3XL18ZMUPeOZs0anD8Iuqs+2952CbDLBm0OoMKa7ZKFU6ZB4XXrtdIhVpxZClICUOaDBfN8S/A9N+RUwW/mtoqxK6tmGgWyz8A+8cY5oVjH3gHYrvfEnG04K887lX3X2ltAgMBAAECgYEAskc1g26wZt4Ant6rR3R3K9lW76DAoBaOcjsdRnjn4dvWK3SSRRzZdYBAXdtSN4PyE+uOBM30sbIzFaJYvnHHz+LaLPfFgtxC7mHEoNj9brBQnz2oL89TPjtSSI5pcN/Y+tATx8bhlzrWbhWo9z+4CihaBBXCBspTenOogex2a50CQQD7JYr4Gwa65/C9qHn9rC9h6TuNwzioXglJQ/JKS0XmXv62TT3KUVPw8az2VnojtNlAWpDxEsPTEzx771bwakRfAkEA3AXIX2NpGjjis5nFjvod7hX4UZFUHyweh88w93hIvJKy9tUBag/YgwJTxuuGplst3qBTc96ssXsFFQsO6dxFswJBAOrJikWwXnZVZ4am/oTcd998/6PkIg+k+eF4mAZEoLBhlpYvV9jQdz0FcsFIzx6ab4/uGxqEhHXLwfvLIOfZMNkCQDMRGIejQCK47002NJdR0B6RdNghvf+aMt/cpAXcIi26ctU9bR2b3enSYXV69v6njQbt+VCcb0KmRfCzZ1u6J2cCQQD3MMQce4+Y3Bj+IbZD7Zy9iBI6nbN1I5DEuqyF5ActUwAuwwrL3Zdqc9l5e6oDGudbXr17RKcly5jV8y58bYTL";
	// 支付宝的公钥，无需修改该值
	public static String wap_ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnvy68wcpDiNUZYhvwxWVQa2pQYkoXvJRcpp+T Fn9j6uQ6Je9U0bNlnfJ5EUPCL2BBnvge3RP5PPucs66IWpu9lz3ibCxuNqV/+cwp93STMMv/lRbZ j3ipFblIeBVHQ9DIuWN14zBM8MfC49wS1SvlF8Go3OYPbNLSZBlGll3a3wIDAQAB";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	public static String wap_sign_type ="RSA";
	
	
	//支付宝预下单参数
	public static String alipay_acquire_precreate = "";
	public static String alipay_acquire_precreate_notify_url = "";
	
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("payconfig/alipay");
		//获取加密配置信息
		AlipayConfig.partner = getDecryptString(rb.getString("partner").trim());
		AlipayConfig.key = getDecryptString(rb.getString("key").trim());
		AlipayConfig.sign_type = getDecryptString(rb.getString("sign_type").trim());
		//获取明文配置信息
		AlipayConfig.service = rb.getString("service").trim();
		AlipayConfig.query = rb.getString("query").trim();
		AlipayConfig.refund = rb.getString("refund").trim();
		AlipayConfig.cancel = rb.getString("cancel").trim();
		AlipayConfig.input_charset = rb.getString("input_charset").trim();
		AlipayConfig.log_path = rb.getString("log_path").trim();
		AlipayConfig.notify_url = rb.getString("notify_url").trim();
		AlipayConfig.pay_time_out = rb.getString("pay_time_out").trim();
		
		//支付宝手机wap支付
		AlipayConfig.wap_return = rb.getString("wap_return").trim();
		AlipayConfig.wap_notify_url = rb.getString("wap_notify_url").trim();
		AlipayConfig.wap_service = rb.getString("wap_service").trim();
		AlipayConfig.wap_show_url = rb.getString("show_url");
		
		//支付宝预下单请求接口
		AlipayConfig.alipay_acquire_precreate = rb.getString("alipay_acquire_precreate");
		AlipayConfig.alipay_acquire_precreate_notify_url = rb.getString("alipay_acquire_precreate_notify_url");
	}

	private static String getDecryptString(String value) {
		return  Strings.isNullOrEmpty(value)?value : AESUtils.decrypt(AESUtils.key, value);
	}
}
