package com.yunpos.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yunpos.model.DataRule;
import com.yunpos.model.User;
import com.yunpos.security.SecurityUser;
import com.yunpos.service.DataRuleService;

/**
 * 拦截未登录的用户信息
 * 
 * @author kingbox
 */
@Service
public class UserSecurityInterceptor implements HandlerInterceptor {

	private static final ThreadLocal<DataRule> DATA_ROLE = new ThreadLocal<DataRule>();

	@Autowired
	private DataRuleService dataRuleService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 验证用户是否登陆
		PrincipalCollection principals= (PrincipalCollection) SecurityUtils.getSubject().getPrincipals();
		SecurityUser securityUser = (SecurityUser) principals.getPrimaryPrincipal();
		
		
		
		Object obj = request.getSession().getAttribute("cur_user");
		String userid = request.getParameter("userid");
		String datatype = request.getParameter("datatype");
		if (obj== null || obj instanceof User) {
			response.sendRedirect(request.getContextPath() + "/index");
			return true;
		} else if (userid != null && datatype != null) {
			DataRule dr = dataRuleService.findByUserID(Integer.parseInt(userid), datatype);
			if (dr != null && !"".equals(dr)) {
				DATA_ROLE.set(dr);
				request.setAttribute("DATA_RULE", dr);
			}
			return true;
		}

		return false;
	}

	public static DataRule getDataRule() {
		return DATA_ROLE.get();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
