package com.yunpos.persistence.dao;

import com.yunpos.model.Privilege;

import java.util.List;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    List<Privilege> selectAll();

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

    List<Privilege> selectByPath(String path);

    void deleteByPath(String path);
}