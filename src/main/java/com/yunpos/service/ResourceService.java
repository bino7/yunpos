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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger= LoggerFactory.getLogger(ResourceService.class);
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private FilterMapper filterMapper;
    @Autowired
    private FilterGroupMapper filterGroupMapper;
    @Autowired
    private FilterDifinitionMapper filterDifinitionMapper;

    public int addResource(Resource resource){
        return resourceMapper.insert(resource);
    }
    public List<Resource> getAllResources(){
        return resourceMapper.selectAll();
    }
    public Resource getResource(int id){
        return resourceMapper.selectByPrimaryKey(id);
    }
    public int removeResource(int id) { return resourceMapper.deleteByPrimaryKey(id);}
    public int updateResource(Resource res) { return resourceMapper.updateByPrimaryKeySelective(res);}

    public List<FilterDifinition> getAllFilterDifinitions(int resourceId){
        return filterDifinitionMapper.selectByResourceId(resourceId);
    }
    public int addFilterDifinition(FilterDifinition filterDifinition){
        return filterDifinitionMapper.insert(filterDifinition);
    }
    public int removeFilterDifinition(int id){
        return filterDifinitionMapper.deleteByPrimaryKey(id);
    }

    public int addFilter(int rid,Filter filter){
        filter.setResourceId(rid);
        return filterMapper.insert(filter);
    }
    public void updateFilter(Filter filter) {
        logger.info("filter update:"+filter.getFilterValue());
        filterMapper.updateByPrimaryKey(filter);
    }
    public void removeFilter(Integer id) { filterMapper.deleteByPrimaryKey(id);}
    public int addFilterGroup(FilterGroup filterGroup){
        return filterGroupMapper.insert(filterGroup);
    }
    public int removeFilterGroup(Integer id){
        return filterGroupMapper.deleteByPrimaryKey(id);
    }
}
