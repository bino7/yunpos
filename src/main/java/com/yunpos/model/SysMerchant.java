package com.yunpos.model;

import java.util.Date;

import com.yunpos.utils.jqgrid.GridRequest;

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

    private String serialNo;					//商户编号

    private String agentSerialNo;				//代理商编号

    private String companyName;					//公司名称

    private Integer industryTypeId;				//行业类型Id

    private String industryType;				//行业类型名称

    private String contactMan;					//联系人

    private String phone;						//联系人电话

    private String tel;							//联系人手机

    private String postcode;					//邮编

    private String prov;						//省

    private String city;						//市

    private String area;						//区

    private String address;						//地址

    private Byte auditStatus;					//审核状态，0：审核中，1：审核通过、2：回退、3：驳回

    private Date endTime;						//合同到期时间

    private String businessLicense;				//营业执照图片路径

    private String registerNumber;				//营业执照注册号

    private Byte identityType;					//证件类型，0：身份证，1：护照

    private String identityCardOn;				//证件正面照图片路径

    private String identityCardOff;				//证件反面照图片路径

    private String terminals;					//申请的终端，格式：银联,支付宝,微信支付（多个用,分割）

    private String serviceType;					//服务类型，格式：服务窗,公众号,收银台（多个用,分割）

    
    
 // 下面表单接收数据使用，非实体数据库字段
    private String userId;					//代理商用户ID
    
 	private String userName;				// 用户名
 	
 	private String nickname;				// 昵称
 	
	private String password;				// 密码

	private String newPassword;				// 新密码
	
    
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
    
    
}