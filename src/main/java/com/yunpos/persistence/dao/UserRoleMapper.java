package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer userroleid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer userroleid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    @Select("select * from user_role")
    List<UserRole> findAll();
    
    
    @Select("select * from user_role t where t.userid=#{userid}")
	List<UserRole> selectByUserId(@Param("userid")int userId);
}