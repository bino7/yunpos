package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysPayOrder;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysPayOrderMapper;
@Service
public class SysPayOrderService extends EntityService<SysPayOrder>{
	@Autowired
	private SysPayOrderMapper sysPayOrderMapper;
	
	@Override
	public EntityMapper<SysPayOrder> getMapper() {
		return sysPayOrderMapper;
	}

}
