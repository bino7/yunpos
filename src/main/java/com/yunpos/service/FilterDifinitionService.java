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

package com.yunpos.service;

import com.yunpos.model.FilterDifinition;
import com.yunpos.model.FilterDifinitionValue;
import com.yunpos.persistence.dao.FilterDifinitionMapper;
import com.yunpos.persistence.dao.FilterDifinitionValueMapper;
import com.yunpos.rewriter.filter.KeyFilter;
import com.yunpos.rewriter.value.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/17
 * @author bino 修改日期：2015/8/17
 */
@Service
public class FilterDifinitionService {

    @Autowired
    FilterDifinitionMapper filterDifinitionMapper;

    @Autowired
    FilterDifinitionValueMapper filterDifinitionValueMapper;

    @Transactional
    public int addOrUpdateFilterDifinition(FilterDifinition difinition) throws IOException, ParseException {
        Integer id=difinition.getId();
        if(id==null){
            id=filterDifinitionMapper.insert(difinition);
        }else{
            id=filterDifinitionMapper.updateByPrimaryKey(difinition);
        }
        return id;
    }

    public int removeFilterDifinition(int id){
        return filterDifinitionMapper.deleteByPrimaryKey(id);
    }

    public int getValueCount(int difinitionId) throws IOException,ParseException{
        return filterDifinitionValueMapper.countByDifinitionId(difinitionId);
    }
    public List<Value> getValues(int difinitionId) throws IOException, ParseException {
        Map map=new HashMap();
        map.put("difinitionId",difinitionId);
        List<FilterDifinitionValue> filterDifinitionValueList=filterDifinitionValueMapper.selectByDifinitionId(map);
        List<Value> valueList=new ArrayList<>();
        for(FilterDifinitionValue filterDifinitionValue:filterDifinitionValueList){
            Value.DataType dataType=filterDifinitionValue.getFilterDifinition().getDataType();
            String jsonValue=filterDifinitionValue.getValue();
            valueList.add(Value.fromJson(dataType, jsonValue));
        }
        return valueList;
    }

    public List<Value> getValues(int difinitionId,int offset,int limit) throws IOException, ParseException {
        Map map=new HashMap();
        map.put("difinitionId",difinitionId);
        map.put("offset",offset);
        map.put("limit", limit);
        List<FilterDifinitionValue> filterDifinitionValueList=filterDifinitionValueMapper.selectByDifinitionId(map);
        List<Value> valueList=new ArrayList<>();
        for(FilterDifinitionValue filterDifinitionValue:filterDifinitionValueList){
            Value.DataType dataType=filterDifinitionValue.getFilterDifinition().getDataType();
            String jsonValue=filterDifinitionValue.getValue();
            valueList.add(Value.fromJson(dataType, jsonValue));
        }
        return valueList;
    }

    public void removeValue(int difinitionId,int difinitionValueId){
        filterDifinitionValueMapper.deleteByPrimaryKey(difinitionValueId);
    }

    private static final Map<String,KeyFilter> keyFilterMap=new HashMap<>();
    static{
        //keyFilterMap.put(Binding.USER_NAME,new KeyFilter(Binding.USER_NAME));
    }
    public KeyFilter getKeyFilter(String key){
        return null;
    }
}
