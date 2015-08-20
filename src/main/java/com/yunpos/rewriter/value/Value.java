/*
 * *
 *  * 功能描述：
 *  * <p>
 *  * 版权所有：小牛信息科技有限公司
 *  * <p>
 *  * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *  *
 *  * @author Bino Zhong 新增日期：${date}
 *  * @author Bino Zhong 修改日期：${date}
 *  *
 *
 */

package com.yunpos.rewriter.value;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/13
 * @author bino 修改日期：2015/8/13
 */
public class Value {
    public static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static enum DataType {
        STRING(0),INT(1),DATE(2);
        private int code;
        DataType(int code){
            this.code=code;
        }
        public int getCode(){
            return code;
        }
        public static DataType fromCode(int code){
            switch(code){
                case 0:return STRING;
                case 1:return INT;
                case 2:return DATE;
                default:throw new java.lang.IllegalArgumentException("unsupported code "+code+" for enum "+DataType.class);
            }
        }
    }

    protected DataType dataType;
    protected Object value;
    public Value(){
        this(null,null);
    }
    public Value(DataType dataType){
        this(dataType,null);
    }
    public Value(DataType dataType,Object value){
        this.dataType=dataType;
        this.value=value;
    }
    public static Value fromJson(DataType dataType, String json) throws IOException, ParseException {
        if(json==null){
            return new Value(dataType,null);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(Value.dateFormat);
        Object value = objectMapper.readValue(json, Object.class);

        if(dataType==DataType.DATE){
            if(value instanceof List){
                List<String> dateStrList=(List<String>)value;
                List<Date> dateList=new ArrayList<Date>();
                for(String dateStr:dateStrList){
                    Date date=dateFormat.parse(dateStr);
                    dateList.add(date);
                }
                value=dateList;
            }else{
                String dateStr=(String)value;
                Date date=dateFormat.parse(dateStr);
                value=date;
            }
        }

        if(value instanceof List){
            return new ValueList(dataType,(List)value);
        }else{
            return new Value(dataType,value);
        }
    }
    public String toJson() throws IOException, ParseException {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(Value.dateFormat);
        return objectMapper.writeValueAsString(this);
    }
    public Object getValue(){
        return value;
    }

    public void setValue(Object value){
        if(getDataType()==null){
            throw new java.lang.IllegalAccessError("you should set dateType before setValue for a "+this.getClass());
        }
        validate(getDataType(),value);
    }

    private boolean validate(DataType dataType,Object value){
        if(value instanceof List){
            List list=(List)value;
            for(Object v:list){
                if(validate(dataType,v)==false){
                    return false;
                }
            }
        }
        switch(dataType){
            case INT:
                return value.getClass()==Integer.class;
            case STRING:
                return value.getClass()==String.class;
            case DATE:
                return value.getClass()==Date.class;
            default:
                return true;
        }
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "ValueViewModel{" +
                "dataType=" + dataType +
                ", value=" + value +
                '}';
    }
}
