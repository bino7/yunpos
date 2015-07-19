package com.yunpos.dataFilter;

import java.util.List;

public class FilterGroup {

	public List<FilterGroup> groups;

	public String op;

	public List<FilterRule> rules;

	public List<FilterGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<FilterGroup> groups) {
		this.groups = groups;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public List<FilterRule> getRules() {
		return rules;
	}

	public void setRules(List<FilterRule> rules) {
		this.rules = rules;
	}

}
