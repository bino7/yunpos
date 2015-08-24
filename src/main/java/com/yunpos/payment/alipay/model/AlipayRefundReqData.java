package com.yunpos.payment.alipay.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.payment.alipay.config.AlipayConfig;

/**
 * 
 * 功能描述：退款请求参数封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月19日
 * @author Devin_Yang 修改日期：2015年8月19日
 *
 */
public class AlipayRefundReqData {

	// 接口参数说明详细请查看官方文档
	private String service = ""; // 接口名称（必填）
	private String partner = ""; // 合作者身份ID（必填）
	private String _input_charset = ""; // 参数编码字符集（必填）
	private String sign_type = ""; // 签名方式（必填）
	private String sign = ""; // 签名（必填）
	private String alipay_ca_request = ""; // 签名类型 （非必填）

	private String out_trade_no = ""; // 商户网站唯一订单号（必填）
	private String refund_amount = ""; // 退款金额（必填）
	private String trade_no = ""; // 支付宝交易号（非必填）
	private String out_request_no = ""; // 商户退款请求单号（非必填）
	private String operator_type = ""; // 操作员类型（非必填）
	private String operator_id = ""; // 操作员号 （非必填）
	private String refund_reason = ""; // 退款原因（非必填）
	private String ref_ids = ""; // 业务关联 ID集合（非必填）

	// 业务附加传递信息（非接口参数）
	private String pay_channel = "";
	private String terminal_unique_no = "";
	private String merchant_num = "";

	public AlipayRefundReqData(String partner, String out_trade_no, String refund_amount) {
		// 接口名称
		setService(AlipayConfig.refund);
		// 合作者身份ID
		setPartner(partner);
		// 参数编码字符集
		set_input_charset(AlipayConfig.input_charset);
		// 签名方式
		setSign_type(AlipayConfig.sign_type);
		// 商户网站唯一订单号
		setOut_trade_no(out_trade_no);
		// 退款金额（必填）
		setRefund_amount(refund_amount);
		// 支付宝交易号（非必填）
		// setTrade_no(trade_no);
		// 商户退款请求单号（非必填）
		// setOut_request_no(out_request_no);
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

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
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

	public String getRefund_reason() {
		return refund_reason;
	}

	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}

	public String getRef_ids() {
		return ref_ids;
	}

	public void setRef_ids(String ref_ids) {
		this.ref_ids = ref_ids;
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
