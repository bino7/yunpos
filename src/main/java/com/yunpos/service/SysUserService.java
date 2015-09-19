package com.yunpos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.model.SysUser;
import com.yunpos.model.SysUserRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysUserMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysUserService extends EntityService<SysUser> {
	@Autowired
	SysUserMapper sysUserMapper;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

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

	/**
	 * 新增用户及添加角色
	 */
	public void creatSysUser(SysUser user) {
		user.setCreatedAt(new Date());
		user.setStatus("1");
		save(user);
		
		if(!Strings.isNullOrEmpty(user.getRole())){
			String[] roleIds = user.getRole().split(",");
			SysUserRole sysUserRole = null;
			for(int i=0;i<roleIds.length;i++){
				sysUserRole = new SysUserRole();
				sysUserRole.setUserId(user.getId());
				sysUserRole.setRoleId(Integer.valueOf(roleIds[i]));
				sysUserRoleService.insert(sysUserRole);
			}
		}
	}
	

	/**
	 * 更新用户及角色
	 */
	public void updateSysUser(SysUser user) {
		user.setUpdatedAt(new Date());
		user.setStatus("1");
		update(user);
		sysUserRoleService.deleteByUserId(user.getId());
		if(!Strings.isNullOrEmpty(user.getRole())){
			String[] roleIds = user.getRole().split(",");
			SysUserRole sysUserRole = null;
			for(int i=0;i<roleIds.length;i++){
				sysUserRole = new SysUserRole();
				sysUserRole.setUserId(user.getId());
				sysUserRole.setRoleId(Integer.valueOf(roleIds[i]));
				sysUserRole.setCreateDate(new Date());
				sysUserRole.setCreateUserId(user.getUpdatedBy());
				sysUserRoleService.insert(sysUserRole);
			}
		}
	}
	
}
