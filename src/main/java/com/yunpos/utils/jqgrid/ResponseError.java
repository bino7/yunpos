package com.yunpos.utils.jqgrid;

/**
 * 
 * 功能描述：springMVC 异常封装类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月4日
 * @author Devin_Yang 修改日期：2015年8月4日
 *
 */
public class ResponseError {
	private String globalErrorCode;		//错误代码
	private String localErrorMessage;	//错误信息
	private String[] validationErrors;	//验证错误信息

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
