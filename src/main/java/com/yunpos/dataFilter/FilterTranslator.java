package com.yunpos.dataFilter;

import java.util.List;
import java.util.Map;

public class FilterTranslator {

	public String commandText;

	public FilterGroup group;

	public Map<String, String> params;

	public FilterTranslator(String commandText, FilterGroup group) {
		this.commandText = commandText;
		this.group = group;
		createFilterParam();
	}

	public void createFilterParam() {
		this.params.put("and", "and");
		this.params.put("or", "or");
		this.params.put("equal", "=");
		this.params.put("less", "<");
		this.params.put("great", ">");
		this.params.put("in", "in");
	}

	/**
	 * 拼装sql语句
	 * 
	 * @return
	 */
	public String getOperatorQueryText(String tableName) {

		return "select * from " + tableName + " where " + regCurrentParamMatch();
	}

	/**
	 * 替换当前用户信息
	 * 
	 * @return
	 */
	public String regCurrentParamMatch() {
		return translate();
	}

	public String translate() {
		return translateGroup(this.group);
	}

	public String translateGroup(FilterGroup filterGroup) {

		String queryText = "";
		List<FilterRule> rules = filterGroup.getRules();
		for (int i = 0; i < rules.size(); i++) {
			queryText = queryText + translateRule(rules.get(i));
			if (i < rules.size())
				queryText = queryText + filterGroup.getOp();
		}

		List<FilterGroup> groups = filterGroup.getGroups();
		if (groups.size() > 0)
			queryText += filterGroup.getOp();
		for (int i = 0; i < groups.size(); i++) {

			queryText += translateGroup(groups.get(i));
			if (i < groups.size())
				queryText = queryText + filterGroup.getOp();
		}

		return queryText;
	}

	public String translateRule(FilterRule filterRule) {

		if ("in".equals(filterRule.getOp()))
			return filterRule.getField() + this.params.get(filterRule.getOp()) + "(" + filterRule.getValue() + ")";
		else
			return filterRule.getField() + this.params.get(filterRule.getOp()) + filterRule.getValue();
	}

	public String getCommandText() {
		return commandText;
	}

	public void setCommandText(String commandText) {
		this.commandText = commandText;
	}

	public FilterGroup getGroup() {
		return group;
	}

	public void setGroup(FilterGroup group) {
		this.group = group;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "FilterTranslator [commandText=" + commandText + ", group=" + group + ", params=" + params + "]";
	}

}
