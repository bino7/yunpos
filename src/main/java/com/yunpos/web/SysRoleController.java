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
import com.yunpos.model.SysRole;
import com.yunpos.service.SysRoleService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

@RestController
public class SysRoleController extends BaseController{
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(value="/ajax/role",method =RequestMethod.GET)
	public JqGridResponse<SysRole> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysRole> dataResponse = sysRoleService.findPageUsers(gridRequest);
		return new JqGridResponse<SysRole>(dataResponse);
	}

	@RequestMapping(value = "/ajax/role/{id}", method = GET)
	public SysRole read(@PathVariable("id") int id) {
		return sysRoleService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/role", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysRole role) {
		sysRoleService.save(role);
		return new  GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysRole role, @PathVariable("id") int id) {
		role.setOrgId(id);
		sysRoleService.update(role);
		return new  GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysRoleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/role/select", method =RequestMethod.GET )
	public List<SysRole> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysRole> roleList = sysRoleService.findAll();
		return roleList;
	}
}
