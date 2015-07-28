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
import com.yunpos.model.SysMenu;
import com.yunpos.model.ViewPage;
import com.yunpos.service.SysMenuService;
import com.yunpos.utils.PageDate;

/**
 * 
 * 功能描述：按钮资源控制器
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
@RequestMapping("/res/menu")
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<SysMenu> list() {
		ViewPage<SysMenu> viewPage = new ViewPage<SysMenu>();
		List<SysMenu> list = sysMenuService.findAll();
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
			sysMenuService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			SysMenu sysMenu = null;
			if (oper.equals("edit")) {
				sysMenu = sysMenuService.findById(Integer.valueOf(id));
			}
			SysMenu entity = new SysMenu();
			
			if(!Strings.isNullOrEmpty(pageParam.getString("applicationcode"))){
				entity.setApplicationcode(Integer.valueOf(pageParam.getString("applicationcode")));
			}
			if(!Strings.isNullOrEmpty(pageParam.getString("isleaf"))){
				entity.setIsleaf(Integer.valueOf(pageParam.getString("isleaf")));
			}
			if(!Strings.isNullOrEmpty(pageParam.getString("isvisible"))){
				entity.setIsvisible(Integer.valueOf(pageParam.getString("isvisible")));
			}
			entity.setMenuname(pageParam.getString("menuname"));
			
			if(!Strings.isNullOrEmpty(pageParam.getString("menuno"))){
				entity.setMenuno(Integer.valueOf(pageParam.getString("menuno")));
			}
			entity.setMenuorder(Strings.isNullOrEmpty(pageParam.getString("menuorder"))?0:Integer.valueOf(pageParam.getString("menuorder")));
			if(!Strings.isNullOrEmpty(pageParam.getString("menuparentno"))){
				entity.setMenuparentno(Integer.valueOf(pageParam.getString("menuparentno")));
			}
			entity.setMenuurl(pageParam.getString("menuurl"));
			
			
			if (oper.equals("edit")) {
				entity.setMenuid(Integer.valueOf(sysMenu.getMenuid()));
				sysMenuService.update(entity);
			} else if (oper.equals("add")) {
				sysMenuService.save(entity);
			}
		}
	}
	
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody  SysMenu read(@PathVariable("id") int id) {
		return sysMenuService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSysMenu(@PathVariable("id") int id, @RequestBody SysMenu sysMenu) {
	    if (sysMenuService.exists(id)) {
	    	sysMenu.setMenuid(id);
	    	sysMenuService.update(sysMenu);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createSysMenu(HttpServletRequest request, @RequestBody SysMenu sysMenu) {
		sysMenuService.insert(sysMenu);
		final int id = sysMenu.getMenuid();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSysMenu(@PathVariable("id") int id) {
		sysMenuService.delete(id);
	}
	
}
