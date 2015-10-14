package com.yunpos.web;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yunpos.model.Page;
import com.yunpos.model.SysUser;
import com.yunpos.security.SecurityUser;
import com.yunpos.utils.PageDate;
import com.yunpos.utils.Tools;
import com.yunpos.utils.UuidUtil;

public class BaseController {
	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 得到PageData
	 */
	public PageDate getPageParam() {
		return new PageDate(this.getRequest());
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map<String,String> getRequestParams(HttpServletRequest request){
		Map<String,String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;

	}
	

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {

		return UuidUtil.get32UUID();
	}
	
	
	public SysUser getUser(){
		SysUser currentUser = null ;
		try{
			currentUser = (SysUser) this.getRequest().getSession().getAttribute("user");
		}catch(Exception e){
			
		}
		 if(Tools.isNullOrEmpty(currentUser)){
			 currentUser = new SysUser();
		 }
		return currentUser;
	}
	
//	public SecurityUser getUser(){
//		SecurityUser currentUser = null ;
//		this.getRequest().getSession().getAttribute("user");
//		try{
//		    currentUser = (SecurityUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
//		}catch(Exception e){
//			
//		}
//		 if(Tools.isNullOrEmpty(currentUser)){
//			 currentUser = new SecurityUser(1, null, null, 1, "cash","0002");
//		 }
//		 return currentUser;
//	}

	/**
	 * 得到分页列表的信息
	 */
	public Page getPage() {
		return new Page();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
