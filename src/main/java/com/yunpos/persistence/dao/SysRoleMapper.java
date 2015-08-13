package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysRole;

public interface SysRoleMapper extends EntityMapper<SysRole> {
	List<SysRole> findListByIds(Object[] array);
	
	void batchDeleteByIds(Object[] array);
	
	@Select("select * from sys_role")
	List<SysRole> findAll();
}