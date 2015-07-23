package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.User;

public interface UserMapper extends EntityMapper<User> {

	@Select("select * from User u where u.loginname=#{loginname}")
	User findByUserName(@Param("loginname") String loginname);

}