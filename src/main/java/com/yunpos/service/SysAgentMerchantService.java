package com.yunpos.service;

import java.util.List;

import org.h2.mvstore.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysAgentMerchant;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAgentMerchantMapper;
import com.yunpos.utils.Tools;
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
	
	
	/**
	 * 根据代理商用户信息查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public SysAgentMerchant getDataByBaseUserId(Integer baseUserId) {
		SysAgentMerchant sysAgentMerchant = new SysAgentMerchant();
		sysAgentMerchant.setBaseUserId(baseUserId);
		List<SysAgentMerchant> list = findByParms(sysAgentMerchant); 
		if(!Tools.isNullOrEmpty(list)){
			sysAgentMerchant = list.get(0);
		}
		return sysAgentMerchant;
	}
	
	
	
	/**
	 * 新增代理商
	 * @param session
	 */
	public void createSysAgentMerchant(SysAgentMerchant sysAgentMerchant) {
		save(sysAgentMerchant);
	}
	
	
}
