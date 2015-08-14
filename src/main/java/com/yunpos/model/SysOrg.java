package com.yunpos.model;

import java.util.Date;

public class SysOrg {
    private Integer id;

    private String orgNo;

    private String orgName;

    private Integer orgParentId;

    private String orgParentName;

    private String orgParentNo;

    private Integer createUserId;

    private Date createDate;

    private Integer modifyUserId;

    private Date modifyDate;

    private Integer extLevel;

    private Boolean extIsLeaf;

    private Boolean extLoaded;

    private Boolean extExpanded;

    private Integer extParent;

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

    public Integer getExtLevel() {
        return extLevel;
    }

    public void setExtLevel(Integer extLevel) {
        this.extLevel = extLevel;
    }

    public Boolean getExtIsLeaf() {
        return extIsLeaf;
    }

    public void setExtIsLeaf(Boolean extIsLeaf) {
        this.extIsLeaf = extIsLeaf;
    }

    public Boolean getExtLoaded() {
        return extLoaded;
    }

    public void setExtLoaded(Boolean extLoaded) {
        this.extLoaded = extLoaded;
    }

    public Boolean getExtExpanded() {
        return extExpanded;
    }

    public void setExtExpanded(Boolean extExpanded) {
        this.extExpanded = extExpanded;
    }

    public Integer getExtParent() {
        return extParent;
    }

    public void setExtParent(Integer extParent) {
        this.extParent = extParent;
    }
}