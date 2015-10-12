package com.yunpos.model.card;

import java.util.Date;

import com.yunpos.utils.jqgrid.GridRequest;

public class SysCardCoupon  extends GridRequest {
    private Integer id;

    private String appid_userId;

    private String title;

    private String source;

    private Byte type;

    private Byte subType;

    private Float price;

    private Float discount;

    private Integer score;

    private Integer pagesPer;

    private String info;

    private String sn;

    private Date startTime;

    private Date endTime;

    private Float quota;

    private Date createdAt;

    private Date updatedAt;

    private String appid_cardId;

    private Byte status;

    private Date useTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid_userId() {
        return appid_userId;
    }

    public void setAppid_userId(String appid_userId) {
        this.appid_userId = appid_userId == null ? null : appid_userId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getSubType() {
        return subType;
    }

    public void setSubType(Byte subType) {
        this.subType = subType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPagesPer() {
        return pagesPer;
    }

    public void setPagesPer(Integer pagesPer) {
        this.pagesPer = pagesPer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
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

    public String getAppid_cardId() {
        return appid_cardId;
    }

    public void setAppid_cardId(String appid_cardId) {
        this.appid_cardId = appid_cardId == null ? null : appid_cardId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}