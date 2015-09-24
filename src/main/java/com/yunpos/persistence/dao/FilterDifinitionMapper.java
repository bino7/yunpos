package com.yunpos.persistence.dao;

import com.yunpos.model.FilterDifinition;

import java.util.List;

public interface FilterDifinitionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterDifinition record);

    int insertSelective(FilterDifinition record);

    FilterDifinition selectByPrimaryKey(Integer id);

    List<FilterDifinition> selectByResourceId(Integer rid);

    int updateByPrimaryKeySelective(FilterDifinition record);

    int updateByPrimaryKey(FilterDifinition record);
}