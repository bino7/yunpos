package com.yunpos.model;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * 功能描述：用户登录信息（如果需要其他信息在此基础上扩展）
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月19日
 * @author Devin_Yang 修改日期：2015年7月19日
 *
 */
public class UserPrincipal implements Serializable,Comparable<UserPrincipal>{
	
	private static final long serialVersionUID = 1L;
	private Long id;					//用户Id
	private String name = "";			//用户名称
	private String email = "";			//email
	private String empNo = "";			//员工号
	private Long departmentId;
	private String departmentName = "";
	private String departmentPath = "";
	private Long loginLogId;
	private String clientIp = "";
	
	
	public UserPrincipal(SysUser sysUser, UsernamePasswordToken token){
		this.id = Long.valueOf(sysUser.getId().toString());
		this.name = sysUser.getUserName();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentPath() {
		return departmentPath;
	}

	public void setDepartmentPath(String departmentPath) {
		this.departmentPath = departmentPath;
	}

	public Long getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(Long loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", name=" + name + ", email="
				+ email + ", empNo=" + empNo + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ",loginLogId="
				+ loginLogId + "]";
	}

	@Override
	public int compareTo(UserPrincipal o) {
		return this.id.compareTo(o.getId());
	}

}
