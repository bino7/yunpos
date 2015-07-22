package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.UserRole;

public interface UserRoleMapper extends EntityMapper<UserRole> {
	
	  @Select("select * from user_role t where t.userid=#{userid}")
	List<UserRole> selectByUserId(@Param("userid")int userId);
 

}