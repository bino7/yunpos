package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Privilege;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.PrivilegeMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;


@Service
public class PrivilegeService extends EntityService<Privilege>{
	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Override
	public EntityMapper<Privilege> getMapper() {
		return  privilegeMapper;
	}
	
	
	public List<Privilege> findListByRoleIds(Object[] array) {
		// TODO Auto-generated method stub
		return privilegeMapper.findListByRoleIds(array);
	}
	 
	public List<Privilege> findAll(){
		return privilegeMapper.findAll();
	}


	public GridResponse<Privilege> findPageUsers(GridRequest gridRequest) {
		GridResponse<Privilege> response = new GridResponse<Privilege>();
		List<Privilege> privileges =  privilegeMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(privileges);
		response.setTotalRowCount(privileges.size());
		return response;
	}
	
	







}
