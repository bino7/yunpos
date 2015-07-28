package com.yunpos.web;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.google.common.base.Strings;
import com.yunpos.model.SysApp;
import com.yunpos.model.ViewPage;
import com.yunpos.service.SysAppService;
import com.yunpos.utils.PageDate;

/**
 * 
 * 功能描述：app资源控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@Controller
@RequestMapping("/res/app")
public class SysAppController extends BaseController{
	
	@Autowired
	private  SysAppService sysAppService;
	
	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<SysApp> list() {
		ViewPage<SysApp> viewPage = new ViewPage<SysApp>();
		List<SysApp> list = sysAppService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setRecords(list.size());
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/operate", method = { RequestMethod.POST, RequestMethod.GET })
	public void operate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDate pageParam = this.getPageParam();
		String oper = pageParam.getString("oper");
		String id = pageParam.getString("id");
		if (oper.equals("del") && !Strings.isNullOrEmpty(id)) {
			String[] ids = id.split(",");
			sysAppService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			SysApp sysApp = null;
			if (oper.equals("edit")) {
				sysApp = sysAppService.findById(Integer.valueOf(id));
			}
			SysApp entity = new SysApp();
			if(!Strings.isNullOrEmpty(pageParam.getString("applicationcode"))){
				entity.setApplicationcode(Integer.valueOf(pageParam.getString("applicationcode")));
			}
			entity.setApplicationdesc(pageParam.getString("applicationdesc"));
			entity.setApplicationname(pageParam.getString("applicationname"));
			
			if (oper.equals("edit")) {
				entity.setApplicationid(Integer.valueOf(sysApp.getApplicationid()));
				sysAppService.update(entity);
			} else if (oper.equals("add")) {
				sysAppService.save(entity);
				
			}
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody SysApp read(@PathVariable("id") int id) {
		return sysAppService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSysApp(@PathVariable("id") int id, @RequestBody SysApp sysApp) {
	    if (sysAppService.exists(id)) {
	    	sysApp.setApplicationid(id);
	    	sysAppService.update(sysApp);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createSysApp(HttpServletRequest request, @RequestBody SysApp sysApp) {
		sysAppService.insert(sysApp);
	    final int id =sysApp.getApplicationid();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	

	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSysApp(@PathVariable("id") int id) {
		sysAppService.delete(id);
	}
}
