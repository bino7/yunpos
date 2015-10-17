package com.yunpos.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.serializer.JsonDateSerializer;

/**
 * 
 * 功能描述：用户
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月7日
 * @author tiger_lin 修改日期：2015年9月7日
 *
 */
public class SysUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String STATUS_DISABLED = "0";

	private Integer id; // id

	private Integer orgId; // 组织结构id
	private String orgNo; // 组织结构号

	private String orgName; // 组织结构名

	private String userName; // 用户名

	private String phone; // 电话号码

	private String email; // 电子邮箱

	private String password; // 密码

	private String payPassword; // 支付密码

	private String nickname; // 昵称

	private String fullname; // 全名

	private Integer status; // 用户状态
	
	private String statusStr;  //页面显示

	private String salt; // 盐值

	private String description; // 描述

	private String source; // 源

	private Date lastLoginDatetime; // 最后登录时间

	private Date createdAt; // 创建时间

	private Integer createdBy; // 创建人

	private Date updatedAt; // 更新时间

	private Integer updatedBy; // 更新人
	
	private Integer	delete_status; //删除状态
	
	private String newPassword;//新密码与旧密码匹对

	// 下面表单接收数据使用，非实体数据库字段
	private String role; //

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname == null ? null : fullname.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status == null ? null : status;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getLastLoginDatetime() {
		return lastLoginDatetime;
	}

	public void setLastLoginDatetime(Date lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
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

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Integer getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(Integer delete_status) {
		this.delete_status = delete_status;
	}

	public String getStatusStr() {
	if (this.status!=null ){
		if(this.status==1){
			return "停用";
		}else if(this.status== 0){
			return "启用";
		}else{
			return null;
		}	
	}else{
		return "启用";
	}
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	

}