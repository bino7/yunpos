package com.yunpos.persistence.dao;

import com.yunpos.model.FilterDifinitionData;

import java.util.List;

public interface FilterDifinitionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterDifinitionData record);

    int insertSelective(FilterDifinitionData record);

    FilterDifinitionData selectByPrimaryKey(Integer id);

    List<FilterDifinitionData> selectByResourceId(Integer resourceId);

    int updateByPrimaryKeySelective(FilterDifinitionData record);

    int updateByPrimaryKey(FilterDifinitionData record);
}