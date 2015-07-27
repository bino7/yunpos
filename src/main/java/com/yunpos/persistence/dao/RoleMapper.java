package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Role;

public interface RoleMapper extends EntityMapper<Role> {

	List<Role> findByIdsMap(Object[] objects);
	
	@Select("select * from role")
	List<Role> findAll();
}