package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysToken;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysTokenMapper;

@Service
public class SysTokenService extends EntityService<SysToken>{
	@Autowired
	private SysTokenMapper sysTokenMapper;
	@Override
	public EntityMapper<SysToken> getMapper() {
		return sysTokenMapper;
	}

}
