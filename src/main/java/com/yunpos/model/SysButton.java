package com.yunpos.model;

public class SysButton {
    private Integer btnid;

    private String btnname;

    private Integer btnno;

    private String btnicon;

    private Integer menuno;

    private Integer initstatus;

    private Integer seqno;

    public Integer getBtnid() {
        return btnid;
    }

    public void setBtnid(Integer btnid) {
        this.btnid = btnid;
    }

    public String getBtnname() {
        return btnname;
    }

    public void setBtnname(String btnname) {
        this.btnname = btnname == null ? null : btnname.trim();
    }

    public Integer getBtnno() {
        return btnno;
    }

    public void setBtnno(Integer btnno) {
        this.btnno = btnno;
    }

    public String getBtnicon() {
        return btnicon;
    }

    public void setBtnicon(String btnicon) {
        this.btnicon = btnicon == null ? null : btnicon.trim();
    }

    public Integer getMenuno() {
        return menuno;
    }

    public void setMenuno(Integer menuno) {
        this.menuno = menuno;
    }

    public Integer getInitstatus() {
        return initstatus;
    }

    public void setInitstatus(Integer initstatus) {
        this.initstatus = initstatus;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }
}