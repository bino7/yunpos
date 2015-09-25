package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysOrg;
import com.yunpos.model.SysPay;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysPayMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysPayService extends EntityService<SysPay> {
	@Autowired
	SysPayMapper sysPayMapper;
	

	@Override
	public EntityMapper<SysPay> getMapper() {
		return sysPayMapper;
	}

	
	public List<SysPay> findAll(){
		return sysPayMapper.findAll();
	}
	
	//通过邮箱查询
//	public SysUser findByEmail(String email) {
//		return sysUserMapper.findByEmail(email);
//	}

	public GridResponse<SysPay> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysPay> response = new GridResponse<SysPay>();
		List<SysPay> sysPay =  sysPayMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysPay);
		response.setTotalRowCount(sysPay.size());
		return response;
	}


	public GridResponse<SysPay> search(GridRequest gridRequest) {
		GridResponse<SysPay> response = new GridResponse<SysPay>();
		List<SysPay> sysPay =  sysPayMapper.findByCondition(gridRequest);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysPay);
		response.setTotalRowCount(sysPay.size());
		return response;
	}
	

	
}
