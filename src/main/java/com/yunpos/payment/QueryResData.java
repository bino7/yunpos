package com.yunpos.payment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.payment.RefundResData.PayChannel;

/**
 * 
 * 功能描述：支付查询返回结果
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月20日
 * @author Devin_Yang 修改日期：2015年8月20日
 *
 */
public class QueryResData {
	private String dynamic_type = ""; // 1微信支付，2支付宝支付
	private String trans_type = ""; // 交易类型
	private String merchant_name = ""; // 商户名
	private String merchant_num = ""; // 商户号
	private String terminal_num = ""; // 终端号
	private String trans_card_num = ""; // 支付卡号
	private String trace_num = ""; // 交易流水号
	private String trans_time = ""; // 交易时间
	private String trans_amount = ""; // 实际交易金额
	private String total_fee = ""; // 交易金额

	// 支付返回类型数据转换
	public QueryResData(PayChannel channel, Map<String, String> resMap, Map<String, String> map) {
		if (channel.equals(PayChannel.WECHAT)) {// 微信
			//支付渠道返回信息
			this.trans_type = resMap.get("trade_type");
			this.terminal_num = resMap.get("device_info");
			this.trans_card_num = "";
			this.trace_num = resMap.get("out_trade_no");
			this.trans_time = resMap.get("time_end");
			this.trans_amount = resMap.get("cash_fee");
			this.total_fee = resMap.get("total_fee");
			//平台返回信息
			this.dynamic_type = "1";
			this.merchant_num = map.get("merchant_num");
			this.merchant_name = map.get("merchant_name");
			
		}
		if (channel.equals(PayChannel.ALIPAY)) {// 支付宝
			//支付渠道返回信息
			this.trans_card_num = "";
			this.trace_num = resMap.get("out_trade_no");
			this.trans_time = resMap.get("send_pay_date");
			this.trans_amount = resMap.get("total_fee");
			this.total_fee = resMap.get("total_fee");
			//平台返回信息
			this.dynamic_type = "2";
			this.trans_type = "";
			this.merchant_name =map.get("merchant_name");
			this.merchant_num = map.get("merchant_num");
			this.terminal_num = map.get("terminal_unique_no");
		}

	}

	public String getDynamic_type() {
		return dynamic_type;
	}

	public void setDynamic_type(String dynamic_type) {
		this.dynamic_type = dynamic_type;
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

	public String getTrans_card_num() {
		return trans_card_num;
	}

	public void setTrans_card_num(String trans_card_num) {
		this.trans_card_num = trans_card_num;
	}

	public String getTrace_num() {
		return trace_num;
	}

	public void setTrace_num(String trace_num) {
		this.trace_num = trace_num;
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
