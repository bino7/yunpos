package com.yunpos.payment.alipay.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.payment.alipay.config.AlipayConfig;

/**
 * 
 * 功能描述：支付宝手机wap支付请求数据封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月19日
 * @author Devin_Yang 修改日期：2015年8月19日
 *
 */
public class AlipayWapPayReqData {
	/////////////////// 基本参数///////////////////////
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
	private String return_url = ""; // 页面跳转同步通知页面路径（非必填）

	/////////////////// 业务参数///////////////////////
	// 商户网站唯一订单号（必填）
	private String out_trade_no = "";
	// 订单标题（必填）
	private String subject = "";
	// 订单金额（必填）
	private String total_fee = "";
	// 卖家支付宝用户号（必填）
	private String seller_id = "";
	// 支付类型（必填）
	private String payment_type = "1";

	private String body = ""; // 订单描述（非必填）
	private String show_url = ""; // 商品展示网址（非必填）
	private String it_b_pay = ""; // 订单支付超时时间（非必填）
	private String extern_token = ""; // 钱包token（非必填）
	private String otherfee = ""; // 航旅订单其它费用（非必填）
	private String airticket = ""; // 航旅订单金额（非必填）

	// 业务附加传递信息（非接口参数）
	private String pay_channel = "";
	private String terminal_unique_no = "";
	private String merchant_num = "";
	private String merchant_name = "";

	public AlipayWapPayReqData(String out_trade_no, String partner, String subject,
			String total_fee, String seller_id) {
		// 必填选项
		setService(AlipayConfig.wap_service);
		setPartner(partner);
		setSeller_id(seller_id);
		set_input_charset(AlipayConfig.input_charset);
		setPayment_type("1");
		setSign_type(AlipayConfig.wap_sign_type);
		setOut_trade_no(out_trade_no);
		setSubject(subject);
		setTotal_fee(total_fee);
		setShow_url(AlipayConfig.wap_show_url);
		
		// 非必填选项
		setBody(subject);
		setNotify_url(AlipayConfig.wap_notify_url);
		setReturn_url(AlipayConfig.wap_return);
		
//		sParaTemp.put("it_b_pay", it_b_pay);
//		sParaTemp.put("extern_token", extern_token);
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

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
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

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
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

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public String getExtern_token() {
		return extern_token;
	}

	public void setExtern_token(String extern_token) {
		this.extern_token = extern_token;
	}

	public String getOtherfee() {
		return otherfee;
	}

	public void setOtherfee(String otherfee) {
		this.otherfee = otherfee;
	}

	public String getAirticket() {
		return airticket;
	}

	public void setAirticket(String airticket) {
		this.airticket = airticket;
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
