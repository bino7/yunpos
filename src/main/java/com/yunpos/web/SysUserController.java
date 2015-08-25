package com.yunpos.web;

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

import com.google.common.base.Strings;
import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysUser;
import com.yunpos.model.SysUserRole;
import com.yunpos.security.SecurityUtils;
import com.yunpos.service.SysUserRoleService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;
@RestController
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@RequestMapping(value="/ajax/user",method = RequestMethod.GET)
	
	public JqGridResponse<SysUser> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysUser> dataResponse = sysUserService.findPageUsers(gridRequest);
		return new JqGridResponse<SysUser>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.GET)
	public SysUser read(@PathVariable("id") int id) throws ServiceException{
		return sysUserService.findById(id);
	}

	@RequestMapping(value="/ajax/user",method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysUser user)throws ServiceException {
		SysUser sysUser = sysUserService.findById(Integer.parseInt(user.getLoginId()));
		user.setCreatedAt(new Date());
		user.setCreatedBy(sysUser.getId());
		user.setStatus("1");
		sysUserService.save(user);
		
		if(!Strings.isNullOrEmpty(user.getRole())){
			String[] roleIds = user.getRole().split(",");
			SysUserRole sysUserRole = null;
			for(int i=0;i<roleIds.length;i++){
				sysUserRole = new SysUserRole();
				sysUserRole.setUserId(user.getId());
				sysUserRole.setRoleId(Integer.valueOf(roleIds[i]));
				sysUserRoleService.insert(sysUserRole);
			}
		}
		return new  GridRowResponse(user.getId());
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysUser user, @PathVariable("id") int id) {
		user.setId(id);
		user.setUpdatedAt(new Date());
		//user.setUpdatedBy(getUser().getId());
		user.setSalt(SecurityUtils.generateSalt());
		sysUserService.update(user);
		return new GridRowResponse(user.getId());
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysUserService.delete(id);
	}
	
	
	@RequestMapping(value = "/ajax/user/select", method =RequestMethod.GET )
	public List<SysUser> getUserSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUser> list = sysUserService.findAll();
		return list;
	}
	
	/**
	 * 
	 * @param request
	 * @param type  name/email/phone
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/user/exist/{type}/{value}", method =RequestMethod.GET )
	public Object exist(HttpServletRequest request, @PathVariable("type") String type,@PathVariable("value") String value) throws Exception {
		boolean flag = false;
		if(type.equals("name")){
			flag = sysUserService.userNameExist(value);
		}else if (type.equals("email")){
			flag = sysUserService.emailExist(value);
		}else if(type.equals("phone")){
			flag = sysUserService.phoneExist(value);
		}
		return flag;
	}

}
