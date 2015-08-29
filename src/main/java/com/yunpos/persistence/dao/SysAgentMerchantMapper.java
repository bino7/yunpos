package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysAgentMerchant;

public interface SysAgentMerchantMapper extends EntityMapper<SysAgentMerchant>{
	
	@Select("select * from sys_agentmerchant order by id")
	List<SysAgentMerchant> findAll();
	
	List<SysAgentMerchant> search();

  
}