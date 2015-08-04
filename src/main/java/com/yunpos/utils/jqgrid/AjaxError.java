package com.yunpos.utils.jqgrid;

public class AjaxError {
	private String globalErrorCode;
	private String localErrorMessage;
	private String[] validationErrors;

	public String getLocalErrorMessage() {
		return localErrorMessage;
	}

	public void setLocalErrorMessage(String localErrorMessage) {
		this.localErrorMessage = localErrorMessage;
	}

	public String getGlobalErrorCode() {
		return globalErrorCode;
	}

	public void setGlobalErrorCode(String globalErrorCode) {
		this.globalErrorCode = globalErrorCode;
	}

	public String[] getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(String[] validationErrors) {
		this.validationErrors = validationErrors;
	}
}
