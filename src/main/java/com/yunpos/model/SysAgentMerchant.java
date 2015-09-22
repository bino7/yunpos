package com.yunpos.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.serializer.JsonDateSerializer;

/**
 * 代理商
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月1日
 * @author tiger_lin 修改日期：2015年9月1日
 *
 */
public class SysAgentMerchant  extends GridRequest {
    private Integer id;					//主键ID

    private Integer baseUserId;			//关联的基础用户信息ID（关联sys_user表）

    private String agentSerialNo;		//代理商编号

    private String companyName;			//公司名称

    private String businessLicense;		//营业执照图片路径

    private String registerNumber;		//营业执照注册号

    private String identityCard;		//法人/经营者身份证图片路径

    private String contactMan;			//联系人

    private String phone;				//联系人电话

    private String tel;					//联系人手机

    private String prov;				//省

    private String city;				//市

    private String area;				//地区

    private String address;				//详细地址

    private int auditStatus;			//审核状态 0：审核中，1：未审核 ，2：审核通过、3：回退、4：驳回
    
    private String auditOpinion;		//审核意见
    
    private int status;					//代理商状态 0停用 1启用

    private Date endTime;				//合同到期时间
    
 // 下面表单接收数据使用，非实体数据库字段
    private String userId;					//代理商用户ID
    
 	private String userName;				// 用户名
 	
 	private String nickname;				// 昵称
 	
	private String password;				// 密码

	private String newPassword;				// 新密码
	
	private Date createdAt;					// 创建时间

	private Integer createdBy;				// 创建人
	
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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
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

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
    
	
}