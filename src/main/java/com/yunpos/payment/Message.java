package com.yunpos.payment;

import java.io.Serializable;
import java.util.Map;

import com.yunpos.payment.PayResData;


public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8675783824101316138L;

	/**
	 * 是否操作成标识
	 */
	public boolean isSuccess = false;
	/**
	 * 编码,当返回有多种可能的情况下使用编码区分结果状态
	 */
	public String code = "";
	
	/**
	 * 提示信息
	 */
	public String msg = "";

	
	public Message(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Message(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	public Message(boolean isSuccess, String code, String msg) {
		super();
		this.isSuccess = isSuccess;
		this.code = code;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
