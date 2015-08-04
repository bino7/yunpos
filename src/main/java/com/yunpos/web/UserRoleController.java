package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
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

import com.yunpos.model.UserRole;
import com.yunpos.model.ViewPage;
import com.yunpos.service.UserRoleService;

@Controller
public class UserRoleController extends BaseController{
	@Autowired
	private UserRoleService userRoleService;

	
	@RequestMapping(value="/ajax/userRole",method = GET)
	public @ResponseBody ViewPage<UserRole> list() {
		ViewPage<UserRole> viewPage = new ViewPage<UserRole>();
		List<UserRole> list = userRoleService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		return viewPage;
	}

	
	@RequestMapping(value = "/ajax/userRole/{id}", method = GET)
	public @ResponseBody UserRole read(@PathVariable("id") int id) {
		return userRoleService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.PUT)
	public void update(@Valid UserRole userRole, @PathVariable("id") int id) {
		userRole.setUserId(id);
		userRole.setModifyDate(new Date());
		userRole.setModifyUserId(this.getUser().getId());
		userRoleService.update(userRole);
	}
	
	@RequestMapping(value = "/ajax/userRole", method = RequestMethod.POST)
	public void create(@Valid UserRole userRole) {
		userRole.setCreateDate(new Date());
		userRole.setCreateUserId(this.getUser().getId());
		userRoleService.save(userRole);
	}

	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		userRoleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/select", method =RequestMethod.GET )
	public  @ResponseBody List<UserRole> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<UserRole> roleList = userRoleService.findAll();
		return roleList;
	}
}
