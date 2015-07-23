package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysApp;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAppMapper;
@Service
public class SysAppService extends EntityService<SysApp> {
	@Autowired
	private SysAppMapper sysAppMapper;

	@Override
	public EntityMapper<SysApp> getMapper() {
		return sysAppMapper;
	}

}
