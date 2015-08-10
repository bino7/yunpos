package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysDataRule;

public interface SysDataRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDataRule record);

    int insertSelective(SysDataRule record);

    SysDataRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDataRule record);

    int updateByPrimaryKeyWithBLOBs(SysDataRule record);

    int updateByPrimaryKey(SysDataRule record);
    
	// 查询角色对应的数据权限
	@Select("select * from sys_data_rule where  roleid = #{roleid}")
	SysDataRule selectByRoleID(Integer roleid);

	// 查询用户对应的数据权限
	@Select("select * from sys_data_rule dr , sys_user_role ur where ur.UserID = #{userid} and dr.RoleID = ur.RoleID and DataType= #{datatype}")
	SysDataRule selectByUserID(@Param("userid") Integer userid, @Param("datatype")String datatype);
}