package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.UserRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.UserRoleMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class UserRoleService extends EntityService<UserRole> {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public EntityMapper<UserRole> getMapper() {
		return userRoleMapper;
	}
	
	public List<UserRole> findRoleByUserId(int intValue) {
		return userRoleMapper.selectByUserId(intValue);
	}
	
	public List<UserRole> findAll(){
		return userRoleMapper.findAll();
	}

	public GridResponse<UserRole> findPageUsers(GridRequest gridRequest) {
		GridResponse<UserRole> response = new GridResponse<UserRole>();
		List<UserRole> userRoles =  userRoleMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(userRoles);
		response.setTotalRowCount(userRoles.size());
		return response;
	}

}
