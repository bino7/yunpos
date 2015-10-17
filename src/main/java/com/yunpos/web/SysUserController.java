package com.yunpos.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
	
	@RequestMapping(value="/ajax/user/search",method = RequestMethod.GET)
	public JqGridResponse<SysUser> search(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysUser> dataResponse = sysUserService.search(gridRequest);
		return new JqGridResponse<SysUser>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.GET)
	public SysUser read(@PathVariable("id") int id) throws ServiceException{
		return sysUserService.findById(id);
	}

	@RequestMapping(value="/ajax/user",method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysUser user,HttpServletRequest request)throws ServiceException {
		
		SysUser currentUser = (SysUser) request.getSession().getAttribute("user");
		user.setCreatedAt(new Date());
		user.setCreatedBy(user.getId());
		user.setOrgId(user.getOrgId());
		user.setOrgName(user.getOrgName());
		user.setUserName(user.getUserName());
		user.setNickname(user.getNickname());
		
		user.setFullname(user.getFullname());
		user.setPhone(user.getPhone());
		user.setDescription(user.getDescription());
		user.setEmail(user.getEmail());
		user.setDelete_status(0);
		user.setStatus(1);
		user.setPassword(DigestUtils.md5DigestAsHex(user.getNewPassword().getBytes()));
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
		user.setUpdatedBy(getUser().getId());
		user.setStatus(user.getStatus());
		user.setSalt(SecurityUtils.generateSalt());		
//		if(user.getPassword()!=null && !"".equals(user.getPassword()))
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		sysUserService.update(user);
		
		if(!Strings.isNullOrEmpty(user.getRole())){
			String[] roleIds = user.getRole().split(",");
			SysUserRole sysUserRole = null;
			sysUserRoleService.deleteByUserId(id);
			for(int i=0;i<roleIds.length;i++){
				sysUserRole = new SysUserRole();
				sysUserRole.setUserId(user.getId());
				sysUserRole.setCreateDate(new Date());
				sysUserRole.setCreateUserId(getUser().getId());
				sysUserRole.setRoleId(Integer.valueOf(roleIds[i]));
				sysUserRoleService.insert(sysUserRole);
			}
		}
		return new GridRowResponse(user.getId());
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/user/updatePwd/{id}", method = RequestMethod.PUT)
	public GridRowResponse updatePWD(@Valid SysUser user, @PathVariable("id") int id) {
		SysUser sysUser = sysUserService.findById(id);
		sysUser.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		sysUserService.update(user);
		return new GridRowResponse(user.getId());
	}

	

	/**
	 * 删除用户需要将对应关联的角色删除
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.DELETE)
	public GridRowResponse delete(@Valid SysUser user,@PathVariable("id") int id) {
		SysUser sysUser = sysUserService.findById(id);
		user.setDelete_status(1);
		sysUserService.update(user);
		return new GridRowResponse(user.getId());
		/*sysUserService.delete(id);
		sysUserRoleService.deleteByUserId(id);*/
		
		
	}
	
	@RequestMapping(value = "/ajax/user/deleteUserRole/{id}", method = RequestMethod.DELETE)
	public void delete1(@PathVariable("id") int id) {
		sysUserRoleService.deleteByUserId(id);
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
