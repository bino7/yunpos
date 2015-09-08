package com.yunpos.model;

import java.util.Date;

/**
 * 地址
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月2日
 * @author tiger_lin 修改日期：2015年9月2日
 *
 */
public class Area {
	
    private Long id;                // id

    private Date addTime;			// 添加时间

    private Boolean deleteStatus;	// 删除状态

    private String areaName;		// 区域名称

    private Boolean common;			// 常用地区，设置常用地区后该地区出现在在店铺搜索页常用地区位置

    private Integer level;			// 层级

    private Integer sequence;		// 序号

    private Long parent_id;			// 上级区域

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Boolean getCommon() {
        return common;
    }

    public void setCommon(Boolean common) {
        this.common = common;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}