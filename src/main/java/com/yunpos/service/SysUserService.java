package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysUser;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysUserMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysUserService extends EntityService<SysUser> {
	@Autowired
	SysUserMapper sysUserMapper;

	@Override
	public EntityMapper<SysUser> getMapper() {
		return sysUserMapper;
	}

	public List<SysUser> findByUserName(String username) {
		return sysUserMapper.findByUserName(username);
	}
	
	public List<SysUser> findAll(){
		return sysUserMapper.findAll();
	}
	
	//通过邮箱查询
//	public SysUser findByEmail(String email) {
//		return sysUserMapper.findByEmail(email);
//	}

	public GridResponse<SysUser> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysUser> response = new GridResponse<SysUser>();
		List<SysUser> sysUsers =  sysUserMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysUsers);
		response.setTotalRowCount(sysUsers.size());
		return response;
	}
	
	
	public GridResponse<SysUser> search(GridRequest gridRequest) {
		GridResponse<SysUser> response = new GridResponse<SysUser>();
		List<SysUser> sysUsers =  sysUserMapper.findByCondition(gridRequest);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysUsers);
		response.setTotalRowCount(sysUsers.size());
		return response;
	}

	
	public boolean userNameExist(String userName) {
		List<SysUser> sysUsers = sysUserMapper.findByUserName(userName);
		if(sysUsers!=null && sysUsers.size()>0){
			return true;
		}
		return false;
	}

	public boolean emailExist(String email) {
		List<SysUser> sysUsers = sysUserMapper.findByEmail(email);
		if(sysUsers!=null && sysUsers.size()>0){
			return true;
		}
		return false;
	}

	public boolean phoneExist(String phone) {
		List<SysUser> sysUsers = sysUserMapper.findByPhone(phone);
		if(sysUsers!=null && sysUsers.size()>0){
			return true;
		}
		return false;
	}

	public List<SysUser> findListByIds(Object[] array) {
		return sysUserMapper.findListByIds(array);
	}


	
}
