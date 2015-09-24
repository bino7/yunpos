package com.yunpos.model;

public class FilterDifinitionValue {

    private Integer id;

    private FilterDifinition filterDifinition;

    private String value;

    public FilterDifinitionValue(){

    }

    public FilterDifinitionValue(Integer id, FilterDifinition filterDifinition, String value) {
        this.id = id;
        this.filterDifinition = filterDifinition;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FilterDifinition getFilterDifinition() {
        return filterDifinition;
    }

    public void setFilterDifinition(FilterDifinition filterDifinition) {
        this.filterDifinition = filterDifinition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}