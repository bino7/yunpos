package com.yunpos.model;

public class SysApp {
	private Integer id;

	private Integer applicationCode;

	private String applicationName;
	private String name;

	private String applicationDesc;

	private Integer showInMenu;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return applicationName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}