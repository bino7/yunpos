package com.yunpos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpos.model.Role;
import com.yunpos.model.ViewPage;
import com.yunpos.service.RoleService;

@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(method =RequestMethod.GET)
	public @ResponseBody ViewPage<Role> list() {
		ViewPage<Role> viewPage = new ViewPage<Role>();
		List<Role> list = roleService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}
}
