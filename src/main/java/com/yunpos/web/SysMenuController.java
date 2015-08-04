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

import com.yunpos.model.SysMenu;
import com.yunpos.model.ViewPage;
import com.yunpos.service.SysMenuService;

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
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping(value = "/ajax/menu/{id}", method = GET)
	public @ResponseBody SysMenu read(@PathVariable("id") int id) {
		return sysMenuService.findById(id);
	}
	
	@RequestMapping(value="/ajax/menu",method = GET)
	public @ResponseBody ViewPage<SysMenu> list() {
		ViewPage<SysMenu> viewPage = new ViewPage<SysMenu>();
		List<SysMenu> list = sysMenuService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setRecords(list.size());
		viewPage.setTotal(list.size());
		return viewPage;
	}

	
	@RequestMapping(value = "/rest/menu/{id}", method = RequestMethod.PUT)
	public void update(@Valid SysMenu sysMenu, @PathVariable("id") int id) {
		sysMenu.setId(id);
		sysMenuService.update(sysMenu);
	}
	
	@RequestMapping(value = "/ajax/menu", method = RequestMethod.POST)
	public void create(@Valid SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
	}

	@RequestMapping(value = "/ajax/menu/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMenuService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/menu/select", method =RequestMethod.GET )
	public  @ResponseBody List<SysMenu> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysMenu> roleList = sysMenuService.findAll();
		return roleList;
	}
	
}
