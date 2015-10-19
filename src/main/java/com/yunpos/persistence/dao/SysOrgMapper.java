package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMenu;
import com.yunpos.model.SysOrg;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysOrgMapper extends EntityMapper<SysOrg>{
	@Select("select * from sys_org order by orgNo")
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
	
	@Select("select * from sys_org  where level =1 ORDER BY orgNo")
	public List<SysOrg> findLevelOne();
	
	@Select("select * from sys_org  where  orgParentNo=#{parentOrgNo} ORDER BY orgNo")
	public List<SysOrg> findChildByParenOrgNo(String parentOrgNo);
	
	@Select("select * from sys_org  where orgNo=#{orgNo}")
	public SysOrg selectByOrgNo(String orgNo); 
	
	
    
}