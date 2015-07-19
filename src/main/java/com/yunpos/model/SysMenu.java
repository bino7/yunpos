package com.yunpos.model;

public class SysMenu {
    private Integer menuid;

    private Integer menuno;

    private Integer applicationcode;

    private Integer menuparentno;

    private Integer menuorder;

    private String menuname;

    private String menuurl;

    private Integer isvisible;

    private Integer isleaf;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getMenuno() {
        return menuno;
    }

    public void setMenuno(Integer menuno) {
        this.menuno = menuno;
    }

    public Integer getApplicationcode() {
        return applicationcode;
    }

    public void setApplicationcode(Integer applicationcode) {
        this.applicationcode = applicationcode;
    }

    public Integer getMenuparentno() {
        return menuparentno;
    }

    public void setMenuparentno(Integer menuparentno) {
        this.menuparentno = menuparentno;
    }

    public Integer getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Integer menuorder) {
        this.menuorder = menuorder;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public Integer getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(Integer isvisible) {
        this.isvisible = isvisible;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }
}