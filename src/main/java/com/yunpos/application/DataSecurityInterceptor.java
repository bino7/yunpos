package com.yunpos.application;

import com.yunpos.Application;
import com.yunpos.model.Resource;
import com.yunpos.rewriter.binding.BindingService;
import com.yunpos.service.ResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 拦截未登录的用户信息
 * 
 * @author kingbox
 */
@Service
public class DataSecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Log logger= LogFactory.getLog(DataSecurityInterceptor.class);


	@Autowired
	private ResourceService resourceService;

	@Autowired
	private BindingService bindingService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("--------------------------DataSecurityInterceptor--------------------------------");
		Map<String,Object> bindingParams=bindingService.getBindingParams();
		request.setAttribute(Application.BINDING_PARAMS_KEY,bindingParams);

		String path=request.getServletPath();
		List<Resource> resourceList = resourceService.getAllResources();
		if(resourceList!=null && resourceList.size()>0) {
			AntPathMatcher matcher = new AntPathMatcher();
			Comparator<String> matcherComparator = matcher.getPatternComparator(path);
			Optional<Resource> matchedResource= resourceList.stream().filter(r -> matcher.match(r.getPath(), path))
					.sorted((r1, r2) -> matcherComparator.compare(r1.getPath(), r2.getPath()))
					.findFirst();
			if(matchedResource.isPresent()){
				request.setAttribute("resource", matchedResource.get());
			}
		}
		logger.info("--------------------------DataSecurityInterceptor--------------------------------");
		return true;
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
