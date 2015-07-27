package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.User;

public interface UserMapper extends EntityMapper<User> {

	@Select("select * from sys_user u where u.userName=#{userName}")
	User findByUserName(@Param("userName") String userName);
	
	@Select("select * from sys_user")
	List<User> findAll();

}