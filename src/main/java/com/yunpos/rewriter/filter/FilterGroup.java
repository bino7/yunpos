package com.yunpos.rewriter.filter;

import com.yunpos.model.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class FilterGroup extends Filter{
    private Integer id;

    private Integer parent_id;

    private Resource resource;

    private List<Filter> filterList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<FilterGroup> children;

    public List<FilterGroup> getChildren() {
        return children;
    }

    public void setChildren(List<FilterGroup> children) {
        this.children = children;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public FilterGroup(){
        filterList=new ArrayList<>();
        children=new ArrayList<>();
    }

    @Override
    public String toSql(Map<String,Object> params){
        StringJoiner joiner=new StringJoiner(" and ");
        for(Filter filter:filterList){
            joiner.add(filter.toSql(params));
        }

        StringJoiner joiner2=new StringJoiner(" or ");
        joiner2.add(joiner.toString());
        for(FilterGroup group:children){
            joiner2.add("("+group.toSql(params)+")");
        }
        return joiner2.toString();
    }

    @Override
    protected String getLeftValue(Map<String, Object> params) {
        throw new java.lang.UnsupportedOperationException(FilterGroup.class+" getLeftValue method is unsupported for filtergroup");
    }
}