package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysApp;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAppMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysAppService extends EntityService<SysApp> {
	@Autowired
	private SysAppMapper sysAppMapper;

	@Override
	public EntityMapper<SysApp> getMapper() {
		return sysAppMapper;
	}
	
	public List<SysApp> findAll(){
		return sysAppMapper.findAll();
	}

	public GridResponse<SysApp> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysApp> response = new GridResponse<SysApp>();
		List<SysApp> sysApps =  sysAppMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}

}
