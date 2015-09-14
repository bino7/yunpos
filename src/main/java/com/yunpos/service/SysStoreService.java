package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysOrder;
import com.yunpos.model.SysStore;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysStoreMapper;
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
}
