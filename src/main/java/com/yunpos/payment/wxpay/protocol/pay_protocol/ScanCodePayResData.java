package com.yunpos.payment.wxpay.protocol.pay_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 功能描述：微信扫码统一下单返回参数封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月27日
 * @author Devin_Yang 修改日期：2015年8月27日
 *
 */
public class ScanCodePayResData {
	// 每个字段具体的意思请查看API文档
	// https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	private String trade_type = "";
	private String prepay_id = "";
	private String code_url = "";
	private String trace_num = "";

	public ScanCodePayResData(Map<String, String> map, Map<String, String> resMap) {
		this.trade_type = map.get("trade_type");
		this.prepay_id = map.get("prepay_id");
		this.code_url = map.get("code_url");
		this.trace_num = resMap.get("out_trade_no");
	}

	public String getTrace_num() {
		return trace_num;
	}

	public void setTrace_num(String trace_num) {
		this.trace_num = trace_num;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
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

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					map.put(field.getName(),(String) obj);
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
