package com.yunpos.security;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.yunpos.model.SysMenu;
import com.yunpos.model.SysPrivilege;
import com.yunpos.model.SysRole;
import com.yunpos.model.SysUser;
import com.yunpos.model.SysUserRole;
import com.yunpos.service.SysMenuService;
import com.yunpos.service.SysPrivilegeService;
import com.yunpos.service.SysRoleService;
import com.yunpos.service.SysUserRoleService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.Encodes;

public class SecurityRealm extends AuthorizingRealm {
	  private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	    @Autowired
	    SysUserService sysUserService;
	    @Autowired
	    SysUserRoleService sysUserRoleService;
	    @Autowired
	    SysRoleService sysRoleService;
	    @Autowired
	    SysPrivilegeService sysPrivilegeService;
	    @Autowired
	    SysMenuService sysMenuService;

	    /**
	     * 授权
	     */
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        logger.debug("doGetAuthorizationInfo......");

	        SimpleAuthorizationInfo info = null;

	        SecurityUser securityUser = (SecurityUser) principals.getPrimaryPrincipal();
	        List<SysUser> sysUser = sysUserService.findByUserName(securityUser.username);
	        
	        if (sysUser != null) {
	        	List<SysUserRole> listuserRole = sysUserRoleService.findRoleByUserId(sysUser.get(0).getId());
				HashSet<Integer> roleIds = Sets.newHashSet();
				HashSet<Integer> privilegeIds = Sets.newHashSet();
				
				HashSet<String> roleNames = Sets.newHashSet();
				HashSet<String> permissions = Sets.newHashSet();
				
				for (SysUserRole userRole : listuserRole) {
					roleIds.add(userRole.getRoleId());
				}
				List<SysRole> roleList = sysRoleService.findListByIds(roleIds.toArray());
				List<SysPrivilege> privilegeList = sysPrivilegeService.findListByRoleIds(roleIds.toArray());
				
				securityUser.setSysRoles(roleList);
				
				for (SysRole sysRole : roleList) {
					roleNames.add(sysRole.getRoleName());
				}
				System.out.println("当前用户具有的角色：" + roleNames.toString());
				
				for (SysPrivilege privilege : privilegeList) {
					privilegeIds.add(privilege.getPrivilegeAccessValue());
				}
				
				List<SysMenu> menus = new ArrayList<SysMenu>();
				if(!privilegeIds.isEmpty()){
					menus = sysMenuService.findListByIds(privilegeIds.toArray());
				}
				
				for(SysMenu menu: menus){
					permissions.add("menu:"+menu.getMenuUrl());
				}
				securityUser.setPermissions(permissions);
				
				
				System.out.println("当前用户具有的权限信息：" + permissions.toString());
				info = new SimpleAuthorizationInfo(roleNames);
				info.setStringPermissions(permissions);
				return info;
	        }
	        return null;
	    }

	    /**
	     * 认证
	     */
//	    @Override
//	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//	        logger.debug("doGetAuthenticationInfo......");
//	        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
//
//	        List<SysUser> sysUsers = sysUserService.findByUserName(token.getUsername());
//	       
//	        if (sysUsers != null) {
//	        	 SysUser sysUser  = sysUsers.get(0);
//	            // 检查用户是否禁用
//	            if (SysUser.STATUS_DISABLED.equalsIgnoreCase(sysUser.getStatus())) {
//	                throw new DisabledAccountException();
//	            }
//
//	            byte[] salt = Encodes.decodeHex(sysUser.getSalt());
//
//	            return new SimpleAuthenticationInfo(new SecurityUser(sysUser.getId(),sysUser.getUserName(), sysUser.getNickname(), sysUser.getOrgId(), sysUser.getOrgName()),
//	            		sysUser.getPassword(), ByteSource.Util.bytes(salt), getName());
//	        }
//	        return null;
//	    }
	    
	    /**
	     * 认证
	     */
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
	        logger.debug("doGetAuthenticationInfo......");
	        //令牌——基于用户名和密码的令牌  
	        UsernamePasswordToken token = (UsernamePasswordToken) authcToken; 
	        
	        List<SysUser> sysUsers = sysUserService.findByUserName(token.getUsername());
	        if (sysUsers != null) {
	        	 SysUser sysUser  = sysUsers.get(0);
	            // 检查用户是否禁用
	            if (SysUser.STATUS_DISABLED.equalsIgnoreCase(sysUser.getStatus())) {
	                throw new DisabledAccountException();
	            }
	            return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
	        }
	        return null;
	    }

}
