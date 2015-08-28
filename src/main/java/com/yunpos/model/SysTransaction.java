package com.yunpos.model;

import java.io.Serializable;
import java.util.Date;

public class SysTransaction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Byte channel;

    private Byte subChannel;

    private String title;

    private String merchantName;

    private String serialNo;

    private String agentSerialNo;

    private String terminalNum;

    private String transCardNum;

    private String transNum;

    private Date transTime;

    private Float transPrice;

    private Float totalPrice;

    private Byte scanType;

    private String couponCode;

    private Byte status;

    private Byte transType;

    private String orderId;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public Byte getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(Byte subChannel) {
        this.subChannel = subChannel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getAgentSerialNo() {
        return agentSerialNo;
    }

    public void setAgentSerialNo(String agentSerialNo) {
        this.agentSerialNo = agentSerialNo == null ? null : agentSerialNo.trim();
    }

    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum == null ? null : terminalNum.trim();
    }

    public String getTransCardNum() {
        return transCardNum;
    }

    public void setTransCardNum(String transCardNum) {
        this.transCardNum = transCardNum == null ? null : transCardNum.trim();
    }

    public String getTransNum() {
        return transNum;
    }

    public void setTransNum(String transNum) {
        this.transNum = transNum == null ? null : transNum.trim();
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

    public Byte getScanType() {
        return scanType;
    }

    public void setScanType(Byte scanType) {
        this.scanType = scanType;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTransType() {
        return transType;
    }

    public void setTransType(Byte transType) {
        this.transType = transType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}