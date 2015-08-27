package com.yunpos.payment.wxpay.config;

import java.util.ResourceBundle;

import com.google.common.base.Strings;
import com.yunpos.utils.AESUtils;

/**
 * 
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月17日
 * @author Devin_Yang 修改日期：2015年8月17日
 *
 */

public class WechatPayConfig {

	// sdk的版本号
	private static final String sdkVersion = "java sdk 1.0.1";

	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	@SuppressWarnings("unused")
	private static String key = "";

	// 微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "";

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "";

	// 受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	// HTTPS证书的本地路径
	private static String certLocalPath = "";

	// HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = "";

	// 是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	// 机器IP
	public static String ip = "";

	// 以下是几个API的路径：
	// 1）被扫支付API
	public static String PAY_API = "";

	// 2）被扫支付查询API
	public static String PAY_QUERY_API = "";

	// 3）退款API
	public static String REFUND_API = "";

	// 4）退款查询API
	public static String REFUND_QUERY_API = "";

	// 5）撤销API
	public static String REVERSE_API = "";

	// 6）下载对账单API
	public static String DOWNLOAD_BILL_API = "";

	// 7) 统计上报API
	public static String REPORT_API = "";

	static {
		ResourceBundle rb = ResourceBundle.getBundle("payconfig/wchatpay");
		// 获取加密配置信息
		WechatPayConfig.key = getDecryptString(rb.getString("key").trim());
		WechatPayConfig.appID = getDecryptString(rb.getString("appID").trim());
		WechatPayConfig.mchID = getDecryptString(rb.getString("mchID").trim());
		// 获取明文配置信息
		
		WechatPayConfig.certPassword = getDecryptString(rb.getString("certPassword").trim());
		WechatPayConfig.ip = getDecryptString(rb.getString("ip").trim());
		
		WechatPayConfig.useThreadToDoReport = Boolean.valueOf(rb.getString("useThreadToDoReport").trim());
		WechatPayConfig.certLocalPath = rb.getString("certLocalPath").trim();
		//API接口
		WechatPayConfig.PAY_API = rb.getString("PAY_API").trim();
		WechatPayConfig.PAY_QUERY_API = rb.getString("PAY_QUERY_API").trim();
		WechatPayConfig.REFUND_API = rb.getString("REFUND_API").trim();
		WechatPayConfig.REFUND_QUERY_API = rb.getString("REFUND_QUERY_API").trim();
		WechatPayConfig.REVERSE_API = rb.getString("REVERSE_API").trim();
		WechatPayConfig.DOWNLOAD_BILL_API = rb.getString("DOWNLOAD_BILL_API").trim();
		WechatPayConfig.REPORT_API = rb.getString("REPORT_API").trim();

	}

	private static String getDecryptString(String value) {
		return Strings.isNullOrEmpty(value) ? value : AESUtils.decrypt(AESUtils.key, value);
	}
}
