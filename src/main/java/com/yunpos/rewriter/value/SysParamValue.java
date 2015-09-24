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

import com.yunpos.rewriter.binding.Binding;

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
public class SysParamValue extends Value implements Binding {
    private String paramName;

    public SysParamValue(DataType dataType,String paramName){
        super(dataType);
        this.paramName=paramName;
    }
    @Override
    public Object getValue() {
        return value;
    }
    
    public String getParamName() {
        return paramName;
    }

    @Override
    public void bind(Map<String, Object> params) throws MissBindingParamExecption{
        Object value=params.get(paramName);
        if(value==null){
            throw new Binding.MissBindingParamExecption(paramName,"");
        }
        this.setValue(value);
    }

    @Override
    public String toString() {
        return super.toString()+" SysParamValue{" +
                "paramName='" + paramName + '\'' +
                '}';
    }
}
