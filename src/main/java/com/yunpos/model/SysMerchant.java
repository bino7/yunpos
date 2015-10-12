package com.yunpos.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.serializer.JsonDateSerializer;

/**
 * 
 * 功能描述：商户
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月7日
 * @author tiger_lin 修改日期：2015年9月7日
 *
 */

public class SysMerchant extends GridRequest{

	    private Integer id;							//商户ID

	    private Integer baseUserId;					//关联的基础用户Id（关联sys_user表）

	    private Integer orgId;				//组织结构ID
	    
	    private String serialNo;					//商户编号

	    private String agentSerialNo;				//代理商编号
	    
	    private String md5Key;							//商户秘钥

	    private String companyName;					//公司名称

	    private Integer industryTypeId;				//行业类型Id     1 餐饮   2 KTV  3 美容美发  4 酒店 

	    private String industryType;				//行业类型名称

	    private String contactMan;					//联系人

	    private String phone;						//联系人电话

	    private String tel;							//联系人手机

	    private String postcode;					//邮编

	    private String prov;						//省

	    private String city;						//市

	    private String area;						//区

	    private String address;						//地址
	    
	    private Byte status;						//审核状态，0：停用，1：启用

	    private Byte auditStatus;					//审核状态，0：审核中，1：审核通过、2：回退、3：驳回

	    private Date endTime;						//合同到期时间

	    private String businessLicense;				//营业执照图片路径

	    private String registerNumber;				//营业执照注册号

	    private Byte identityType;					//证件类型，0：身份证，1：护照

	    private String identityCardOn;				//证件正面照图片路径

	    private String identityCardOff;				//证件反面照图片路径

	    private String terminals;					//申请的终端，格式：1银联,2支付宝,3微信支付（多个用,分割）

	    private String serviceType;					//服务类型，格式：服务窗,公众号,收银台（多个用,分割）
	    
	    private String serviceAlipay;				//服务类型，格式：服务窗,
	    
	    private String serviceWeixin;				//服务类型，格式：公众号,
	    
	    private String merchantName;

	    private Byte merchantType;

	    private String printName;

	    private String ranges;

	    private String email;

	    private String serviceTel;

	    private String webSite;

	    private String applyForBusiness;

	    private String auditMemo;

	    private Date updatedBy;

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
	    
	    

	 // 下面表单接收数据使用，非实体数据库字段
	    private String userId;					//代理商用户ID
	    
	 	private String userName;				// 用户名
	 	
	 	private String nickname;				// 昵称
	 	
		private String password;				// 密码

		private String newPassword;				// 新密码
		
		private Date createdAt;					// 创建时间

		private Integer createdBy;				// 创建人
		
		private String description;				// 描述
		
		private String[] terminalsStr;					//申请的终端，格式：1银联,2支付宝,3微信支付（多个用,分割）
	    

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
	    	if(!Tools.isNullOrEmpty(industryTypeId)){
		    	switch(industryTypeId){
			    	case 1 :industryType = "餐饮" ;
			    		break;
			    	case 2 :industryType = "KTV" ;
			    		break;
			    	case 3 :industryType = "美容美发" ;
			    		break;
			    	case 4 :industryType = "酒店" ;
			    		break;
			    	default : industryType = "";
		    	}
	    	}
	        return industryType;
	    }

	    public void setIndustryType(String industryType) {
	        this.industryType = industryType == null ? null : industryType.trim();
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

	    public Byte getAuditStatus() {
	        return auditStatus;
	    }

	    public void setAuditStatus(Byte auditStatus) {
	        this.auditStatus = auditStatus;
	    }

	    @JsonSerialize(using = JsonDateSerializer.class)
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

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
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

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		 @JsonSerialize(using = JsonDateSerializer.class)
		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Integer getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}

		public String getMd5Key() {
			return md5Key;
		}

		public void setMd5Key(String md5Key) {
			this.md5Key = md5Key;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String[] getTerminalsStr() {
			if(!Tools.isNullOrEmpty(terminals)){
				terminalsStr = terminals.split(",");
			}
			return terminalsStr;
		}

		public void setTerminalsStr(String[] terminalsStr) {
			this.terminalsStr = terminalsStr;
		}

		public Byte getStatus() {
			return status;
		}

		public void setStatus(Byte status) {
			this.status = status;
		}

		public String getServiceAlipay() {
			return serviceAlipay;
		}

		public void setServiceAlipay(String serviceAlipay) {
			this.serviceAlipay = serviceAlipay;
		}

		public String getServiceWeixin() {
			return serviceWeixin;
		}

		public void setServiceWeixin(String serviceWeixin) {
			this.serviceWeixin = serviceWeixin;
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


	    public String getAuditMemo() {
	        return auditMemo;
	    }

	    public void setAuditMemo(String auditMemo) {
	        this.auditMemo = auditMemo == null ? null : auditMemo.trim();
	    }


	    public Date getUpdatedBy() {
	        return updatedBy;
	    }

	    public void setUpdatedBy(Date updatedBy) {
	        this.updatedBy = updatedBy;
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

		public Integer getOrgId() {
			return orgId;
		}

		public void setOrgId(Integer orgId) {
			this.orgId = orgId;
		}

		public void setRanges(String ranges) {
			this.ranges = ranges;
		}

	
}