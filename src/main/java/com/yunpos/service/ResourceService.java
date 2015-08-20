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

import com.yunpos.model.*;
import com.yunpos.persistence.dao.FilterDifinitionMapper;
import com.yunpos.persistence.dao.FilterGroupMapper;
import com.yunpos.persistence.dao.FilterMapper;
import com.yunpos.persistence.dao.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FilterMapper filterMapper;
    @Autowired
    private FilterGroupMapper filterGroupMapper;
    @Autowired
    private FilterDifinitionMapper filterDifinitionMapper;

    public List<Resource> getAllResources(){
        return resourceMapper.selectAll();
    }

    public List<FilterDifinition> getAllFilterDifinitions(int resourceId){
        List<FilterDifinition> ret=new ArrayList<>();
        List<FilterDifinitionData> difinitionDataList=filterDifinitionMapper.selectByResourceId(resourceId);
        for(FilterDifinitionData data:difinitionDataList){
            ret.add(new FilterDifinition(data));
        }
        return ret;
    }

    public int addFilter(Filter filter){
        return filterMapper.insert(filter);
    }
    public void updateFilter(Filter filter) {
        filterMapper.updateByPrimaryKey(filter);
    }
    public void removeFilter(Integer id) { filterMapper.deleteByPrimaryKey(id);}
    public int addFilterGroup(FilterGroup filterGroup){
        return filterGroupMapper.insert(filterGroup);
    }
    public void removeFilterGroup(Integer id){
        filterGroupMapper.deleteByPrimaryKey(id);
    }
}
