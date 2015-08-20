package com.yunpos.rewriter.filter;

import com.yunpos.model.Resource;
import com.yunpos.rewriter.Binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class FilterGroup extends Filter{
    private int id;
    private Resource resource;

    private List<Filter> filterList;

    private List<FilterGroup> children;


    public FilterGroup(){
        filterList=new ArrayList<>();
        children=new ArrayList<>();
    }

    @Override
    public String toSql(){
        StringJoiner joiner=new StringJoiner(" and ");
        for(Filter filter:filterList){
            joiner.add(filter.toSql());
        }

        StringJoiner joiner2=new StringJoiner(" or ");
        joiner2.add(joiner.toString());
        for(FilterGroup group:children){
            joiner2.add("("+group.toSql()+")");
        }
        return joiner2.toString();
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected void bindKey(Map<String, Object> params) throws MissBindingParamExecption {
        //pass
    }

    @Override
    protected String getFilterKey() {
        return null;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public List<FilterGroup> getChildren() {
        return children;
    }

    public void setChildren(List<FilterGroup> children) {
        this.children = children;
    }
}