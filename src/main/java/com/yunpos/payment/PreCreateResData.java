package com.yunpos.payment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 功能描述：支付宝统一预支付返回信息数据封装类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月27日
 * @author Devin_Yang 修改日期：2015年8月27日
 *
 */
public class PreCreateResData {

	private String trade_no = "";
	private String out_trade_no = "";
	private String voucher_type = "";
	private String qr_code = "";
	private String pic_url = "";
	private String small_pic_url = "";
	private String user_order_no = "";

	public PreCreateResData(Map<String, String> map, String user_order_no) {
		this.trade_no = map.get("trade_no");
		this.out_trade_no = map.get("out_trade_no");
		this.voucher_type = map.get("voucher_type");
		this.qr_code = map.get("qr_code");
		this.pic_url = map.get("pic_url");
		this.small_pic_url = map.get("small_pic_url");
		this.user_order_no = map.get("user_order_no");
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getVoucher_type() {
		return voucher_type;
	}

	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getSmall_pic_url() {
		return small_pic_url;
	}

	public void setSmall_pic_url(String small_pic_url) {
		this.small_pic_url = small_pic_url;
	}

	public String getUser_order_no() {
		return user_order_no;
	}

	public void setUser_order_no(String user_order_no) {
		this.user_order_no = user_order_no;
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
