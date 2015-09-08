package com.yunpos.model;

import java.util.Date;

public class SysOrderEcommerce {
    private Integer id;

    private Integer baseOrderId;

    private String orderType;

    private String postcode;

    private String address;

    private String msg;

    private Byte sendStatus;

    private Date sendTime;

    private String logisticsid;

    private String logistics;

    private String logisticsOrder;

    private Date handleTime;

    private String handleUid;

    private Byte checkFlag;

    private Date checkTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Integer baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(String logisticsid) {
        this.logisticsid = logisticsid == null ? null : logisticsid.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public String getLogisticsOrder() {
        return logisticsOrder;
    }

    public void setLogisticsOrder(String logisticsOrder) {
        this.logisticsOrder = logisticsOrder == null ? null : logisticsOrder.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleUid() {
        return handleUid;
    }

    public void setHandleUid(String handleUid) {
        this.handleUid = handleUid == null ? null : handleUid.trim();
    }

    public Byte getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Byte checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}