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

import com.yunpos.model.Role;
import com.yunpos.model.ViewPage;
import com.yunpos.service.RoleService;

@Controller
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(value="/ajax/role",method =RequestMethod.GET)
	public @ResponseBody ViewPage<Role> list() {
		ViewPage<Role> viewPage = new ViewPage<Role>();
		List<Role> list = roleService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}

	@RequestMapping(value = "/ajax/role/{id}", method = GET)
	public @ResponseBody Role read(@PathVariable("id") int id) {
		return roleService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/role", method = RequestMethod.POST)
	public void create(@Valid Role role) {
		roleService.save(role);
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.PUT)
	public void update(@Valid Role role, @PathVariable("id") int id) {
		role.setOrgId(id);
		roleService.update(role);
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		roleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/role/select", method =RequestMethod.GET )
	public  @ResponseBody List<Role> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Role> roleList = roleService.findAll();
		return roleList;
	}
}
