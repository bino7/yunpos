package com.yunpos.persistence.dao;


import com.yunpos.rewriter.filter.Filter;

public interface FilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Filter record);

    int insertSelective(Filter record);

    Filter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Filter record);

    int updateByPrimaryKey(Filter record);
}