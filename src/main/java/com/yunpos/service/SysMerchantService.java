package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMerchant;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMerchantMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMerchantService extends EntityService<SysMerchant>{

	@Autowired
	private SysMerchantMapper sysMerchantMapper;
	
	@Override
	public EntityMapper<SysMerchant> getMapper() {
		return sysMerchantMapper;
	}

	public GridResponse<SysMerchant> search(GridRequest gridRequest) {
		GridResponse<SysMerchant> response = new GridResponse<SysMerchant>();
		List<SysMerchant> sysMerchant =  sysMerchantMapper.search(gridRequest);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysMerchant);
		response.setTotalRowCount(sysMerchant.size());
		return response;
	}

	public List<SysMerchant> findAll() {
		return sysMerchantMapper.findAll();
	}

	public SysMerchant findBySerialNo(String serialNo) {
		return sysMerchantMapper.findBySerialNo(serialNo);
	}

}
