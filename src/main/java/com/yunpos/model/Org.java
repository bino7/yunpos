package com.yunpos.model;

import java.util.Date;

public class Org {
    private Integer orgid;

    private String orgno;

    private String orgname;

    private Integer orgparentid;

    private String orgparentname;

    private String orgparentno;

    private Integer createuserid;

    private Date createdate;

    private Integer modifyuserid;

    private Date modifydate;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno == null ? null : orgno.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public Integer getOrgparentid() {
        return orgparentid;
    }

    public void setOrgparentid(Integer orgparentid) {
        this.orgparentid = orgparentid;
    }

    public String getOrgparentname() {
        return orgparentname;
    }

    public void setOrgparentname(String orgparentname) {
        this.orgparentname = orgparentname == null ? null : orgparentname.trim();
    }

    public String getOrgparentno() {
        return orgparentno;
    }

    public void setOrgparentno(String orgparentno) {
        this.orgparentno = orgparentno == null ? null : orgparentno.trim();
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(Integer modifyuserid) {
        this.modifyuserid = modifyuserid;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}