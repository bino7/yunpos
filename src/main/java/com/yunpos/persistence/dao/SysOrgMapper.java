package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrg;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysOrgMapper extends EntityMapper<SysOrg>{
	@Select("select * from sys_org order by sequence")
	public List<SysOrg> findAll();
	
	@Select("select * from sys_org g where g.orgName=#{orgName}")
	public List<SysOrg> findByOrgName(String orgName);

	public List<SysOrg> findByCondition(GridRequest gridRequest); 
	
	/**
	 * 获取当前等级组织结构最大编号
	 * @param sysOrg
	 * @return
	 */
	public String findMaxOrgNo(SysOrg sysOrg); 
    
}