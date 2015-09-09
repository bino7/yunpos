package com.yunpos.payment.wxwap.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.payment.wxpay.common.DateUtil;

/**
 * User: rizenguo
 * Date: 2014/10/22
 * Time: 16:42
 */

/**
 * 被扫支付提交Post数据给到API之后，API会返回XML格式的数据，这个类用来装这些数据
 */
public class WapPayResData {

	private String trans_type = ""; // 交易类型
	private String merchant_name = ""; // 商户名
	private String merchant_num = ""; // 商户号
	private String terminal_num = ""; // 终端号
	private String trace_num = ""; // 交易流水号
	private String trans_time = ""; // 交易时间
	private String trans_amount = ""; // 实际交易金额
	private String total_fee = ""; // 交易金额
	private String prepay_id = ""; // 预支付交易会话标识
	private String code_url = ""; // 二维码链接

	public WapPayResData(Map<String, String> responseXml, Map<String, String> dtoMap) {
		// 支付渠道返回信息
		this.terminal_num = responseXml.get("device_info");
		this.trans_type = responseXml.get("trade_type");
		this.prepay_id = responseXml.get("prepay_id");
		this.code_url = responseXml.get("code_url");
		// 平台返回信息
		this.merchant_name = dtoMap.get("merchant_name");
		this.merchant_num = dtoMap.get("merchant_num");
		this.trace_num = dtoMap.get("trace_num");
		this.trans_time = DateUtil.getNow();
		this.total_fee = dtoMap.get("total_fee");
		this.trans_amount = dtoMap.get("total_fee");
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_num() {
		return merchant_num;
	}

	public void setMerchant_num(String merchant_num) {
		this.merchant_num = merchant_num;
	}

	public String getTerminal_num() {
		return terminal_num;
	}

	public void setTerminal_num(String terminal_num) {
		this.terminal_num = terminal_num;
	}

	public String getTrace_num() {
		return trace_num;
	}

	public void setTrace_num(String trace_num) {
		this.trace_num = trace_num;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	public String getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					if (field.getType() == int.class) {
						map.put(field.getName(), String.valueOf(obj));
					} else {
						map.put(field.getName(), (String) obj);
					}
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
