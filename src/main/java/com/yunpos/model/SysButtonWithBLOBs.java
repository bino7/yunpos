package com.yunpos.model;

public class SysButtonWithBLOBs extends SysButton {
	private String btnClass;

	private String btnScript;

	public String getBtnClass() {
		return btnClass;
	}

	public void setBtnClass(String btnClass) {
		this.btnClass = btnClass == null ? null : btnClass.trim();
	}

	public String getBtnScript() {
		return btnScript;
	}

	public void setBtnScript(String btnScript) {
		this.btnScript = btnScript == null ? null : btnScript.trim();
	}
}