package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysButtonWithBLOBs;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysButtonMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysButtonService extends EntityService<SysButtonWithBLOBs> {
	
	@Autowired
	private SysButtonMapper  sysButtonMapper;

	@Override
	public EntityMapper<SysButtonWithBLOBs> getMapper() {
		return sysButtonMapper;
	}
	
	public List<SysButtonWithBLOBs> findAll(){
		return sysButtonMapper.findAll();
	}

	public GridResponse<SysButtonWithBLOBs> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysButtonWithBLOBs> response = new GridResponse<SysButtonWithBLOBs>();
		List<SysButtonWithBLOBs> sysButtons =  sysButtonMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysButtons);
		response.setTotalRowCount(sysButtons.size());
		return response;
	}
	

  

}
