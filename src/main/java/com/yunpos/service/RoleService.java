package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Role;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.RoleMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

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

	public GridResponse<Role> findPageUsers(GridRequest gridRequest) {
		GridResponse<Role> response = new GridResponse<Role>();
		List<Role> roles =  roleMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(roles);
		response.setTotalRowCount(roles.size());
		return response;
	}

}
