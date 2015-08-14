package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysPrivilege;

public interface SysPrivilegeMapper extends EntityMapper<SysPrivilege>{
  
	List<SysPrivilege> findListByIds(Object[] array);
	
	@Select("select * from sys_privilege")
	List<SysPrivilege> findAll();
	
	List<SysPrivilege> findListByRoleIds(Object[] array);
}