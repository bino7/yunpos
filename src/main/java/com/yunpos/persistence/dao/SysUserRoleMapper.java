package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysUserRole;

public interface SysUserRoleMapper extends EntityMapper<SysUserRole> {

	@Select("select * from sys_user_role t where t.userId=#{userId}")
	List<SysUserRole> selectByUserId(@Param("userId") int userId);

	@Select("select * from sys_user_role")
	List<SysUserRole> findAll();
}