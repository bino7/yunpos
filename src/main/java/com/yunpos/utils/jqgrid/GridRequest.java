package com.yunpos.utils.jqgrid;

import com.yunpos.utils.Tools;

/**
 * 
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月4日
 * @author Devin_Yang 修改日期：2015年8月4日
 *
 */
public class GridRequest {
	protected int pageNumber;
	protected int pageSize;
	
	protected int start;

	protected String sortField;
	protected boolean sortDesc;

	protected String searchField;
	protected String searchValue;
	protected String searchOperation;

	public GridRequest() {
	}

	public GridRequest(int pageNumber, int pageSize, String sortField, boolean sortDesc, String searchField, String searchValue, String searchOperation) {
//		if(Tools.isNullOrEmpty(pageNumber)){
//			pageNumber = 0;
//		}
//		if(Tools.isNullOrEmpty(pageSize)){
//			pageSize = 20;
//		}
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.start = pageNumber * pageSize;
		this.sortField = sortField;
		this.sortDesc = sortDesc;
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.searchOperation = searchOperation;
	}

	public int getPageNumber() {
		if(Tools.isNullOrEmpty(pageNumber)){
			pageNumber = 0;
		}
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
//		if(Tools.isNullOrEmpty(pageSize) || pageSize == 0){
//			pageSize = 20;
//		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public boolean getSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchOperation() {
		return searchOperation;
	}

	public void setSearchOperation(String searchOperation) {
		this.searchOperation = searchOperation;
	}

	// generated automatically; equals() is used in unit testing
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridRequest other = (GridRequest) obj;
		if (pageNumber != other.pageNumber)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (searchField == null) {
			if (other.searchField != null)
				return false;
		} else if (!searchField.equals(other.searchField))
			return false;
		if (searchOperation == null) {
			if (other.searchOperation != null)
				return false;
		} else if (!searchOperation.equals(other.searchOperation))
			return false;
		if (searchValue == null) {
			if (other.searchValue != null)
				return false;
		} else if (!searchValue.equals(other.searchValue))
			return false;
		if (sortDesc != other.sortDesc)
			return false;
		if (sortField == null) {
			if (other.sortField != null)
				return false;
		} else if (!sortField.equals(other.sortField))
			return false;
		return true;
	}

	public int getStart() {
		start = pageNumber * pageSize;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
