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
import java.util.Arrays;
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
                default:throw new IllegalArgumentException("unsupported code "+code+" for enum "+DataType.class);
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

    public String getText(){
        if (value==null) {
            return "";
        }
        switch(dataType){
            case DATE:
                return Value.dateFormat.format(value);
            case STRING:
            case INT:
                return value.toString();
            default:
                throw new IllegalArgumentException("unsupported data type "+dataType);
        }

    }
    public static Value fromJson(DataType dataType, String json) throws IOException, ParseException {
        if(json==null){
            return new Value(dataType,null);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Object value = objectMapper.readValue(json, Object.class);

        if(dataType==DataType.DATE){
            objectMapper.setDateFormat(Value.dateFormat);
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
    public static Value fromSplitedStr(DataType dataType,String str,String spliter){
        if(str==null || str.trim().isEmpty()){
            return null;
        }
        String[] vs=str.split(spliter);
        if(vs[vs.length-1].trim().isEmpty()){
            vs= Arrays.copyOf(vs,vs.length-1);
        }
        return fromStr(dataType,vs);
    }

    public static Value fromStr(DataType dataType,String... str ){
        if(str.length==0){
            return null;
        }
        if(str.length==1){
            Object data=parse(dataType,str[0]);
            return new Value(dataType,data);
        }else{
            List list=new ArrayList<>();
            for(int i=0;i<str.length;i++){
                list.add(parse(dataType,str[i]));
            }
            return new Value(dataType,list);
        }
    }

    private static Object parse(DataType dataType,String str ){
        switch(dataType){
            case STRING:
                return str;
            case DATE:
                try {
                    return Value.dateFormat.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            case INT:
                return Integer.valueOf(str);
            default:
                throw new IllegalArgumentException("unsupported data type "+dataType);
        }
    }

    public String toJson() throws IOException, ParseException {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(Value.dateFormat);
        return objectMapper.writeValueAsString(this.value);
    }
    public Object getValue(){
        return value;
    }

    public void setValue(Object value){
        if(getDataType()==null){
            throw new IllegalAccessError("you should set dateType before setValue for a "+this.getClass());
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
