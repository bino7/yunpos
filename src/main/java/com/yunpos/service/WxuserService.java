package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Wxuser;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.WxuserMapper;

@Service
public class WxuserService extends EntityService<Wxuser> {
	
	@Autowired
	WxuserMapper wxuserMapper;

	@Override
	public EntityMapper<Wxuser> getMapper() {
		return wxuserMapper;
	}



}
