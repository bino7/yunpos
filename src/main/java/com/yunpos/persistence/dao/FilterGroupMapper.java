package com.yunpos.persistence.dao;

import com.yunpos.model.FilterGroup;

public interface FilterGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterGroup record);

    int insertSelective(FilterGroup record);

    FilterGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilterGroup record);

    int updateByPrimaryKey(FilterGroup record);
}