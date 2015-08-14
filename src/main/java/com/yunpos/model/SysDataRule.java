package com.yunpos.model;

public class SysDataRule {
    private Integer id;

    private Integer dataType;

    private Integer userId;

    private Integer roleId;

    private String dataRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDataRule() {
        return dataRule;
    }

    public void setDataRule(String dataRule) {
        this.dataRule = dataRule == null ? null : dataRule.trim();
    }
}