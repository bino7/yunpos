package com.yunpos.service.payment;

/**
 * 
 * 功能描述：支付信息DTO
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
public class PayParam {

	//基本参数
	private String channel;		//（必填）支付通道 DNA:银联 VPOS:快钱 ALIPAY:支付宝
	private String roleId;		//订单号
	private String userId;		//订单标题
	private String price;		//订单总金额
	private String barCode;		//条码
	private String imei;		
	private String deviceType;	//设备类型
	private String orderNo;		
	private String orderTitle;	
	
	
	
	//微信支付
	private String body;	//商品或支付单简要描述
	private String attach;
	private String spBillCreateIP;
	private String timeStart;
	private String timeExpire;
	private String goodsTag;
	
	


	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSpBillCreateIP() {
		return spBillCreateIP;
	}

	public void setSpBillCreateIP(String spBillCreateIP) {
		this.spBillCreateIP = spBillCreateIP;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}


	
	
	public enum payChannel{
		ALIPAY,		//支付宝
		DNA,		//银联	
		VPOS;		//快钱
	}
	

	
	@Override
	public String toString() {
		return "PayParam [channel=" + channel + ", roleId=" + roleId
				+ ", userId=" + userId + ", price=" + price + ", barCode="
				+ barCode + ", imei=" + imei + ", deviceType=" + deviceType
				+ ", orderNo=" + orderNo + ", orderTitle=" + orderTitle + "]";
	} 
}
