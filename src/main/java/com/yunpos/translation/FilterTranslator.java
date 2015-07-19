package com.yunpos.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterTranslator {

	public String commandText;

	public FilterGroup group;

	public Map<String, String> params = new HashMap<String, String>();

	public FilterTranslator(String commandText, FilterGroup group) {
		this.commandText = commandText;
		this.group = group;
		createFilterParam();
	}

	public void createFilterParam() {
		this.params.put("and", " and ");
		this.params.put("or", " or ");
		this.params.put("equal", " = ");
		this.params.put("less", " < ");
		this.params.put("great", " > ");
		this.params.put("in", " in ");
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
		return this.commandText + " " + translateGroup(this.group);
	}

	public String translateGroup(FilterGroup filterGroup) {

		String queryText = "";
		List<FilterRule> rules = filterGroup.getRules();
		if (rules != null && !"".equals(rules) && rules.size() > 0) {
			queryText += "( 1=1 ";
			queryText += this.params.get(filterGroup.getOp());
			for (int i = 0; i < rules.size(); i++) {
				queryText = queryText + translateRule(rules.get(i));
				if (i < rules.size() - 1)
					queryText += this.params.get(filterGroup.getOp());

			}
			queryText += ")";
		} else {
			queryText += " 1=1";
		}
		List<FilterGroup> groups = filterGroup.getGroups();
		if (groups != null && !"".equals(groups) && groups.size() > 0) {
			queryText += this.params.get(filterGroup.getOp());
			queryText = queryText + "( ";
			for (int i = 0; i < groups.size(); i++) {

				queryText += translateGroup(groups.get(i));
				if (i < groups.size() - 1)
					queryText += this.params.get(filterGroup.getOp());
			}
			queryText += ")";
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

	public static void main(String[] args) throws JsonProcessingException, IOException {
		// String s
		// ="{\"rules\":[{\"field\":\"OrderDate\",\"op\":\"less\",\"value\":\"2012-01-01\"}],\"groups\":[{\"rules\":[{\"field\":\"CustomerID\",\"op\":\"equal\",\"value\":\"VINET\"},
		// {\"field\":\"CustomerID\",\"op\":\"equal\",\"value\":\"TOMSP\"}],\"op\":\"or\"}],\"op\":\"and\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		// ObjectReader reader=objectMapper.reader(FilterGroup.class);
		// FilterGroup fg = reader.readValue(s);
		// System.out.println(fg);
		// 例子一
		FilterRule fr = new FilterRule("CustomerID", "equal", "", "VINET");
		FilterRule fr1 = new FilterRule("CustomerID", "equal", "", "TOMSP");
		FilterRule fr2 = new FilterRule("OrderDate", "less", "", "2012-01-01");
		FilterGroup fg0 = new FilterGroup();

		fg0.setOp("or");
		List<FilterRule> rules = new ArrayList<FilterRule>();
		rules.add(fr);
		rules.add(fr1);
		fg0.setRules(rules);

		FilterGroup fgs = new FilterGroup();
		List<FilterGroup> fglist = new ArrayList<FilterGroup>();
		fglist.add(fg0);
		fgs.setGroups(fglist);
		fgs.setOp("and");
		List<FilterRule> rules1 = new ArrayList<FilterRule>();
		rules1.add(fr2);
		fgs.setRules(rules1);
		System.out.println(objectMapper.writeValueAsString(fgs));
		FilterTranslator ft = new FilterTranslator("select * from abc where", fgs);
		System.out.println(ft.translate());
		// 例子二
		// List<FilterRule> rules = new ArrayList<FilterRule>();
		// List<FilterGroup> fglist = new ArrayList<FilterGroup>();
		// FilterRule fr3 = new FilterRule("CustomerID", "in", "", "2,6");
		// FilterRule fr4 = new FilterRule("CustomerID", "equal", "int", "7");
		// FilterRule fr5 = new FilterRule("EmployeeID", "equal", "",
		// "CustomerEmployeeID");
		// rules.clear();
		// rules.add(fr3);
		// FilterGroup fgs2 = new FilterGroup();
		// fgs2.setRules(rules);
		// fgs2.setOp("and");
		// fglist.clear();
		// fglist.add(fgs2);
		// FilterGroup fgs3 = new FilterGroup();
		//
		// List<FilterRule> rules2 = new ArrayList<FilterRule>();
		// rules2.add(fr4);
		// rules2.add(fr5);
		// fgs3.setRules(rules2);
		// fgs3.setOp("and");
		// fglist.add(fgs3);
		//
		// FilterGroup fgs4 = new FilterGroup();
		// fgs4.setGroups(fglist);
		// fgs4.setOp("or");
		//
		// System.out.println(objectMapper.writeValueAsString(fgs4));
		// FilterTranslator ft1 = new FilterTranslator("", fgs4);
		// System.out.println(ft1.translate());
	}
}
