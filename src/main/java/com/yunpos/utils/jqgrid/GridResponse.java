package com.yunpos.utils.jqgrid;

import java.util.List;

public class GridResponse<T> {
	protected List<T> rows;
	protected int pageNumber;
	protected int pageSize;
	protected int totalRowCount;

	public GridResponse() {
	}

	public GridResponse(int pageNumber, int pageSize, int totalRowCount, List<T> rows) {
		this.rows = rows;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRowCount = totalRowCount;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> pageRows) {
		this.rows = pageRows;
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

	public int getTotalPageCount() {
		int pageCount = divideAndRoundUp(totalRowCount, pageSize);
		pageCount = pageCount > 0 ? pageCount : 1;
		return pageCount;
	}

	protected static int divideAndRoundUp(int num, int divisor) {
		return (num + divisor - 1) / divisor;
	}
}
