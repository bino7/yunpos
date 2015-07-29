package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.UserRole;

public interface UserRoleMapper extends EntityMapper<UserRole> {

	@Select("select * from user_role t where t.userId=#{userId}")
	List<UserRole> selectByUserId(@Param("userId") int userId);

	@Select("select * from user_role")
	List<UserRole> findAll();


}
