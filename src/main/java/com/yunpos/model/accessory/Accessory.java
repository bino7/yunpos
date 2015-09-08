package com.yunpos.model.accessory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 功能描述：上传图片文件
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月1日
 * @author tiger_lin 修改日期：2015年9月1日
 *
 */
public class Accessory {

	private Integer id;
	 
	private String name;			// 附件名称
	
	private String path;			// 存放路径
	
	private BigDecimal size;		// 附件大小
	
	private int width;				// 宽度
	
	private int height;				// 高度
	
	private String ext;				// 扩展名，不包括.
	
	private Date addTime;			// 新增时间.
	
	private int deleteStatus;		// 删除状态.
	
	private String info;			// 附件说明
	
	private Integer baseUserId;		// 附件对应的用户，可以精细化管理用户附件

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(Integer baseUserId) {
		this.baseUserId = baseUserId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	
}
