package com.yunpos.model;

import java.io.Serializable;
import java.util.Date;

import com.yunpos.utils.jqgrid.GridRequest;

/**
 * 
 * 功能描述：交易流水
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月9日
 * @author tiger_lin 修改日期：2015年9月9日
 *
 */
public class SysTransaction extends GridRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // id

	private Integer channel; // 支付渠道,，1支付宝，2微信，3银联，4：预存款

	private String channelStr; // 页面中文显示

	private Integer subChannel; // 细分渠道，0：支付宝Wap，1:支付宝手机

	private String title; // 商品名称

	private String merchantName; // 商户名

	private String serialNo; // 商户编号

	private String agentSerialNo; // 代理商编号

	private String terminalNum; // 终端号

	private String transCardNum; // 支付帐号（支付宝账户、银联卡号、微信财付通帐号）

	private String transNum; // 交易流水号

	private Date transTime; // 交易时间

	private Float transPrice; // 实际交易金额（小数点后两位）

	private Float totalPrice; // 订单金额（小数点后两位）

	private Integer scanType; // 扫描类型，正扫：QR_CODE_OFFLIN，反扫：BARCODE_PAY_OFFLINE

	private String scanTypeStr;

	private String couponCode; // 卡券核销码

	private Integer status; // 付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败

	private Integer transType; // 交易类型，0:支付，1:退款
	
	private String transTypeStr;

	private String orderId;
	

	public Integer getTransType() {
		return transType;
	}

	private String oid;

	private String info; // 备注

	private String user_order_no;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelStr() {
		if (this.channel != null) {
			if (this.channel == 0) {
				return "支付宝";
			} else if (this.channel == 1) {
				return "微信";
			} else if (this.channel == 2) {
				return "银联";
			} else if (this.channel == 3) {
				return "预付款";
			} else {
				return null;
			}
		} else {
			return null;
		}

		/*
		 * if(this.payStatus == 0){ return "已支付"; }else{ return "未支付"; }
		 */
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getSubChannel() {
		return subChannel;
	}

	public void setSubChannel(Integer subChannel) {
		this.subChannel = subChannel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAgentSerialNo() {
		return agentSerialNo;
	}

	public void setAgentSerialNo(String agentSerialNo) {
		this.agentSerialNo = agentSerialNo;
	}

	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	public String getTransCardNum() {
		return transCardNum;
	}

	public void setTransCardNum(String transCardNum) {
		this.transCardNum = transCardNum;
	}

	public String getTransNum() {
		return transNum;
	}

	public void setTransNum(String transNum) {
		this.transNum = transNum;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public Float getTransPrice() {
		return transPrice;
	}

	public void setTransPrice(Float transPrice) {
		this.transPrice = transPrice;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getScanTypeStr() {
		if (this.scanType != null) {
			if (this.scanType == 0) {
				return "正扫";
			} else if (this.scanType == 1) {
				return "反扫";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void setChannelStr(String channelStr) {
		this.channelStr = channelStr;
	}

	public void setScanTypeStr(String scanTypeStr) {
		this.scanTypeStr = scanTypeStr;
	}

	public void setScanType(Integer scanType) {
		this.scanType = scanType;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTransTypeStr() {
		if (this.transType != null) {
			if (this.transType == 0) {
				return "支付";
			} else {
				return "退款";
			}
		} else {
			return null;
		}
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid == null ? null : oid.trim();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUser_order_no() {
		return user_order_no;
	}

	public void setUser_order_no(String user_order_no) {
		this.user_order_no = user_order_no;
	}


	public void setTransTypeStr(String transTypeStr) {
		this.transTypeStr = transTypeStr;
	}

	public Integer getChannel() {
		return channel;
	}

	public Integer getScanType() {
		return scanType;
	}
	
	

}