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
import com.yunpos.rewriter.value.SysParamValue;
import com.yunpos.rewriter.value.Value;
import com.yunpos.rewriter.value.ValueList;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
public class SysParamFilter extends Filter {
    private SysParamValue sysValue;
    public SysParamFilter(Integer dataTypeCode,String valueStr,String paramName) throws IOException, ParseException {
        this(Value.DataType.fromCode(dataTypeCode), valueStr, paramName);
    }

    public SysParamFilter(Value.DataType dataType,String valueStr,String paramName) throws IOException, ParseException {

        super(dataType,valueStr);

        sysValue=new SysParamValue(dataType,paramName);
    }

    @Override
    protected void bindKey(Map<String, Object> params) throws MissBindingParamExecption {
        sysValue.bind(params);
    }

    @Override
    protected String getFilterKey() {
        return sysValue.getValue().toString();
    }

    @Override
    public String toSql() {
        if(sysValue.getParamName().equals(Binding.SYS_ROLE)){
            if(sysValue.getParamName().equals(Binding.SYS_ROLE)){
                List<Integer> key=(List<Integer>)sysValue.getValue();
                List<Integer> value=(List<Integer>)getFilterValue();
                boolean pass=false;
                if(getOp()==Op.EQ){
                    if(key.size()<=value.size()){
                        pass=false;
                    }else{
                        int m=0;
                        for(Integer k:key){
                            for(Integer v:value){
                                if(k==v){
                                    m++;
                                    continue;
                                }
                            }
                        }
                        if (m==key.size()){
                            pass=true;
                        }else{
                            pass=false;
                        }
                    }
                }else if(getOp()==Op.IN){
                    if(key.size()>value.size()){
                        pass=false;
                    }else{
                        int m=0;
                        for(Integer k:key){
                            for(Integer v:value){
                                if(k==v){
                                    m++;
                                    continue;
                                }
                            }
                        }
                        if (m==key.size()){
                            pass=true;
                        }else{
                            pass=false;
                        }
                    }
                }
                if(pass){
                    return" 1=1 ";
                }else{
                    return " 1=2 ";
                }
            }
        }
        return super.toSql();
    }

    @Override
    public void setOp(Op op) {
        super.setOp(op);
        if(op!=Op.EQ && op!=Op.IN){
            throw new java.lang.IllegalArgumentException("filter(name:"+getName()+" id:"+getId()+")'s" +
                    " Op must be EQ or IN current Op is "+op.getCode()+" "+Op.EQ.getCode()+" "+Op.IN.getCode());
        }
    }

    @Override
    public String toString() {
        return super.toString()+" SysParamFilter{" +
                "sysValue=" + sysValue +
                '}';
    }
}
