package com.yunpos.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * 功能描述：返回消息封装类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月20日
 * @author Devin_Yang 修改日期：2015年8月20日
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -8675783824101316138L;

	public String result_code = "";
	
	public String err_code = "";		//错误代码
	
	public String result_msg = "";		//返回信息描述
	
	public Map<String,String> lists;

	
	public Message(String result_code,String err_code, String result_msg,Map<String,String> lists) {
		super();
		this.result_code = result_code;
		this.err_code = err_code;
		this.result_msg = result_msg;
		this.lists = lists;
	}
	
//	public Message(String result_code, String result_msg,Map<String,String> lists) {
//		super();
//		this.result_code = result_code;
//		this.result_msg = result_msg;
//		this.lists = lists;
//	}
	
	
	public enum ErrorCode{
		PARAM_IS_NULL,		//传递参数为空
		ORDER_NOT_EXIST,	//支付流水订单不存在
		SYSTEM_EXCEPTION;	//系统异常
		
	}

	public enum ResultCode{
		SUCCESS,	//支付成功
		USERPAYING,	//支付中 （等待用户输入支付密码）
		FAIL;		//支付失败
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public Map<String, String> getLists() {
		return lists;
	}

	public void setLists(Map<String, String> lists) {
		this.lists = lists;
	}
	
	
}
