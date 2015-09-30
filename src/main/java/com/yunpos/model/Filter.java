package com.yunpos.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.rewriter.value.Value;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {
    private Logger logger= org.slf4j.LoggerFactory.getLogger(Filter.class);
    private Integer id;

    private Integer resourceId;

    private Integer op;

    private Integer groupId;

    private Integer difinitionId;

    private Integer type;

    private Integer dataType;

    private Integer valueType;

    private String filterValue;

    private List value=new ArrayList<>();

    private String colName;

    private String keyParam;

    private String keyColumn;

    private String keyTable;

    private String primaryColumn;

    private List difiniValues=new ArrayList();

    private Map<String,Boolean> supportOp=new HashMap();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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
        /*ObjectMapper mapper=new ObjectMapper();
        mapper.setDateFormat(Value.dateFormat);
        try{
        Object v=mapper.readValue(this.filterValue,Object.class);
        if (v instanceof List) {
            this.value =(List)v;
        }else{
            this.value.clear();
            this.value.add(v);
        }
        }catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getKeyParam() {
        return keyParam;
    }

    public void setKeyParam(String keyParam) {
        this.keyParam = keyParam;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getKeyTable() {
        return keyTable;
    }

    public void setKeyTable(String keyTable) {
        this.keyTable = keyTable;
    }

    public String getPrimaryColumn() {
        return primaryColumn;
    }

    public void setPrimaryColumn(String primaryColumn) {
        this.primaryColumn = primaryColumn;
    }

    public List getValue() {
        return value;
    }

    public void setValue(List value) throws JsonProcessingException {
        this.value = value;
        ObjectMapper mapper=new ObjectMapper();
        this.filterValue=mapper.writeValueAsString(this.value);
    }

    public List getDifiniValues() {
        return difiniValues;
    }

    public void setDifiniValues(String difiniValues) {
        ObjectMapper mapper=new ObjectMapper();
        mapper.setDateFormat(Value.dateFormat);
        try{
            Object v=mapper.readValue(difiniValues,Object.class);
            if (v instanceof List) {
                this.difiniValues =(List)v;
            }else{
                this.difiniValues.clear();
                this.difiniValues.add(v);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Boolean> getSupportOp() {
        return supportOp;
    }

    public void setSupportOp(Integer supportOpCode) {
        List<com.yunpos.rewriter.filter.Filter.Op> supportOps= com.yunpos.rewriter.filter.Filter.Op.disjoint(supportOpCode);
        for (com.yunpos.rewriter.filter.Filter.Op op:com.yunpos.rewriter.filter.Filter.Op.values()){
            if(supportOps.stream().filter(o-> o.getCode()==op.getCode()).findAny().isPresent()){
                this.supportOp.put(op.name(),true);
            }else{
                this.supportOp.put(op.name(),false);
            }
        }
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }
}