package com.yunpos.model;

public class Filter {
    private Integer id;

    private Integer resource_id;

    private Integer group_id;

    private Integer filter_difinition_id;

    private Integer op;

    private String filter_value;

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

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getFilter_difinition_id() {
        return filter_difinition_id;
    }

    public void setFilter_difinition_id(Integer filter_difinition_id) {
        this.filter_difinition_id = filter_difinition_id;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getFilter_value() {
        return filter_value;
    }

    public void setFilter_value(String filter_value) {
        this.filter_value = filter_value == null ? null : filter_value.trim();
    }
}