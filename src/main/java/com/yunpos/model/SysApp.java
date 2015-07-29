package com.yunpos.model;

public class SysApp {
	private Integer applicationId;

	private Integer applicationCode;

	private String applicationName;

	private String applicationDesc;

	private Integer showInMenu;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(Integer applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName == null ? null : applicationName
				.trim();
	}

	public String getApplicationDesc() {
		return applicationDesc;
	}

	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc == null ? null : applicationDesc
				.trim();
	}

	public Integer getShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(Integer showInMenu) {
		this.showInMenu = showInMenu;
	}
}