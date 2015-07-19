package com.yunpos.model;



public class SysButtonWithBLOBs extends SysButton {
    private String btnclass;

    private String btnscript;

    public String getBtnclass() {
        return btnclass;
    }

    public void setBtnclass(String btnclass) {
        this.btnclass = btnclass == null ? null : btnclass.trim();
    }

    public String getBtnscript() {
        return btnscript;
    }

    public void setBtnscript(String btnscript) {
        this.btnscript = btnscript == null ? null : btnscript.trim();
    }
}