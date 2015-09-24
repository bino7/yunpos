package com.yunpos.model;

public class SysPay {
    private Integer id;

    private String payName;

    private String payDes;

    private Integer open;
    private String openStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public String getPayDes() {
        return payDes;
    }

    public void setPayDes(String payDes) {
        this.payDes = payDes == null ? null : payDes.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

	public String getOpenStr() {
		if(open==0){
			return "停用";
		}else{
			return "启用";
		}
		
	}

	public void setOpenStr(String openStr) {
		this.openStr = openStr;
	}
    
    
}