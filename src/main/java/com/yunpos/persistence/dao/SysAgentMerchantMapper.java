package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysAgentMerchant;

public interface SysAgentMerchantMapper extends EntityMapper<SysAgentMerchant>{
	
	@Select("select * from sys_agentmerchant order by id")
	List<SysAgentMerchant> findAll();
	
	List<SysAgentMerchant> search();

	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	List<SysAgentMerchant> selectByParm(SysAgentMerchant sysAgentMerchant);
}