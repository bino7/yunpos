package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysPrivilege;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysPrivilegeMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;


@Service
public class SysPrivilegeService extends EntityService<SysPrivilege>{
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;

	@Override
	public EntityMapper<SysPrivilege> getMapper() {
		return  sysPrivilegeMapper;
	}
	
	
	public List<SysPrivilege> findListByRoleIds(Object[] array) {
		// TODO Auto-generated method stub
		return sysPrivilegeMapper.findListByRoleIds(array);
	}
	 
	public List<SysPrivilege> findAll(){
		return sysPrivilegeMapper.findAll();
	}


	public GridResponse<SysPrivilege> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysPrivilege> response = new GridResponse<SysPrivilege>();
		List<SysPrivilege> privileges =  sysPrivilegeMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(privileges);
		response.setTotalRowCount(privileges.size());
		return response;
	}
	
	







}
