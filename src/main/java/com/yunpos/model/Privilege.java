package com.yunpos.model;

public class Privilege {
    private Integer privilegeid;

    private String privilegemaster;	//	主体类型

    private Integer privilegemastervalue;	//主体对应ID

    private String privilegeaccess;	//资源类型

    private Integer privilegeaccessvalue; //资源ID

    private Integer privilegeoperation;
    
    
	public enum Privilegemaster{
		 ROLE,USER;
	}
	
	public enum Privilegeaccess{
		 SYSBUTTON,SYSAPP,SYSMENU;
	}

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