package com.yunpos.model;

import java.util.Date;

public class SysMerchant {
    private Integer id;

    private Integer baseUserId;

    private String serialNo;

    private String agentSerialNo;

    private String md5Key;

    private String merchantName;

    private Byte merchantType;

    private String printName;

    private String companyName;

    private Integer industryTypeId;

    private String industryType;

    private String range;

    private String contactMan;

    private String phone;

    private String tel;

    private String postcode;

    private String prov;

    private String city;

    private String area;

    private String address;

    private String email;

    private String serviceTel;

    private String webSite;

    private String applyForBusiness;

    private Byte auditStatus;

    private String auditMemo;

    private Byte status;

    private Date endTime;

    private String businessLicense;

    private String registerNumber;

    private Byte identityType;

    private String identityCardOn;

    private String identityCardOff;

    private String terminals;

    private String serviceType;

    private Date createdBy;

    private Date updatedBy;

    private String serviceAlipay;

    private String serviceWeixin;

    private Float totalArea;

    private Integer totalEmployees;

    private Float totalIncomes;

    private String operatePhoto;

    private String identityName;

    private String identityNum;

    private Date identityBt;

    private Date identityEt;

    private String organizeBarCode;

    private String organizeBarCodePic;

    private Date organizeBarCodeBt;

    private Date organizeBarCodeEt;

    private String taxBarCode;

    private String taxBarCodePic;

    private Date taxBarCodeBt;

    private Date taxBarCodeEt;

    private Byte cleanType;

    private String openBankProv;

    private String openBankCity;

    private String openBank;

    private String openBankSub;

    private String openBankName;

    private String openBankAccount;

    private String bankCardOn;

    private String bankCardOff;

    private String openBankLicense;

    private String signAccount;

    private Byte openBookingCard;

    private Float industryRate;

    private String cappingMachine;

    private String weixinPayOnline;

    private String weixinPayOffline;

    private String aliPayOnline;

    private String aliPayOffline;
    
    private String userName;
    
    private String nickname;
    
    private String password;
    
    private String description;
    
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Integer baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getAgentSerialNo() {
        return agentSerialNo;
    }

    public void setAgentSerialNo(String agentSerialNo) {
        this.agentSerialNo = agentSerialNo == null ? null : agentSerialNo.trim();
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key == null ? null : md5Key.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Byte getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Byte merchantType) {
        this.merchantType = merchantType;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName == null ? null : printName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(Integer industryTypeId) {
        this.industryTypeId = industryTypeId;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range == null ? null : range.trim();
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan == null ? null : contactMan.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel == null ? null : serviceTel.trim();
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite == null ? null : webSite.trim();
    }

    public String getApplyForBusiness() {
        return applyForBusiness;
    }

    public void setApplyForBusiness(String applyForBusiness) {
        this.applyForBusiness = applyForBusiness == null ? null : applyForBusiness.trim();
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo == null ? null : auditMemo.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber == null ? null : registerNumber.trim();
    }

    public Byte getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Byte identityType) {
        this.identityType = identityType;
    }

    public String getIdentityCardOn() {
        return identityCardOn;
    }

    public void setIdentityCardOn(String identityCardOn) {
        this.identityCardOn = identityCardOn == null ? null : identityCardOn.trim();
    }

    public String getIdentityCardOff() {
        return identityCardOff;
    }

    public void setIdentityCardOff(String identityCardOff) {
        this.identityCardOff = identityCardOff == null ? null : identityCardOff.trim();
    }

    public String getTerminals() {
        return terminals;
    }

    public void setTerminals(String terminals) {
        this.terminals = terminals == null ? null : terminals.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public Date getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Date createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getServiceAlipay() {
        return serviceAlipay;
    }

    public void setServiceAlipay(String serviceAlipay) {
        this.serviceAlipay = serviceAlipay == null ? null : serviceAlipay.trim();
    }

    public String getServiceWeixin() {
        return serviceWeixin;
    }

    public void setServiceWeixin(String serviceWeixin) {
        this.serviceWeixin = serviceWeixin == null ? null : serviceWeixin.trim();
    }

    public Float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Float totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Float getTotalIncomes() {
        return totalIncomes;
    }

    public void setTotalIncomes(Float totalIncomes) {
        this.totalIncomes = totalIncomes;
    }

    public String getOperatePhoto() {
        return operatePhoto;
    }

    public void setOperatePhoto(String operatePhoto) {
        this.operatePhoto = operatePhoto == null ? null : operatePhoto.trim();
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName == null ? null : identityName.trim();
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum == null ? null : identityNum.trim();
    }

    public Date getIdentityBt() {
        return identityBt;
    }

    public void setIdentityBt(Date identityBt) {
        this.identityBt = identityBt;
    }

    public Date getIdentityEt() {
        return identityEt;
    }

    public void setIdentityEt(Date identityEt) {
        this.identityEt = identityEt;
    }

    public String getOrganizeBarCode() {
        return organizeBarCode;
    }

    public void setOrganizeBarCode(String organizeBarCode) {
        this.organizeBarCode = organizeBarCode == null ? null : organizeBarCode.trim();
    }

    public String getOrganizeBarCodePic() {
        return organizeBarCodePic;
    }

    public void setOrganizeBarCodePic(String organizeBarCodePic) {
        this.organizeBarCodePic = organizeBarCodePic == null ? null : organizeBarCodePic.trim();
    }

    public Date getOrganizeBarCodeBt() {
        return organizeBarCodeBt;
    }

    public void setOrganizeBarCodeBt(Date organizeBarCodeBt) {
        this.organizeBarCodeBt = organizeBarCodeBt;
    }

    public Date getOrganizeBarCodeEt() {
        return organizeBarCodeEt;
    }

    public void setOrganizeBarCodeEt(Date organizeBarCodeEt) {
        this.organizeBarCodeEt = organizeBarCodeEt;
    }

    public String getTaxBarCode() {
        return taxBarCode;
    }

    public void setTaxBarCode(String taxBarCode) {
        this.taxBarCode = taxBarCode == null ? null : taxBarCode.trim();
    }

    public String getTaxBarCodePic() {
        return taxBarCodePic;
    }

    public void setTaxBarCodePic(String taxBarCodePic) {
        this.taxBarCodePic = taxBarCodePic == null ? null : taxBarCodePic.trim();
    }

    public Date getTaxBarCodeBt() {
        return taxBarCodeBt;
    }

    public void setTaxBarCodeBt(Date taxBarCodeBt) {
        this.taxBarCodeBt = taxBarCodeBt;
    }

    public Date getTaxBarCodeEt() {
        return taxBarCodeEt;
    }

    public void setTaxBarCodeEt(Date taxBarCodeEt) {
        this.taxBarCodeEt = taxBarCodeEt;
    }

    public Byte getCleanType() {
        return cleanType;
    }

    public void setCleanType(Byte cleanType) {
        this.cleanType = cleanType;
    }

    public String getOpenBankProv() {
        return openBankProv;
    }

    public void setOpenBankProv(String openBankProv) {
        this.openBankProv = openBankProv == null ? null : openBankProv.trim();
    }

    public String getOpenBankCity() {
        return openBankCity;
    }

    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity == null ? null : openBankCity.trim();
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getOpenBankSub() {
        return openBankSub;
    }

    public void setOpenBankSub(String openBankSub) {
        this.openBankSub = openBankSub == null ? null : openBankSub.trim();
    }

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName == null ? null : openBankName.trim();
    }

    public String getOpenBankAccount() {
        return openBankAccount;
    }

    public void setOpenBankAccount(String openBankAccount) {
        this.openBankAccount = openBankAccount == null ? null : openBankAccount.trim();
    }

    public String getBankCardOn() {
        return bankCardOn;
    }

    public void setBankCardOn(String bankCardOn) {
        this.bankCardOn = bankCardOn == null ? null : bankCardOn.trim();
    }

    public String getBankCardOff() {
        return bankCardOff;
    }

    public void setBankCardOff(String bankCardOff) {
        this.bankCardOff = bankCardOff == null ? null : bankCardOff.trim();
    }

    public String getOpenBankLicense() {
        return openBankLicense;
    }

    public void setOpenBankLicense(String openBankLicense) {
        this.openBankLicense = openBankLicense == null ? null : openBankLicense.trim();
    }

    public String getSignAccount() {
        return signAccount;
    }

    public void setSignAccount(String signAccount) {
        this.signAccount = signAccount == null ? null : signAccount.trim();
    }

    public Byte getOpenBookingCard() {
        return openBookingCard;
    }

    public void setOpenBookingCard(Byte openBookingCard) {
        this.openBookingCard = openBookingCard;
    }

    public Float getIndustryRate() {
        return industryRate;
    }

    public void setIndustryRate(Float industryRate) {
        this.industryRate = industryRate;
    }

    public String getCappingMachine() {
        return cappingMachine;
    }

    public void setCappingMachine(String cappingMachine) {
        this.cappingMachine = cappingMachine == null ? null : cappingMachine.trim();
    }

    public String getWeixinPayOnline() {
        return weixinPayOnline;
    }

    public void setWeixinPayOnline(String weixinPayOnline) {
        this.weixinPayOnline = weixinPayOnline == null ? null : weixinPayOnline.trim();
    }

    public String getWeixinPayOffline() {
        return weixinPayOffline;
    }

    public void setWeixinPayOffline(String weixinPayOffline) {
        this.weixinPayOffline = weixinPayOffline == null ? null : weixinPayOffline.trim();
    }

    public String getAliPayOnline() {
        return aliPayOnline;
    }

    public void setAliPayOnline(String aliPayOnline) {
        this.aliPayOnline = aliPayOnline == null ? null : aliPayOnline.trim();
    }

    public String getAliPayOffline() {
        return aliPayOffline;
    }

    public void setAliPayOffline(String aliPayOffline) {
        this.aliPayOffline = aliPayOffline == null ? null : aliPayOffline.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}