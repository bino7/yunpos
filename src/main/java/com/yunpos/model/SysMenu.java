package com.yunpos.model;

public class SysMenu {
	private Integer id;

	private Integer menuNo;

	private Integer applicationCode;

	private Integer menuParentNo;

	private Integer menuOrder;

	private String menuName;
	private String name;

	private String menuUrl;

	private Integer isVisible;

	private Integer isLeaf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(Integer menuNo) {
		this.menuNo = menuNo;
	}

	public Integer getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(Integer applicationCode) {
		this.applicationCode = applicationCode;
	}

	public Integer getMenuParentNo() {
		return menuParentNo;
	}

	public void setMenuParentNo(Integer menuParentNo) {
		this.menuParentNo = menuParentNo;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return menuName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}