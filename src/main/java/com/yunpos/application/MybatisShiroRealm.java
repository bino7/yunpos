//package com.yunpos.application;
//
//import java.util.HashSet;
//import java.util.List;
//
//import org.apache.shiro.authc.AccountException;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.google.common.base.Strings;
//import com.google.common.collect.Sets;
//import com.yunpos.model.Privilege;
//import com.yunpos.model.Privilege.Privilegemaster;
//import com.yunpos.model.Role;
//import com.yunpos.model.User;
//import com.yunpos.model.UserPrincipal;
//import com.yunpos.model.UserRole;
//import com.yunpos.service.PrivilegeService;
//import com.yunpos.service.RoleService;
//import com.yunpos.service.UserRoleService;
//import com.yunpos.service.UserService;
///**
// * 
// * 功能描述：认证授权实现realm
// * <p>
// * 版权所有：小牛信息科技有限公司
// * <p>
// * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
// * 
// * @author Devin_Yang 新增日期：2015年7月19日
// * @author Devin_Yang 修改日期：2015年7月19日
// *
// */
//class MybatisShiroRealm extends AuthorizingRealm {
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserRoleService userRoleService;
//	@Autowired
//	private RoleService roleService;
//	@Autowired
//	private PrivilegeService  privilegeService;
//	
//	//重写认证方法（登录认证调用此方法）
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(
//			AuthenticationToken token) throws AuthenticationException {
//		//获取表单提交的用户名和密码
//		UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) token;
//		String username = usernamePasswordToke.getUsername();
//		if(Strings.isNullOrEmpty(username)){
//			throw new AccountException("Null or empty username are not allowed!");
//		}
//		String encodedPassword = new Sha256Hash(usernamePasswordToke.getPassword()).toBase64();
//		
//		//查询服务获取用户信息
//		User user = userService.findByUserName(username);
//		UserPrincipal userPrincipal = new UserPrincipal(user, usernamePasswordToke);
//		if(user!=null){
//			 //return new SimpleAuthenticationInfo(new UserPrincipal(user,usernamePasswordToke),user.getLoginname(), user.getLoginpassword(), getName());  
//			 return new SimpleAuthenticationInfo(userPrincipal,user.getLoginpassword(), getName());  
//		}else{
//			throw new UnknownAccountException("No account found for [" + username + "]");
//		}
//	}
//		
//	
//	//重写授权方法
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(
//			PrincipalCollection principals) {
//		// 得到 doGetAuthenticationInfo 方法中传入的凭证
//		UserPrincipal userPrincipal = (UserPrincipal) getAvailablePrincipal(principals);
//		
//		List<UserRole> listuserRole = userRoleService.findByUserId(userPrincipal.getId().intValue());
//		HashSet<Integer> roleIds  = Sets.newHashSet();
//		HashSet<String> roleNames  = Sets.newHashSet();
//		HashSet<String> permissions = Sets.newHashSet();
//		for(UserRole userRole:listuserRole){
//			roleIds.add(userRole.getRoleid());
//		}
//		List<Role> roleList = roleService.findListByIds(roleIds.toArray());
//		List<Privilege> privilegeList = privilegeService.findListByIds(roleIds.toArray());
//		for(Role role:roleList){
//			roleNames.add(role.getRolename());
//		}
//		System.out.println("当前用户具有的角色："+roleNames.toString());
//		for(Privilege privilege:privilegeList){
//			//当前值考虑角色鉴权，后续再添加用户直接授权
//			permissions.add(privilege.getPrivilegeaccess()+":"+privilege.getPrivilegeaccessvalue()+":"+privilege.getPrivilegeoperation());
//		}	
//		System.out.println("当前用户具有的权限信息："+permissions.toString());
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
//		info.setStringPermissions(permissions);
//		return info;
//	}
//
//}
