package com.yunpos.service.payment;
/**
 * 
 * 功能描述：支付接口字段常量
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class PayConst {
	public static final String SERVICE = "service";					//（必填）接口名称
	public static final String PARTNER = "partner";					//（必填）合作者身份ID
	public static final String _INPUT_CHARSET= "_input_charset";	//（必填）字符编码
	public static final String SIGN_TYPE= "sign_type";				//（必填）签名类型
	public static final String SIGN = "sign";						//（必填）签名方式
	public static final String NOTIFY_URL = "notify_url";			//（选填）异步通知地址
	
	//业务参数
	public static final String OUT_TRADE_NO = "out_trade_no";		//（必填）商户订单号
	public static final String SUBJECT = "subject";					//（必填）订单标题
	public static final String PRODUCT_CODE ="product_code";		//（必填）订单业务类型
	public static final String TOTAL_FEE = "total_fee";				//（必填）总金额

	public static final String SELLER_ID = "seller_id";				//（选填）卖家支付宝用户号
	public static final String SELLER_EMAIL= "seller_email";		//（选填）卖家支付宝用户号
	public static final String BUYER_ID = "buyer_id";				//（选填）买家支付宝用户号
	public static final String BUYER_EMAIL = "buyer_email";			//（选填）buyer_email
	public static final String OPERATOR_TYPE = "operator_type";
	public static final String OPERATOR_ID = "operator_id";
	public static final String BODY = "body";
	public static final String SHOW_URL = "show_url";
	public static final String CURRENCY = "currency";
	public static final String PRICE = "price";
	public static final String DYNAMIC_ID_TYPE = "dynamic_id_type";
	public static final String DYNAMIC_ID = "dynamic_id";
	public static final String GOODS_DETAIL = "goods_detail";	
	public static final String IT_B_PAY = "it_b_pay";	
	
}
