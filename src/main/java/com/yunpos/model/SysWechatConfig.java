package com.yunpos.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SysWechatConfig {
    private Integer id;

    private String appId;

    private String mchId;

    private String certLocalPath;

    private String certPassword;

    private String info;

    private String mark;

    private Byte status;

    private String merchantNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getCertLocalPath() {
        return certLocalPath;
    }

    public void setCertLocalPath(String certLocalPath) {
        this.certLocalPath = certLocalPath == null ? null : certLocalPath.trim();
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword == null ? null : certPassword.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
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
    
    public Map<String, Object> toMap() {
  		Map<String, Object> map = new HashMap<String, Object>();
  		Field[] fields = this.getClass().getDeclaredFields();
  		for (Field field : fields) {
  			Object obj;
  			try {
  				obj = field.get(this);
  				if (obj != null) {
  					map.put(field.getName(),  obj);
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