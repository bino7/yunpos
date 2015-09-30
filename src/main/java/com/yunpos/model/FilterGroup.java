package com.yunpos.model;

import java.util.List;

public class FilterGroup {
    private Integer id;

    private Integer resource_id;

    private Integer parent_id;

    private List<Filter> filterList;

    private List<FilterGroup> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
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