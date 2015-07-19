package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysApp;

public interface SysAppMapper {
    int deleteByPrimaryKey(Integer applicationid);

    int insert(SysApp record);

    int insertSelective(SysApp record);

    SysApp selectByPrimaryKey(Integer applicationid);

    int updateByPrimaryKeySelective(SysApp record);

    int updateByPrimaryKey(SysApp record);
    
    @Select("select * from SysApp")
	List<SysApp> findAll();
}