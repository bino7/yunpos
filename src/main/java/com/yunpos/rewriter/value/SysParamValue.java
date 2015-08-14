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

import com.yunpos.rewriter.Binding;

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
public class SysParamValue extends Value implements Binding {
    @Override
    public Object getValue() {
        return value;
    }
    
    public Object getName() {
        return value;
    }

    @Override
    public void bind(Map<String, Object> params) {
        Object value=params.get(this.getName());
        if(value!=null){
            this.setValue(value);
        }
    }
}
