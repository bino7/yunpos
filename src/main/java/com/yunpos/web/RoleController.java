package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.Role;
import com.yunpos.service.RoleService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

@RestController
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(value="/ajax/role",method =RequestMethod.GET)
	public JqGridResponse<Role> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<Role> dataResponse = roleService.findPageUsers(gridRequest);
		return new JqGridResponse<Role>(dataResponse);
	}

	@RequestMapping(value = "/ajax/role/{id}", method = GET)
	public Role read(@PathVariable("id") int id) {
		return roleService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/role", method = RequestMethod.POST)
	public GridRowResponse create(@Valid Role role) {
		roleService.save(role);
		return new  GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid Role role, @PathVariable("id") int id) {
		role.setOrgId(id);
		roleService.update(role);
		return new  GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		roleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/role/select", method =RequestMethod.GET )
	public List<Role> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Role> roleList = roleService.findAll();
		return roleList;
	}
}
