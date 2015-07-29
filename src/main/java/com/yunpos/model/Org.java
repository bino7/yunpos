package com.yunpos.model;

import java.util.Date;

public class Org {
	private Integer orgId;

	private String orgNo;

	private String orgName;

	private Integer orgParentId;

	private String orgParentName;

	private String orgParentNo;

	private Integer createUserId;

	private Date createDate;

	private Integer modifyUserId;

	private Date modifyDate;

	private Integer level;

	private Boolean isLeaf;

	private Boolean loaded;

	private Boolean expanded;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo == null ? null : orgNo.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public Integer getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(Integer orgParentId) {
		this.orgParentId = orgParentId;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName == null ? null : orgParentName
				.trim();
	}

	public String getOrgParentNo() {
		return orgParentNo;
	}

	public void setOrgParentNo(String orgParentNo) {
		this.orgParentNo = orgParentNo == null ? null : orgParentNo.trim();
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

}