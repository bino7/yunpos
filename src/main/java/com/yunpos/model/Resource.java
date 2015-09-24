package com.yunpos.model;

import com.yunpos.rewriter.filter.*;
import com.yunpos.rewriter.filter.FilterGroup;

import java.util.List;

public class Resource {
    private Integer id;

    private String name;

    private String tableName;

    private String path;

    private List<FilterGroup> filterGroupList;

    public List<FilterGroup> getFilterGroupList() {
        return filterGroupList;
    }

    public void setFilterGroupList(List<FilterGroup> filterGroupList) {
        this.filterGroupList = filterGroupList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}