package com.yunpos.model;

public class SysWechatConfigWithBLOBs extends SysWechatConfig {
	private String apiSecret;
	 
    private String appSecret;

    private String appKey;

    public String getAppSecret() {
        return appSecret;
    }
    
    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret == null ? null : apiSecret.trim();
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }
}