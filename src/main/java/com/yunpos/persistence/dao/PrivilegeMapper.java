package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Privilege;

public interface PrivilegeMapper extends EntityMapper<Privilege> {
	
	List<Privilege> findListByIds(Object[] array);
	
	@Select("select * from privilege")
	List<Privilege> findAll();
}