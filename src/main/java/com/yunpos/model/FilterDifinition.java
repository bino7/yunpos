package com.yunpos.model;

import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.value.Value;

import java.util.List;

public class FilterDifinition {

    public static enum ValueType{
        SINGLE_VALUE(0),SINGLE_SELECTED_VALUE(1),MUTIL_SELECTED_VALUE(2);
        int code;
        ValueType(int code){
            this.code=code;
        }
        public int getCode(){
            return code;
        }

        public static ValueType fromCode(int code){
            switch (code){
                case 0:return SINGLE_VALUE;
                case 1:return SINGLE_SELECTED_VALUE;
                case 2:return MUTIL_SELECTED_VALUE;
                default:throw new IllegalArgumentException("unsupported code enum type FilterDifinitionViewModel.ValueType "+code);
            }
        }

    }

    public static enum Type{
        COLUMN_FILTER(0),KEY_PARAM_FILTER(1);
        int code;
        Type(int code){
            this.code=code;
        }
        public int getCode(){
            return code;
        }

        public static Type fromCode(int code){
            switch (code){
                case 0:return COLUMN_FILTER;
                case 1:return KEY_PARAM_FILTER;
                default:throw new IllegalArgumentException("unsupported code enum type FilterDifinitionViewModel.Type "+code);
            }
        }

    }



    private Integer id;

    private Integer resourceId;

    private String name;

    private Type type;

    private ValueType valueType;

    private Value.DataType dataType;

    private String colName;

    private String keyParam;

    private String keyColumn;

    private Integer supportOpCode;

    private List<Filter.Op> supportOp;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public Value.DataType getDataType() {
        return dataType;
    }

    public void setDataType(Value.DataType dataType) {
        this.dataType = dataType;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName == null ? null : colName.trim();
    }

    public String getKeyParam() {
        return keyParam;
    }

    public void setKeyParam(String keyParam) {
        this.keyParam = keyParam == null ? null : keyParam.trim();
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn == null ? null : keyColumn.trim();
    }

    public Integer getSupportOpCode() {
        return supportOpCode;
    }

    public void setSupportOpCode(Integer supportOpCode) {
        this.supportOpCode = supportOpCode;
        supportOp= Filter.Op.disjoint(supportOpCode);
    }

    public List<Filter.Op> getSupportOp() {
        return supportOp;
    }

}