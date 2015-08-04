package com.yunpos.utils.jqgrid;

import java.util.List;

public class JqGridResponse<T> {

	private int total; // number of pages
	private int page; // page number
	private int records; // number of records
	private List<T> rows; // records

	public JqGridResponse() {

	}

	public JqGridResponse(GridResponse<T> dataResponse) {

		this.rows = dataResponse.getRows();
		this.page = dataResponse.getPageNumber();
		this.total = dataResponse.getTotalPageCount();
		this.records = dataResponse.getTotalRowCount();
	}

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getRecords() {
		return records;
	}

	public List<T> getRows() {
		return rows;
	}
}
