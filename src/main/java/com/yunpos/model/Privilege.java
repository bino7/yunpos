package com.yunpos.model;

public class Privilege {
	private Integer privilegeId;

	private String privilegeMaster;

	private Integer privilegeMasterValue;

	private String privilegeAccess;

	private Integer privilegeAccessValue;

	private Integer privilegeOperation;

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeMaster() {
		return privilegeMaster;
	}

	public void setPrivilegeMaster(String privilegeMaster) {
		this.privilegeMaster = privilegeMaster == null ? null : privilegeMaster
				.trim();
	}

	public Integer getPrivilegeMasterValue() {
		return privilegeMasterValue;
	}

	public void setPrivilegeMasterValue(Integer privilegeMasterValue) {
		this.privilegeMasterValue = privilegeMasterValue;
	}

	public String getPrivilegeAccess() {
		return privilegeAccess;
	}

	public void setPrivilegeAccess(String privilegeAccess) {
		this.privilegeAccess = privilegeAccess == null ? null : privilegeAccess
				.trim();
	}

	public Integer getPrivilegeAccessValue() {
		return privilegeAccessValue;
	}

	public void setPrivilegeAccessValue(Integer privilegeAccessValue) {
		this.privilegeAccessValue = privilegeAccessValue;
	}

	public Integer getPrivilegeOperation() {
		return privilegeOperation;
	}

	public void setPrivilegeOperation(Integer privilegeOperation) {
		this.privilegeOperation = privilegeOperation;
	}
}