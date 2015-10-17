package com.yunpos.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.DateUtil;
import com.yunpos.utils.serializer.JsonDateSerializer;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年10月14日
 * @author Devin_Yang 修改日期：2015年10月14日
 * 
 */
public class SysOrg {
	private Integer id;
	
	private Integer sequence;

	private String orgNo;

	private String orgName;

	private Integer orgParentId;

	private String orgParentName;

	private String orgParentNo;

	private Integer level;

	private Integer createUserId;

	private Date createDate;

	private Integer modifyUserId;

	private Date modifyDate;
	
	private Integer  isLeaf;
	
	private List<Map<String,Object>> cells;
	
	
	private List children = new ArrayList();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.orgParentName = orgParentName == null ? null : orgParentName.trim();
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
	
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}
	
	public String getCreateDateStr() {
		if(null !=createDate){
			return DateUtil.format(createDate);
		}
		return "";
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
	@JsonSerialize(using = JsonDateSerializer.class)
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public List<Map<String, Object>> getCells() {
		return cells;
	}

	public void setCells(List<Map<String, Object>> cells) {
		this.cells = cells;
	}
	
	
	
	
	
	

}