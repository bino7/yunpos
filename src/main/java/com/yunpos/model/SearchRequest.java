package com.yunpos.model;
/**
 * 
 * 功能描述：查询数据封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月28日
 * @author Devin_Yang 修改日期：2015年8月28日
 *
 */
public class SearchRequest {
	protected Integer page;				//当前页数
	protected Integer rows;				//每页显示行数
	protected String sord;			//排序字段

	//交易流水查询条件
	protected String startTransTime;	//查询开始时间
	protected String endTransTime;		//查询结束时间
	protected Integer channel;			//支付渠道
	protected Integer status;			//交易状态
	public Integer getPage() {
		if(page==null){
			return 1;
		}
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		if(rows==null){
			return 10;
		}
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getStartTransTime() {
		return startTransTime;
	}
	public void setStartTransTime(String startTransTime) {
		this.startTransTime = startTransTime;
	}
	public String getEndTransTime() {
		return endTransTime;
	}
	public void setEndTransTime(String endTransTime) {
		this.endTransTime = endTransTime;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	


}
