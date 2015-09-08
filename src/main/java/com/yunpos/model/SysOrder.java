package com.yunpos.model;

import java.util.Date;

import com.yunpos.utils.jqgrid.GridRequest;

/**
 * 
 * 功能描述：订单
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月8日
 * @author tiger_lin 修改日期：2015年9月8日
 *
 */
public class SysOrder extends GridRequest{
	
    private Integer id; 				// id

    private String orderId;				//订单号

    private String openId;				//用户的OpenId

    private String memberId;			//下单用户Id
    
    private String sysMerchantId;		//商户ID
    
    private String sysMerchantSerialNo;	//商户编码
    
    private String agentId;				//代理商ID
    
    private String agentSerialNo;		//代理商编号
    
    private String orgId;				//组织结构ID
    
    private String orgName;				//组织结构名称
    
    private Date createdAt;				//下单时间

    private Date updatedAt;				//订单更新时间

    private String trueName;			//订单消费用户

    private String tel;					//订单消费用户联系电话

    private String productList;			//商品信息列表，格式：products:[{productName:商品名称,price:价格,num:数量},{productName:商品名称,price:价格,num:数量}]

    private Float productPrice;			//商品总价格

    private Integer total;				//商品数量

    private Float mailPrice;			//额外费用

    private Float couponPrice;			//积分、优惠券抵扣金额

    private Float totalPrice;			//订单总价格

    private Boolean groupon;			//团购标识，0：非团购，1：团购

    private String groupcode;			//团购码

    private String houseAddress;		//门店地址

    private Byte payMode;				//支付方式，0：在线支付；1：货到付款；2：会员卡支付 3：其他

    private Byte payType;				//支付类型，0：银联支付，1：支付宝支付，2：微信支付 3：其他

    private Byte payStatus;				//付款状态，0：未付款，1：付款中，2：已付款 ，3：退款，4：退款中，5:退款失败，6：付款失败，

    private Integer score;				//消费获得的积分

    private Byte printed;				//小票打印状态，0：未打印，1：打印中，2：已打印

    private Byte handleStatus;			//订单处理状态，0：未处理，1：处理中，2：已处理

    private String info;				//订单备注
    
    

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public Byte getPayStatus() {
        return payStatus;
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

    public Byte getPrinted() {
        return printed;
    }

    public void setPrinted(Byte printed) {
        this.printed = printed;
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

	public String getSysMerchantId() {
		return sysMerchantId;
	}

	public void setSysMerchantId(String sysMerchantId) {
		this.sysMerchantId = sysMerchantId;
	}

	public String getSysMerchantSerialNo() {
		return sysMerchantSerialNo;
	}

	public void setSysMerchantSerialNo(String sysMerchantSerialNo) {
		this.sysMerchantSerialNo = sysMerchantSerialNo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentSerialNo() {
		return agentSerialNo;
	}

	public void setAgentSerialNo(String agentSerialNo) {
		this.agentSerialNo = agentSerialNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
    
}