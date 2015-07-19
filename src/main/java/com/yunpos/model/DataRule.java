package com.yunpos.model;

public class DataRule {
    private Integer ruleid;

    private Integer datatype;

    private Integer userid;

    private Integer roleid;

    private String datarule;

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getDatarule() {
        return datarule;
    }

    public void setDatarule(String datarule) {
        this.datarule = datarule == null ? null : datarule.trim();
    }
}