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

package com.yunpos.model;

import com.yunpos.rewriter.filter.*;
import com.yunpos.rewriter.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/18
 * @author bino 修改日期：2015/8/18
 */
public class FilterDifinition extends FilterDifinitionData{



    public FilterDifinition(){

    }

    public FilterDifinition(FilterDifinitionData data){
        clone(data);
    }

    private void clone(FilterDifinitionData data) {
        super.setCol_name(data.getCol_name());
        super.setDataType(data.getDataType());
        super.setId(data.getId());
        super.setKey(data.getKey());
        super.setName(data.getName());
        super.setResource_id(data.getResource_id());
        super.setSupport_op_code(data.getSupport_op_code());
        super.setTable_name(data.getTable_name());
        super.setValueType(data.getValueType());
    }


    public List<Filter.Op> getSupportOp() {
        Integer code = getSupport_op_code();
        if(code==null){
            return null;
        }
        List<Filter.Op> supportOp=Filter.Op.disjoint(code);
        return supportOp;
    }


}
