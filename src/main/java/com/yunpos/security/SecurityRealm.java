package com.yunpos.security;

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

import com.yunpos.model.User;
import com.yunpos.service.UserService;
import com.yunpos.utils.Encodes;

public class SecurityRealm extends AuthorizingRealm {
	  private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	    @Autowired
	    UserService userService;

	    /**
	     * 授权
	     */
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        logger.debug("doGetAuthorizationInfo......");

	        SimpleAuthorizationInfo info = null;

	        SecurityUser securityUser = (SecurityUser) principals.getPrimaryPrincipal();
	        User user = userService.findByUserName(securityUser.username);

//	        if (user != null) {
//	            info = new SimpleAuthorizationInfo();
//
//	            for (Role role : user.getRoles()) {
//	                // 用户拥有角色
//	                info.addRole(role.getCode());
//
//	                // 角色的授权信息
//	                info.addStringPermissions(role.getPermissions().stream().map(Permission::getCode).collect(Collectors.toSet()));
//	            }
//	            // 用户的授权信息
//	            info.addStringPermissions();
//	        }
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
