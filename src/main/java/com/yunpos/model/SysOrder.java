package com.yunpos.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.serializer.JsonDateSerializer;

/**
 * 
 * 功能描述：订单
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月23日
 * @author tiger_lin 修改日期：2015年9月23日
 *
 */
public class SysOrder  extends GridRequest {
    private Integer id;

    private String orderId;

    private String openId;

    private String memberId;

    private String nickName;

    private String sysMerchantId;

    private String sysMerchantSerialNo;

    private String agentId;

    private String agentSerialNo;

    private String orgId;

    private String orgName;

    private Date createdAt;

    private Date updatedAt;

    private String trueName;

    private String tel;

    private String productList;

    private Float productPrice;

    private Integer total;

    private Float mailPrice;

    private Float couponPrice;

    private Float totalPrice;

    private Boolean groupon;

    private String groupcode;

    private String houseAddress;

    private Date payTime;

    private Byte payMode;

    private Byte payType;

    private Byte payStatus;

    private Integer score;

    private Byte printStatus;

    private Byte handleStatus;

    private String info;

    private String shopId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getSysMerchantId() {
        return sysMerchantId;
    }

    public void setSysMerchantId(String sysMerchantId) {
        this.sysMerchantId = sysMerchantId == null ? null : sysMerchantId.trim();
    }

    public String getSysMerchantSerialNo() {
        return sysMerchantSerialNo;
    }

    public void setSysMerchantSerialNo(String sysMerchantSerialNo) {
        this.sysMerchantSerialNo = sysMerchantSerialNo == null ? null : sysMerchantSerialNo.trim();
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public String getAgentSerialNo() {
        return agentSerialNo;
    }

    public void setAgentSerialNo(String agentSerialNo) {
        this.agentSerialNo = agentSerialNo == null ? null : agentSerialNo.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList == null ? null : productList.trim();
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getMailPrice() {
        return mailPrice;
    }

    public void setMailPrice(Float mailPrice) {
        this.mailPrice = mailPrice;
    }

    public Float getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Float couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getGroupon() {
        return groupon;
    }

    public void setGroupon(Boolean groupon) {
        this.groupon = groupon;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode == null ? null : groupcode.trim();
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress == null ? null : houseAddress.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getPayMode() {
        return payMode;
    }

    public void setPayMode(Byte payMode) {
        this.payMode = payMode;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
    	
 	if(this.payStatus!=null){
    /*	switch(this.payStatus){
    	case 0 : return "已支付";
    	case 1 : return "已取消";
    	default : return ;
    	}*/
 		if(this.payStatus == 0){
    		return  "已支付";
    	}else if(this.payStatus==1){
    		return  "已取消";
    	}else{
    		return null;
    	}
 	}else{
 		return null;
 	}
        
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Byte getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Byte printStatus) {
        this.printStatus = printStatus;
    }

    public Byte getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Byte handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }
}