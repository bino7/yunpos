package com.yunpos.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.yunpos.model.SysMenu;
import com.yunpos.model.SysPrivilege;
import com.yunpos.model.SysRole;
import com.yunpos.model.SysUserRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysPrivilegeMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;


@Service
public class SysPrivilegeService extends EntityService<SysPrivilege>{
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;

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
	
	/**
	 * 获取用户权限
	 * @param userId
	 * @return
	 */
	public String getUserMenu(int userId){
		HashSet<Integer> roleIds = Sets.newHashSet();
		HashSet<Integer> privilegeIds = Sets.newHashSet();
		HashSet<String> permissions = Sets.newHashSet();
		HashSet<String> roleNames = Sets.newHashSet();
		try {
			List<SysUserRole> listuserRole = sysUserRoleService.findRoleByUserId(userId);
			if(listuserRole!=null&& listuserRole.size()>0){
				for (SysUserRole userRole : listuserRole) {
					roleIds.add(userRole.getRoleId());
				}
				
				List<SysRole> roleList = sysRoleService.findListByIds(roleIds.toArray());
				List<SysPrivilege> privilegeList = findListByRoleIds(roleIds.toArray());
				for (SysPrivilege privilege : privilegeList) {
					privilegeIds.add(privilege.getPrivilegeAccessValue());
				}
				List<SysMenu> menus = new ArrayList<SysMenu>();
				if(!privilegeIds.isEmpty()){
					menus = sysMenuService.findListByIds(privilegeIds.toArray());
				}
				for(SysMenu menu: menus){
					permissions.add(menu.getMenuUrl());
				}
			}
		} catch (Exception e) {
			return "";
		}
		return permissions.toString();
	}
	
	







}
