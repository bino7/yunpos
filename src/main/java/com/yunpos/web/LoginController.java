package com.yunpos.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.SysUser;
import com.yunpos.payment.Message;
import com.yunpos.security.exception.IncorrectCaptchaException;
import com.yunpos.service.SysPrivilegeService;
import com.yunpos.service.SysUserService;

/**
 * 
 * 功能描述：云铺后台登陆控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月21日
 * @author Devin_Yang 修改日期：2015年7月21日
 *
 */
@Controller
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysPrivilegeService sysPrivilegeService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/ajax/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
			return new Message(false, "param_is_null", "参数为空");
		}

		List<SysUser> sysUser = sysUserService.findByUserName(username);
		if (sysUser == null) {
			return new Message(false, "user_not_exist", "用户不存在"); 	
		}
		if (!sysUser.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(password.trim().getBytes()))) {
			return new Message(false, "password_error", "密码错误");
		}

		// UsernamePasswordToken token = new UsernamePasswordToken(username,
		// password);
		// Subject currentUser = SecurityUtils.getSubject();
		// try {
		// currentUser.login(token);
		// } catch ( UnknownAccountException uae ) {
		// return new Message(true,"error","登录失败",null);
		// } catch ( IncorrectCredentialsException ice ) {
		// return new Message(true,"error","登录失败",null);
		// } catch ( LockedAccountException lae ) {
		// return new Message(true,"error","登录失败",null);
		// } catch ( ExcessiveAttemptsException eae ) {
		// return new Message(true,"error","登录失败",null);
		// }catch ( AuthenticationException ae ) {
		// return new Message(true,"error","登录失败",null);
		// }
		Map<String, Object> returnMap = new HashMap<>();
		String menus = sysPrivilegeService.getUserMenu(sysUser.get(0).getId());
		request.getSession().setAttribute("user", sysUser.get(0));
		returnMap.put("user", sysUser.get(0));
		returnMap.put("menu", menus);
		System.out.println(menus);
		return new Message(true, "success", "登录成功", returnMap);

	}

	@RequestMapping(value = "/ajax/profil", method = RequestMethod.POST)
	@ResponseBody
	public Object profil(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
		Map<String, Object> returnMap = null;
		if (sysUser != null) {
			returnMap = new HashMap<>();
			String menus = sysPrivilegeService.getUserMenu(sysUser.getId());
			returnMap.put("user", sysUser);
			returnMap.put("menu", menus);
		}
		return returnMap;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, Model model) {
		SecurityUtils.getSubject().logout();
		return "login";
	}

	@RequestMapping(value = "/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}

	@SuppressWarnings("unused")
	private String parseException(HttpServletRequest request) {
		String errorClassName = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

		String msg = null;
		if (IncorrectCaptchaException.class.getName().equals(errorClassName)) {
			msg = "The verification code is incorrect.";
		} else if (UnknownAccountException.class.getName().equals(errorClassName)) {
			msg = "Username or password is incorrect.";
		} else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
			msg = "Username or password is incorrect.";
		} else if (AuthenticationException.class.getName().equals(errorClassName)) {
			msg = "Username or password is incorrect.";
		} else if (DisabledAccountException.class.getName().equals(errorClassName)) {
			msg = "Username or password is incorrect.";
		} else {
			msg = "Username or password is incorrect.";
		}
		return msg;
	}
}