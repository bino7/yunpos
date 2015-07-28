package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysButtonWithBLOBs;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysButtonMapper;
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
	

  

}
