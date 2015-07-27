package com.yunpos.model;

import java.util.List;
import java.util.Map;


public class ViewPage<T> {

	private List<T> rows;
	private int page;
	//private int max;
	private int records;
	private long total;
	private Map<String,Object> userdata;	//扩展字段
	
	public ViewPage() {
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public Map<String, Object> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<String, Object> userdata) {
		this.userdata = userdata;
	}
	
	

}
