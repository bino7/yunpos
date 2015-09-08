package com.yunpos.model;

/**
 * 
 * 功能描述：行业
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月2日
 * @author tiger_lin 修改日期：2015年9月2日
 *
 */
public class Industry {
	
    private int id;                // id

    private String industryType;		// 行业名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
}