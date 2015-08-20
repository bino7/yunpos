package com.yunpos.model;

import com.yunpos.rewriter.filter.*;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.value.Value;

import java.util.ArrayList;
import java.util.List;

public class FilterDifinitionData {
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
                default:throw new java.lang.IllegalArgumentException("unsupported code enum type FilterDifinitionViewModel.ValueType "+code);
            }
        }


    }

    private Integer id;

    private Integer resource_id;

    private String name;

    private ValueType valueType;

    private Value.DataType dataType;

    private String table_name;

    private String col_name;

    private String key;

    private Integer support_op_code;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name == null ? null : table_name.trim();
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name == null ? null : col_name.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getSupport_op_code() {
        return support_op_code;
    }

    public void setSupport_op_code(Integer support_op_code) {
        this.support_op_code = support_op_code;
    }
}