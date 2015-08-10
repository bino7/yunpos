package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrg;

public interface SysOrgMapper extends EntityMapper<SysOrg>{
	@Select("select * from sys_org")
	public List<SysOrg> findAll(); 
    
}