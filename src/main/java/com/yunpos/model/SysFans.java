package com.yunpos.model;

import java.util.Date;

public class SysFans {
    private Integer id;

    private Byte subscribeFlag;

    private String serialNo;

    private String wxalipayId;

    private Byte sourceType;

    private String openId;

    private String nickName;

    private String trueName;

    private Byte sex;

    private String prov;

    private String city;

    private String area;

    private String address;

    private String zip;

    private String headimgurl;

    private Date subscribeTime;

    private Date unSubscribeTime;

    private String appid_userId;

    private String unionid;

    private String remark;

    private Byte userType;

    private String userStatus;

    private String firmName;

    private Byte certType;

    private String certNo;

    private String phone;

    private String mobile;

    private String is_certified;

    private String is_id_auth;

    private String is_mobile_auth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSubscribeFlag() {
        return subscribeFlag;
    }

    public void setSubscribeFlag(Byte subscribeFlag) {
        this.subscribeFlag = subscribeFlag;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getWxalipayId() {
        return wxalipayId;
    }

    public void setWxalipayId(String wxalipayId) {
        this.wxalipayId = wxalipayId == null ? null : wxalipayId.trim();
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getSex() {
    	if(this.sex==1){
    		return "男";
    	}else{
    		return "女";
    	}
    	
       
    }

    public void setSex(Byte sex) {
    
        this.sex = sex;
    }

   
    
    
    
    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getUnSubscribeTime() {
        return unSubscribeTime;
    }

    public void setUnSubscribeTime(Date unSubscribeTime) {
        this.unSubscribeTime = unSubscribeTime;
    }

    public String getAppid_userId() {
        return appid_userId;
    }

    public void setAppid_userId(String appid_userId) {
        this.appid_userId = appid_userId == null ? null : appid_userId.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName == null ? null : firmName.trim();
    }

    public Byte getCertType() {
        return certType;
    }

    public void setCertType(Byte certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getIs_certified() {
        return is_certified;
    }

    public void setIs_certified(String is_certified) {
        this.is_certified = is_certified == null ? null : is_certified.trim();
    }

    public String getIs_id_auth() {
        return is_id_auth;
    }

    public void setIs_id_auth(String is_id_auth) {
        this.is_id_auth = is_id_auth == null ? null : is_id_auth.trim();
    }

    public String getIs_mobile_auth() {
        return is_mobile_auth;
    }

    public void setIs_mobile_auth(String is_mobile_auth) {
        this.is_mobile_auth = is_mobile_auth == null ? null : is_mobile_auth.trim();
    }
}