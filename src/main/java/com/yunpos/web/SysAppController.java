package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpos.model.SysApp;
import com.yunpos.model.ViewPage;
import com.yunpos.service.SysAppService;

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
public class SysAppController extends BaseController{
	
	@Autowired
	private  SysAppService sysAppService;
	
	@RequestMapping(value="/ajax/app",method = GET)
	public @ResponseBody ViewPage<SysApp> list() {
		ViewPage<SysApp> viewPage = new ViewPage<SysApp>();
		List<SysApp> list = sysAppService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setRecords(list.size());
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	@RequestMapping(value = "/ajax/app/{id}", method = GET)
	public @ResponseBody SysApp read(@PathVariable("id") int id) {
		return sysAppService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/app", method = RequestMethod.POST)
	public void create(@Valid SysApp sysApp) {
		sysAppService.save(sysApp);
	}

	@RequestMapping(value = "/ajax/app/{id}", method = RequestMethod.PUT)
	public void update(@Valid SysApp sysApp, @PathVariable("id") int id) {
		sysApp.setId(id);
		sysAppService.update(sysApp);
	}

	@RequestMapping(value = "/ajax/app/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysAppService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/app/select", method =RequestMethod.GET )
	public  @ResponseBody List<SysApp> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysApp> roleList = sysAppService.findAll();
		return roleList;
	}
}
