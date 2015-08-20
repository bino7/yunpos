package com.yunpos.model;

public class FilterDifinitionValue {

    private Integer id;

    private FilterDifinitionData filterDifinition;

    private String value;

    public FilterDifinitionValue(Integer id, FilterDifinitionData filterDifinition, String value) {
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

    public FilterDifinitionData getFilterDifinition() {
        return filterDifinition;
    }

    public void setFilterDifinition(FilterDifinitionData filterDifinition) {
        this.filterDifinition = filterDifinition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}