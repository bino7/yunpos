package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer privilegeid);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer privilegeid);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    @Select("select * from Privilege")
	List<Privilege> findAll();

	List<Privilege> findListByIds(Object[] array);
}