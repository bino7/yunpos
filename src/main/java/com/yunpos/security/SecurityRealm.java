package com.yunpos.security;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.yunpos.model.Privilege;
import com.yunpos.model.Role;
import com.yunpos.model.SysMenu;
import com.yunpos.model.User;
import com.yunpos.model.UserRole;
import com.yunpos.service.PrivilegeService;
import com.yunpos.service.RoleService;
import com.yunpos.service.SysMenuService;
import com.yunpos.service.UserRoleService;
import com.yunpos.service.UserService;
import com.yunpos.utils.Encodes;

public class SecurityRealm extends AuthorizingRealm {
	  private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	    @Autowired
	    UserService userService;
	    @Autowired
	    UserRoleService userRoleService;
	    @Autowired
	    RoleService roleService;
	    @Autowired
	    PrivilegeService privilegeService;
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
	        User user = userService.findByUserName(securityUser.username);
	        
	        if (user != null) {
	        	List<UserRole> listuserRole = userRoleService.findRoleByUserId(user.getId());
				HashSet<Integer> roleIds = Sets.newHashSet();
				HashSet<Integer> privilegeIds = Sets.newHashSet();
				
				HashSet<String> roleNames = Sets.newHashSet();
				HashSet<String> permissions = Sets.newHashSet();
				
				for (UserRole userRole : listuserRole) {
					roleIds.add(userRole.getRoleId());
				}
				List<Role> roleList = roleService.findListByIds(roleIds.toArray());
				List<Privilege> privilegeList = privilegeService.findListByRoleIds(roleIds.toArray());
				
				securityUser.setRoles(roleList);
				
				for (Role role : roleList) {
					roleNames.add(role.getRoleName());
				}
				System.out.println("当前用户具有的角色：" + roleNames.toString());
				
				for (Privilege privilege : privilegeList) {
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
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
	        logger.debug("doGetAuthenticationInfo......");
	        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;

	        User user = userService.findByUserName(token.getUsername());
	        if (user != null) {
	            // 检查用户是否禁用
	            if (User.STATUS_DISABLED.equalsIgnoreCase(user.getStatus())) {
	                throw new DisabledAccountException();
	            }

	            byte[] salt = Encodes.decodeHex(user.getSalt());

	            return new SimpleAuthenticationInfo(new SecurityUser(user.getId(),user.getUserName(), user.getNickname()),
	                    user.getPassword(), ByteSource.Util.bytes(salt), getName());
	        }
	        return null;
	    }

}
