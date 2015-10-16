package com.yunpos.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SysWechatConfig {
    private Integer id;

    private String appId;

    private String mchId;
    
    private Byte mchType;

    private String parentMchId;

    private String certLocalPath;
   
    private String certPassword;
    
    private String merchantScanNotify;

    private String info;

    private String mark;

    private Byte status;

    private String merchantNo;
    
    //服务商
    public static Byte mchType_service = 0;
	//商户
    public static Byte mchType_mch = 1;

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
    
    

	public Byte getMchType() {
		return mchType;
	}

	public void setMchType(Byte mchType) {
		this.mchType = mchType;
	}

	public String getParentMchId() {
		return parentMchId;
	}

	public void setParentMchId(String parentMchId) {
		this.parentMchId = parentMchId;
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