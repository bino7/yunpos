package com.yunpos.payment.alipay.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.payment.alipay.config.AlipayConfig;

/**
 * 
 * 功能描述：支付宝预下单请求参数封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月19日
 * @author Devin_Yang 修改日期：2015年8月19日
 *
 */
public class AlipayScanPayReqData {
	// 接口名称（必填）
	private String service = "";
	// 合作者身份ID（必填）
	private String partner = "";
	// 参数编码字符集（必填）
	private String _input_charset = "";
	// 签名（必填）
	private String sign_type = "";
	// 签名方式（必填）
	private String sign = "";

	private String notify_url = ""; // 服务器异步通知页面路径（非必填）
	private String alipay_ca_request = ""; // 签名类型 （非必填）

	// 商户网站唯一订单号（必填）
	private String out_trade_no = "";
	// 订单标题（必填）
	private String subject = "";
	// 订单业务类型（必填）
	private String product_code = "";
	// 订单金额（必填）
	private String total_fee = "";

	private String seller_id = ""; // 卖家支付宝用户号（非必填）
	private String seller_email = ""; // 卖家支付宝账号（非必填）
	private String buyer_id = ""; // 买家支付宝用户号（非必填）
	private String buyer_email = ""; // 买家支付宝账号（非必填）
	private String operator_type = ""; // 操作员类型（非必填）
	private String operator_id = ""; // 操作员号（非必填）
	private String body = ""; // 订单描述（非必填）
	private String show_url = ""; // 商品展示网址（非必填）
	private String currency = ""; // 订单金额币种（非必填）
	private String price = ""; // 商品单价（非必填）
	private String quantity = ""; // 商品数量（非必填）
	private String goods_detail = ""; // 商品明细（非必填）
	private String extend_params = ""; // 公用业务扩展信息（非必填）
	private String it_b_pay = ""; // 订单支付超时时间（非必填）
	private String royalty_type = ""; // 分账类型（非必填）
	private String royalty_parameters = ""; // 分账信息（非必填）
	private String channel_parameters = ""; // 渠道参数（非必填）
	// 动态 ID 类型（必填）
	private String dynamic_id_type = "";
	// 动态 ID（必填）
	private String dynamic_id = "";
	private String ref_ids = ""; // 关联 ID 集合（非必填）
	private String mcard_parameters = ""; // 预付卡相关参数（非必填）
	private String auth_no = ""; // 授权号（非必填）
	private String promo_params = ""; // 优惠参数（非必填）
	private String passback_parameters = "";// 业务透传参数（非必填）
	private String agreement_info = ""; // 协议信息（非必填）

	// 业务附加传递信息（非接口参数）
	private String pay_channel = "";
	private String terminal_unique_no = "";
	private String merchant_num = "";
	private String merchant_name = "";

	public AlipayScanPayReqData(String out_trade_no, String partner, String subject, String product_code,
			String total_fee, String dynamic_id,String extend_params) {
		// 必填选项
		setService(AlipayConfig.service);
		setPartner(partner);
		set_input_charset(AlipayConfig.input_charset);
		setSign_type(AlipayConfig.sign_type);
		setOut_trade_no(out_trade_no);
		setSubject(subject);
		setProduct_code(product_code);
		setTotal_fee(total_fee);
		setDynamic_id_type(DynamicIdType.bar_code.toString());
		setDynamic_id(dynamic_id);

		// 非必填选项
		setNotify_url(AlipayConfig.notify_url);
		setExtend_params(extend_params);//分润
	}

	public enum DynamicIdType {
		wave_code, // 声波
		bar_code; // 条码
	}

	public enum ProductCode {
		BARCODE_PAY_OFFLINE, // 条码支付
		SOUNDWAVE_PAY_OFFLINE, // 声波支付
		MEMBER_CARD_QR_OFFLINE, // 会员卡支付
		FUND_TRADE_FAST_PAY, // 预授权产品
		FINGERPRINT_FAST_PAY; // 指纹支付

	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getAlipay_ca_request() {
		return alipay_ca_request;
	}

	public void setAlipay_ca_request(String alipay_ca_request) {
		this.alipay_ca_request = alipay_ca_request;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getOperator_type() {
		return operator_type;
	}

	public void setOperator_type(String operator_type) {
		this.operator_type = operator_type;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(String extend_params) {
		this.extend_params = extend_params;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getRoyalty_type() {
		return royalty_type;
	}

	public void setRoyalty_type(String royalty_type) {
		this.royalty_type = royalty_type;
	}

	public String getRoyalty_parameters() {
		return royalty_parameters;
	}

	public void setRoyalty_parameters(String royalty_parameters) {
		this.royalty_parameters = royalty_parameters;
	}

	public String getChannel_parameters() {
		return channel_parameters;
	}

	public void setChannel_parameters(String channel_parameters) {
		this.channel_parameters = channel_parameters;
	}

	public String getDynamic_id_type() {
		return dynamic_id_type;
	}

	public void setDynamic_id_type(String dynamic_id_type) {
		this.dynamic_id_type = dynamic_id_type;
	}

	public String getDynamic_id() {
		return dynamic_id;
	}

	public void setDynamic_id(String dynamic_id) {
		this.dynamic_id = dynamic_id;
	}

	public String getRef_ids() {
		return ref_ids;
	}

	public void setRef_ids(String ref_ids) {
		this.ref_ids = ref_ids;
	}

	public String getMcard_parameters() {
		return mcard_parameters;
	}

	public void setMcard_parameters(String mcard_parameters) {
		this.mcard_parameters = mcard_parameters;
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}

	public String getPromo_params() {
		return promo_params;
	}

	public void setPromo_params(String promo_params) {
		this.promo_params = promo_params;
	}

	public String getPassback_parameters() {
		return passback_parameters;
	}

	public void setPassback_parameters(String passback_parameters) {
		this.passback_parameters = passback_parameters;
	}

	public String getAgreement_info() {
		return agreement_info;
	}

	public void setAgreement_info(String agreement_info) {
		this.agreement_info = agreement_info;
	}

	public String getPay_channel() {
		return pay_channel;
	}

	public void setPay_channel(String pay_channel) {
		this.pay_channel = pay_channel;
	}

	public String getTerminal_unique_no() {
		return terminal_unique_no;
	}

	public void setTerminal_unique_no(String terminal_unique_no) {
		this.terminal_unique_no = terminal_unique_no;
	}

	public String getMerchant_num() {
		return merchant_num;
	}

	public void setMerchant_num(String merchant_num) {
		this.merchant_num = merchant_num;
	}
	
	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null && !obj.equals("")) {
					map.put(field.getName(), (String) obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
