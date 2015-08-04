package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Role;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.RoleMapper;

@Service
public class RoleService extends EntityService<Role> {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public EntityMapper<Role> getMapper() {
		return roleMapper;
	}

	public List<Role> findListByIds(Object[] array) {
		return roleMapper.findListByIds(array);
	}
	
	public List<Role> findAll(){
		return roleMapper.findAll();
	}

}
