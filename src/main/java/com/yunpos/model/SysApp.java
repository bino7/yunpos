package com.yunpos.model;

public class SysApp {
    private Integer applicationid;

    private Integer applicationcode;

    private String applicationname;

    private String applicationdesc;

    private Integer showinmenu;

    public Integer getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Integer applicationid) {
        this.applicationid = applicationid;
    }

    public Integer getApplicationcode() {
        return applicationcode;
    }

    public void setApplicationcode(Integer applicationcode) {
        this.applicationcode = applicationcode;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname == null ? null : applicationname.trim();
    }

    public String getApplicationdesc() {
        return applicationdesc;
    }

    public void setApplicationdesc(String applicationdesc) {
        this.applicationdesc = applicationdesc == null ? null : applicationdesc.trim();
    }

    public Integer getShowinmenu() {
        return showinmenu;
    }

    public void setShowinmenu(Integer showinmenu) {
        this.showinmenu = showinmenu;
    }
}