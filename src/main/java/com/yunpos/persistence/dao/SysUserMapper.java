package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMenu;
import com.yunpos.model.SysUser;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysUserMapper extends EntityMapper<SysUser> {

	@Select("select * from sys_user u where u.userName=#{userName} and u.delete_status = 0")
	List<SysUser> findByUserName(@Param("userName") String userName);

	@Select("select * from sys_user u where u.delete_status=0")
	List<SysUser> findAll();
	
	
	List<SysUser> findByCondition(GridRequest gridRequest);
	
	 //通过邮件查询
	@Select("select * from sys_user u where u.email=#{email}")
	List<SysUser> findByEmail(String email);
	
	@Select("select * from sys_user u where u.phone=#{phone}")
	List<SysUser> findByPhone(String phone);

	List<SysUser> findListByIds(Object[] array); 
}