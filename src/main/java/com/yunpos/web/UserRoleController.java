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
import com.yunpos.model.UserRole;
import com.yunpos.service.UserRoleService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

@RestController
public class UserRoleController extends BaseController{
	@Autowired
	private UserRoleService userRoleService;

	
	@RequestMapping(value="/ajax/userRole",method = GET)
	public JqGridResponse<UserRole> list(JqGridRequest jqGridRequest)  throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<UserRole> dataResponse = userRoleService.findPageUsers(gridRequest);
		return new JqGridResponse<UserRole>(dataResponse);
	}

	
	@RequestMapping(value = "/ajax/userRole/{id}", method = GET)
	public UserRole read(@PathVariable("id") int id) {
		return userRoleService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid UserRole userRole, @PathVariable("id") int id) {
		userRole.setUserId(id);
		userRole.setModifyDate(new Date());
		userRole.setModifyUserId(this.getUser().getId());
		userRoleService.update(userRole);
		return new GridRowResponse(userRole.getId());
	}
	
	@RequestMapping(value = "/ajax/userRole", method = RequestMethod.POST)
	public GridRowResponse create(@Valid UserRole userRole) {
		userRole.setCreateDate(new Date());
		userRole.setCreateUserId(this.getUser().getId());
		userRoleService.save(userRole);
		return new  GridRowResponse(userRole.getId());
	}

	@RequestMapping(value = "/ajax/userRole/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		userRoleService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/userRole/select", method =RequestMethod.GET )
	public List<UserRole> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<UserRole> roleList = userRoleService.findAll();
		return roleList;
	}
}
