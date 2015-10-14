package com.yunpos.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SysAlipayConfig {
	private Integer id;

	private String sellerEmail;

	private String pid;

	private String alipaymrikey;

	private String merchanSynNotify;

	private String merchanAsynNotify;
	
    private String merchantBarNotify;

    private String merchantScanNotify;

	private String synNotify;

	private String asynNotify;

	private String devGetway;

	private String token;

	private String pagentId;

	private String mark;

	private String info;

	private Byte status;

	private String merchantNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getAlipaymrikey() {
		return alipaymrikey;
	}

	public void setAlipaymrikey(String alipaymrikey) {
		this.alipaymrikey = alipaymrikey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public String getPagentId() {
		return pagentId;
	}

	public void setPagentId(String pagentId) {
		this.pagentId = pagentId == null ? null : pagentId.trim();
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark == null ? null : mark.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info == null ? null : info.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo == null ? null : merchantNo.trim();
	}

	public String getMerchanSynNotify() {
		return merchanSynNotify;
	}

	public void setMerchanSynNotify(String merchanSynNotify) {
		this.merchanSynNotify = merchanSynNotify;
	}

	public String getMerchanAsynNotify() {
		return merchanAsynNotify;
	}

	public void setMerchanAsynNotify(String merchanAsynNotify) {
		this.merchanAsynNotify = merchanAsynNotify;
	}

	public String getSynNotify() {
		return synNotify;
	}

	public void setSynNotify(String synNotify) {
		this.synNotify = synNotify;
	}

	public String getAsynNotify() {
		return asynNotify;
	}

	public void setAsynNotify(String asynNotify) {
		this.asynNotify = asynNotify;
	}

	public String getDevGetway() {
		return devGetway;
	}

	public void setDevGetway(String devGetway) {
		this.devGetway = devGetway;
	}
	

	public String getMerchantBarNotify() {
		return merchantBarNotify;
	}

	public void setMerchantBarNotify(String merchantBarNotify) {
		this.merchantBarNotify = merchantBarNotify;
	}

	public String getMerchantScanNotify() {
		return merchantScanNotify;
	}

	public void setMerchantScanNotify(String merchantScanNotify) {
		this.merchantScanNotify = merchantScanNotify;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					map.put(field.getName(), obj);
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