package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
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
import com.yunpos.model.SysUserRole;
import com.yunpos.service.SysUserRoleService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

@RestController
public class SysUserRoleController extends BaseController{
	@Autowired
	private SysUserRoleService sysUserRoleService;

	
	@RequestMapping(value="/ajax/userRole",method = GET)
	public JqGridResponse<SysUserRole> list(JqGridRequest jqGridRequest)  throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysUserRole> dataResponse = sysUserRoleService.findPageUsers(gridRequest);
		return new JqGridResponse<SysUserRole>(dataResponse);
	}

	
	@RequestMapping(value = "/ajax/userRole/{id}", method = GET)
	public SysUserRole read(@PathVariable("id") int id) {
		return sysUserRoleService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysUserRole userRole, @PathVariable("id") int id) {
		userRole.setUserId(id);
		userRole.setModifyDate(new Date());
		//userRole.setModifyUserId(this.getUser().getId());
		sysUserRoleService.update(userRole);
		return new GridRowResponse(userRole.getId());
	}
	
	@RequestMapping(value = "/ajax/userRole", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysUserRole userRole) {
		userRole.setCreateDate(new Date());
		//userRole.setCreateUserId(this.getUser().getId());
		sysUserRoleService.save(userRole);
		return new  GridRowResponse(userRole.getId());
	}

	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysUserRoleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/select", method =RequestMethod.GET )
	public List<SysUserRole> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUserRole> roleList = sysUserRoleService.findAll();
		return roleList;
	}
}
