package com.yunpos.persistence.dao;

import com.yunpos.model.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    List<Resource> selectAll();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}