package com.yunpos.dataFilter;

public class FilterRule {

	public String field;

	public String op;

	public String type;

	public String value;

	public FilterRule(String field, String op, String type, String value) {
		this.field = field;
		this.op = op;
		this.type = type;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
