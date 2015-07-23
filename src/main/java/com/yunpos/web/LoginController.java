package com.yunpos.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yunpos.security.exception.IncorrectCaptchaException;
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
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, Model model) {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @SuppressWarnings("unused")
	private String parseException(HttpServletRequest request) {
        String errorClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

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