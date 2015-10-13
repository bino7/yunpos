package com.yunpos.model.card;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunpos.utils.DateUtil;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.serializer.JsonDateSerializer;

public class SysCardTemplate extends GridRequest {
	
	private Integer id;

	private String title;

	private String cardColor;

	private String subtitle;

	private Integer type; // 卡券类型 1：通用券

	private Integer number;

	private Integer inventory;

	private Integer limitNum;

	private String background;

	private String logo;

	private Date startDate;

	private Date endDate;

	private String tel;

	private String operation;

	private String merchant;

	private Date createdAt;

	private Date updatedAt;

	private String validityDate;

	private String discount;// 折扣额度
	
	private String customtitle;// 自定义标题
	
	private String share;// 卡券分享
	
	private String notice;// 操作提示
	
	private String urltitle; // 入口名称
	
	private String urldesc;// 引导语
	
	private String  typeDescription; //卡券类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCardColor() {
		return cardColor;
	}

	public void setCardColor(String cardColor) {
		this.cardColor = cardColor == null ? null : cardColor.trim();
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle == null ? null : subtitle.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background == null ? null : background.trim();
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant == null ? null : merchant.trim();
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

	public String getValidityDate() {
		return validityDate = DateUtil.format(this.getStartDate()) + "至" + DateUtil.format(this.getEndDate());
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getUrltitle() {
		return urltitle;
	}

	public void setUrltitle(String urltitle) {
		this.urltitle = urltitle;
	}

	public String getUrldesc() {
		return urldesc;
	}

	public void setUrldesc(String urldesc) {
		this.urldesc = urldesc;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCustomtitle() {
		return customtitle;
	}

	public void setCustomtitle(String customtitle) {
		this.customtitle = customtitle;
	}

	public String getTypeDescription() {
		if(!Tools.isNullOrEmpty(type)){
	    	switch(type){
		    	case 1 :typeDescription = "通用券" ;
		    		break;
		    	case 2 :typeDescription = "折扣券" ;
		    		break;
		    	case 3 :typeDescription = "优惠券" ;
		    		break;
		    	default : typeDescription = "";
	    	}
    	}
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	
}