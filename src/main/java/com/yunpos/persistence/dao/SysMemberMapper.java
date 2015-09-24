package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMember;

public interface SysMemberMapper extends EntityMapper<SysMember>{
	@Select("select * from sys_member order by id")
	List<SysMember> findAll();
  
	

	/**
	 * 根据会员参数查询
	 * @param SysMember
	 * @return
	 */
	List<SysMember> selectByParm(SysMember sysMember);
}