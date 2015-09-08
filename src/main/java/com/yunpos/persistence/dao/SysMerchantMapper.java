package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMerchant;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysMerchantMapper extends EntityMapper<SysMerchant>{
	@Select("select * from sys_merchant order by id")
	List<SysMerchant> findAll();

	List<SysMerchant> search(GridRequest gridRequest);
	
	@Select("select * from sys_merchant where serialNo=#{serialNo}")
	SysMerchant findBySerialNo(String serialNo);
	
	/**
	 * 根据商户参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	List<SysMerchant> selectByParm(SysMerchant sysMerchant);
    
}