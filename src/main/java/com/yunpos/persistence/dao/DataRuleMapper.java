package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.DataRule;

public interface DataRuleMapper {
	int deleteByPrimaryKey(Integer ruleid);

	int insert(DataRule record);

	int insertSelective(DataRule record);

	DataRule selectByPrimaryKey(Integer ruleid);

	int updateByPrimaryKeySelective(DataRule record);

	int updateByPrimaryKeyWithBLOBs(DataRule record);

	int updateByPrimaryKey(DataRule record);

	// 查询角色对应的数据权限
	@Select("select * from Data_Rule where  roleid = #{roleid}")
	DataRule selectByRoleID(Integer roleid);

	// 查询用户对应的数据权限
	@Select("select * from Data_Rule dr , User_Role ur where ur.userid = #{userid} and dr.roleid = ur.roleid and datatype= #{datatype}")
	DataRule selectByUserID(Integer userid, String datatype);
}