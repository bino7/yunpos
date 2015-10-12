package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysStore;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysStore;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysStoreMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysStoreService extends EntityService<SysStore> {
	@Autowired
	private SysStoreMapper sysStoreMapper;
	
	@Override
	public EntityMapper<SysStore> getMapper() {
		return sysStoreMapper;
	}

	public List<SysStore> findAll() {
		// TODO Auto-generated method stub
		return sysStoreMapper.findAll();
	}
	

	/**
	 * 根据门店参数查询
	 * @param SysStore
	 * @return
	 */
	public List<SysStore> findByParms(SysStore sysStore) {
		List<SysStore> list = sysStoreMapper.selectByParm(sysStore);
		return list;
	}
	

	
	public GridResponse<SysStore> search(SysStore sysStore) {
		GridResponse<SysStore> response = new GridResponse<SysStore>();
		List<SysStore> sysApps = sysStoreMapper.selectByParm(sysStore);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}
}
