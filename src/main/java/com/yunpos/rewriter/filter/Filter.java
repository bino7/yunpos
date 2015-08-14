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

package com.yunpos.rewriter.filter;

import com.yunpos.rewriter.Binding;
import com.yunpos.rewriter.value.Value;

import java.util.Map;
import java.util.StringJoiner;

public abstract class Filter implements Binding{

    public static enum Op{
        EQ(0,"="),GE(1,">="),GT(2,">"),LE(3,"<="),LT(4,"<"),LIKE(5,"like"),IN(6,"in");
        private int code;
        private String symbol;
        Op(int code,String symbol){
            this.code=code;
            this.symbol=symbol;
        }
        public int getCode(){
            return code;
        }
        public String getSymbol(){
            return symbol;
        }
        public static Op fromCode(int code){
            switch(code){
                case 0:return EQ;
                case 1:return GE;
                case 2:return LT;
                case 3:return LE;
                case 4:return LT;
                case 5:return LIKE;
                default:throw new java.lang.IllegalArgumentException("unsported code "+code+" for enum Filter.Op");
            }
        }
    }

    private Op op;
    private Value rightValue;

    public String toSql(Map<String,Object> params){
        String left= getLeftValue(params);
        String right= getRightValue(params);
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(left);
        joiner.add(op.getSymbol());
        switch (op){
            case IN:
                joiner.add("("+right+")");
                break;
            default:
                joiner.add(right);
        }
        return joiner.toString();
    }
    private Map<String,Object> params;
    public void bind(Map<String,Object> params){
        this.params=params;
    }
    protected abstract String getLeftValue(Map<String, Object> params);
    protected String getRightValue(Map<String, Object> params){
        return rightValue.toString();
    }


    /*public String toSql(Map<String,String> params){
        String leftParam=parseValue(this.getLeftParam(),params);
        String rightParam=null;
        if(rightParams.length==1){
            rightParam=rightParams[0];
        }else {
            StringJoiner joiner = new StringJoiner(",");
            for (int i = 0; i < this.getRightParams().length; i++) {
                joiner.add(parseValue(this.getRightParams()[i], params));
            }
            rightParam=joiner.toString();
        }
        switch(op){
            case LIKE:
                return (leftTableAlias!=null?leftTableAlias+".":"")+leftParam+" "+op.getSymbol()+" '"
                        +(rightTableAlias!=null?rightTableAlias+".":"")+rightParam+"'";
            case IN:
                return (leftTableAlias!=null?leftTableAlias+".":"")+leftParam+" "+op.getSymbol()+" ("
                        +rightParam+")";
            default:
                return (leftTableAlias!=null?leftTableAlias+".":"")+leftParam+" "+op.getSymbol()+" "
                        +(rightTableAlias!=null?rightTableAlias+".":"")+rightParam;
        }
    }

    private String parseValue(String value,Map<String,String> params){
        if(params.containsKey(value)){
            return params.get(value);
        }
        return value;
    }*/
}