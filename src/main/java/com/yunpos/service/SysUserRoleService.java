package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysUserRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysUserRoleMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysUserRoleService extends EntityService<SysUserRole> {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public EntityMapper<SysUserRole> getMapper() {
		return sysUserRoleMapper;
	}
	
	public List<SysUserRole> findRoleByUserId(int intValue) {
		return sysUserRoleMapper.selectByUserId(intValue);
	}
	
	public List<SysUserRole> findAll(){
		return sysUserRoleMapper.findAll();
	}

	public GridResponse<SysUserRole> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysUserRole> response = new GridResponse<SysUserRole>();
		List<SysUserRole> userRoles =  sysUserRoleMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(userRoles);
		response.setTotalRowCount(userRoles.size());
		return response;
	}
	
	
	public List<SysUserRole> findUserRoleByRoleId(int roleId) {
		return sysUserRoleMapper.findUserRoleByRoleId(roleId);
	}

	public int deleteByUserId(int userId) {
		return sysUserRoleMapper.deleteByUserId(userId);
	}
	
	public int deleteByUserIdAandRoleId(int userId,int roleId){
		return sysUserRoleMapper.deleteByUserIdAandRoleId(userId,roleId);
	}

}
