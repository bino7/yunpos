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
	public static final String sdkVersion = "java sdk 1.0.1";

	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	// 是否使用异步线程的方式来上报API测速，默认为异步模式
	public static boolean useThreadToDoReport = true;
	public static String ip = "";
	public static String HttpsRequestClassName = "com.yunpos.payment.wxpay.common.HttpsRequest";

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
	
	
	
	//扫码支付相关配置
	public static String scan_notify_url = "";
	public static String scan_unifiedorder_api = "";
	public static String CLOSE_ORDER_API = "";
	

	static {
		ResourceBundle rb = ResourceBundle.getBundle("payconfig/wchatpay");	
		WechatPayConfig.useThreadToDoReport = Boolean.valueOf(rb.getString("useThreadToDoReport").trim());
		//API接口
		WechatPayConfig.PAY_API = rb.getString("PAY_API").trim();
		WechatPayConfig.PAY_QUERY_API = rb.getString("PAY_QUERY_API").trim();
		WechatPayConfig.REFUND_API = rb.getString("REFUND_API").trim();
		WechatPayConfig.REFUND_QUERY_API = rb.getString("REFUND_QUERY_API").trim();
		WechatPayConfig.REVERSE_API = rb.getString("REVERSE_API").trim();
		WechatPayConfig.DOWNLOAD_BILL_API = rb.getString("DOWNLOAD_BILL_API").trim();
		WechatPayConfig.REPORT_API = rb.getString("REPORT_API").trim();
		
		//扫码支付相关配置
		WechatPayConfig.scan_notify_url = rb.getString("scan_notify_url").trim();
		WechatPayConfig.scan_unifiedorder_api = rb.getString("scan_unifiedorder_api").trim();
		WechatPayConfig.CLOSE_ORDER_API = rb.getString("CLOSE_ORDER_API").trim();
		
	}

	private static String getDecryptString(String value) {
		return Strings.isNullOrEmpty(value) ? value : AESUtils.decrypt(AESUtils.key, value);
	}
}
