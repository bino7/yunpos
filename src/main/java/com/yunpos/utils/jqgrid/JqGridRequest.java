package com.yunpos.utils.jqgrid;


public class JqGridRequest {
	protected int page;
	protected int rows;
	protected String sidx;
	protected String sord;
	protected String searchField;
	protected String searchString;	
	protected String searchOper;

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
