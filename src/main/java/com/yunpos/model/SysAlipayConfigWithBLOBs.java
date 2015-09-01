package com.yunpos.model;

public class SysAlipayConfigWithBLOBs extends SysAlipayConfig {
    private String appPrivateKey;

    private String appPublicKey;

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey == null ? null : appPrivateKey.trim();
    }

    public String getAppPublicKey() {
        return appPublicKey;
    }

    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey == null ? null : appPublicKey.trim();
    }
}