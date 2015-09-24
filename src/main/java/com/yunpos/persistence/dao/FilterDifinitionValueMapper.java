package com.yunpos.persistence.dao;

import com.yunpos.model.FilterDifinitionValue;

import java.util.List;
import java.util.Map;

public interface FilterDifinitionValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilterDifinitionValue record);

    int insertSelective(FilterDifinitionValue record);

    FilterDifinitionValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilterDifinitionValue record);

    int updateByPrimaryKey(FilterDifinitionValue record);

    List<FilterDifinitionValue> selectByDifinitionId(Map map);

    int countByDifinitionId(int difinitionId);
}