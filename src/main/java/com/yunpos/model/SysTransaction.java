package com.yunpos.model;

import java.io.Serializable;
import java.util.Date;

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
public class SysTransaction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;					//id

    private Integer channel;			//支付渠道,，1支付宝，2微信，3银联，4：预存款

    private Integer subChannel;			//细分渠道，0：支付宝Wap，1:支付宝手机

    private String title;				//商品名称

    private String merchantName;		//商户名

    private String serialNo;			//商户编号

    private String agentSerialNo;		//代理商编号

    private String terminalNum;			//终端号

    private String transCardNum;		//支付帐号（支付宝账户、银联卡号、微信财付通帐号）	

    private String transNum;			//交易流水号

    private Date transTime;				//交易时间

    private Float transPrice;			//实际交易金额（小数点后两位）

    private Float totalPrice;			//订单金额（小数点后两位）

    private Integer scanType;				//扫描类型，正扫：QR_CODE_OFFLIN，反扫：BARCODE_PAY_OFFLINE

    private String couponCode;			//卡券核销码

    private Integer status;				//付款状态， 0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5：退款失败，6：付款失败

    private Integer transType;				//交易类型，0:支付，1:退款

    private String orderId;

    private String oid;

    private String info;				//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChannel() {
		return channel;
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

	public Integer getScanType() {
		return scanType;
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

	public Integer getTransType() {
		return transType;
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

  
}