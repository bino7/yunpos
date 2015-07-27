package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysButton;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysButtonMapper;
@Service
public class SysButtonService extends EntityService<SysButton> {
	
	@Autowired
	private SysButtonMapper  sysButtonMapper;

	@Override
	public EntityMapper<SysButton> getMapper() {
		return sysButtonMapper;
	}
	
	public List<SysButton> findAll(){
		return sysButtonMapper.findAll();
	}
	

  

}
