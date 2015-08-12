package com.yunpos.service.payment;

import java.io.Serializable;

/**
 * 
 * 功能描述：此类对页面ajax请求响应内容时行封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -8675783824101316138L;

	public enum PayStatus{
		TRANS_SUCCESS("交易处理成功"),
		REQUEST_SUCCESS("交易请求成功"),
		FAILD("处理失败");
		
		private String desc;
		PayStatus(String desc){
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		
	}
	public PayStatus status;
	
	/**
	 * 提示信息
	 */
	public String msg = "";

	public Message(PayStatus status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public PayStatus getStatus() {
		return status;
	}

	public void setStatus(PayStatus status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message [status=" + status.desc + ", msg=" + msg + "]";
	}
}
