package com.yunpos.model.viewModel;

/**
 * Created by bino on 15-9-29.
 */
public class FilterViewModel {
    private Integer id;
    private Integer resourceId;
    private Integer groupId;
    private Integer difinitionId;
    private Integer op;
    private String filterValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDifinitionId() {
        return difinitionId;
    }

    public void setDifinitionId(Integer difinitionId) {
        this.difinitionId = difinitionId;
    }

    public Integer getOp() {
        return op;
    }

    public void setOp(Integer op) {
        this.op = op;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
