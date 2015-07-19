package com.yunpos.model;

public class Privilege {
    private Integer privilegeid;

    private String privilegemaster;

    private Integer privilegemastervalue;

    private String privilegeaccess;

    private Integer privilegeaccessvalue;

    private Integer privilegeoperation;

    public Integer getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Integer privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String getPrivilegemaster() {
        return privilegemaster;
    }

    public void setPrivilegemaster(String privilegemaster) {
        this.privilegemaster = privilegemaster == null ? null : privilegemaster.trim();
    }

    public Integer getPrivilegemastervalue() {
        return privilegemastervalue;
    }

    public void setPrivilegemastervalue(Integer privilegemastervalue) {
        this.privilegemastervalue = privilegemastervalue;
    }

    public String getPrivilegeaccess() {
        return privilegeaccess;
    }

    public void setPrivilegeaccess(String privilegeaccess) {
        this.privilegeaccess = privilegeaccess == null ? null : privilegeaccess.trim();
    }

    public Integer getPrivilegeaccessvalue() {
        return privilegeaccessvalue;
    }

    public void setPrivilegeaccessvalue(Integer privilegeaccessvalue) {
        this.privilegeaccessvalue = privilegeaccessvalue;
    }

    public Integer getPrivilegeoperation() {
        return privilegeoperation;
    }

    public void setPrivilegeoperation(Integer privilegeoperation) {
        this.privilegeoperation = privilegeoperation;
    }
}