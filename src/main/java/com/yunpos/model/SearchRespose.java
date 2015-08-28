package com.yunpos.model;

import java.util.List;

public class SearchRespose<T> {
	protected List<T> rows;
	protected int pageNumber;
	protected int pageSize;
	protected int totalRowCount;

	public SearchRespose() {
	}

	public SearchRespose(int pageNumber, int pageSize, int totalRowCount, List<T> rows) {
		this.rows = rows;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRowCount = totalRowCount;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

}
