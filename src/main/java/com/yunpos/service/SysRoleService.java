package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysRoleMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysRoleService extends EntityService<SysRole> {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public EntityMapper<SysRole> getMapper() {
		return sysRoleMapper;
	}

	public List<SysRole> findListByIds(Object[] array) {
		return sysRoleMapper.findListByIds(array);
	}
	
	public List<SysRole> findAll(){
		return sysRoleMapper.findAll();
	}

	public GridResponse<SysRole> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysRole> response = new GridResponse<SysRole>();
		List<SysRole> roles =  sysRoleMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(roles);
		response.setTotalRowCount(roles.size());
		return response;
	}

	public boolean existRoleName(String roleName) {
		List<SysRole> roles = sysRoleMapper.findByRoleName(roleName);
		if(roles!=null && roles.size()>0){
			return true;
		}
		return false;
	}

}
