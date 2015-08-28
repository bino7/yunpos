package com.yunpos.utils.jqgrid;

/**
 * 
 * 功能描述：分页查询请求参数
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月4日
 * @author Devin_Yang 修改日期：2015年8月4日
 *
 */
public class JqGridRequest {
	protected int page;				//当前页数
	protected int rows;				//每页显示行数
	protected String sidx;			//
	protected String sord;			//排序字段
	protected String searchField;	//查询字段
	protected String searchString;	//查询参数
	protected String searchOper;	//查询操作or/and
	
	//交易流水查询条件
	protected String startTransTime;	//查询开始时间
	protected String endTransTime;		//查询结束时间
	protected String channel;			//支付渠道
	protected String status;			//交易状态
	
	


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public GridRequest createDataRequest() {

		GridRequest dataRequest = new GridRequest();

		dataRequest.setPageNumber(page);
		dataRequest.setPageSize(rows);
		dataRequest.setSearchField(searchField);
		dataRequest.setSearchOperation(searchOper);
		dataRequest.setSearchValue(searchString);
		dataRequest.setSortDesc(sord != null && sord.equalsIgnoreCase("desc"));
		dataRequest.setSortField(sidx);

		return dataRequest;
	}
}
