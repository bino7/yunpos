package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Privilege;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.PrivilegeMapper;


@Service
public class PrivilegeService extends EntityService<Privilege>{
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public EntityMapper<Privilege> getMapper() {
		return  privilegeMapper;
	}
	
	
	public List<Privilege> findListByIds(Object[] array) {
		// TODO Auto-generated method stub
		return privilegeMapper.findListByIds(array);
	}
	 
	public List<Privilege> findAll(){
		return privilegeMapper.findAll();
	}
	
	







}
