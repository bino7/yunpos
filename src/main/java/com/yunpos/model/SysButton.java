package com.yunpos.model;

public class SysButton {
	private Integer btnId;

	private String btnName;

	private Integer btnNo;

	private String btnIcon;

	private Integer menuNo;

	private Integer initStatus;

	private Integer seqNo;

	public Integer getBtnId() {
		return btnId;
	}

	public void setBtnId(Integer btnId) {
		this.btnId = btnId;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName == null ? null : btnName.trim();
	}

	public Integer getBtnNo() {
		return btnNo;
	}

	public void setBtnNo(Integer btnNo) {
		this.btnNo = btnNo;
	}

	public String getBtnIcon() {
		return btnIcon;
	}

	public void setBtnIcon(String btnIcon) {
		this.btnIcon = btnIcon == null ? null : btnIcon.trim();
	}

	public Integer getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(Integer menuNo) {
		this.menuNo = menuNo;
	}

	public Integer getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(Integer initStatus) {
		this.initStatus = initStatus;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
}