package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysOrg;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAgentMerchantMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysAgentMerchantService extends EntityService<SysAgentMerchant>{
	@Autowired
	private SysAgentMerchantMapper sysAgentMerchantMapper;
	
	@Override
	public EntityMapper<SysAgentMerchant> getMapper() {
		return sysAgentMerchantMapper;
	}

	public List<SysAgentMerchant> findAll() {
		return sysAgentMerchantMapper.findAll();
	}

	public GridResponse<SysAgentMerchant> search(GridRequest gridRequest) {
		GridResponse<SysAgentMerchant> response = new GridResponse<SysAgentMerchant>();
		List<SysAgentMerchant> sysApps =  sysAgentMerchantMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}

	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<SysAgentMerchant> findByParms(SysAgentMerchant sysAgentMerchant) {
		List<SysAgentMerchant> list = sysAgentMerchantMapper.selectByParm(sysAgentMerchant);
		return list;
	}
}
