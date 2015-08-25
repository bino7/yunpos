package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysMenu;
import com.yunpos.model.SysRole;
import com.yunpos.model.SysUser;
import com.yunpos.model.SysUserRole;
import com.yunpos.service.SysRoleService;
import com.yunpos.service.SysUserRoleService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

@RestController
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajax/role", method = RequestMethod.GET)
	public JqGridResponse<SysRole> list(JqGridRequest jqGridRequest) throws ServiceException {
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
		return new GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysRole role, @PathVariable("id") int id) {
		role.setOrgId(id);
		sysRoleService.update(role);
		return new GridRowResponse(role.getId());
	}

	@RequestMapping(value = "/ajax/role/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") int id) {
		sysRoleService.delete(id);
	}

	@RequestMapping(value = "/ajax/role/select", method = RequestMethod.GET)
	public List<SysRole> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysRole> roleList = sysRoleService.findAll();
		return roleList;
	}

	@RequestMapping(value = "/ajax/role/exist/{value}", method = RequestMethod.GET)
	public Object exist(HttpServletRequest request, @PathVariable("value") String value) throws Exception {
		return sysRoleService.existRoleName(value);
	}


	//查看角色对应的用户
	@RequestMapping(value = "/ajax/role/listUser/{roleId}", method = RequestMethod.GET)
	public Object listUser(HttpServletRequest request, @PathVariable("roleId") String roleId) throws Exception {
		List<SysUserRole> list = sysUserRoleService.findUserRoleByRoleId(Integer.valueOf(roleId));
		List<Integer> ids = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (SysUserRole sysUserRole : list) {
				ids.add(sysUserRole.getUserId());
			}
		}
		return sysUserService.findListByIds(ids.toArray());
	}
	
	//通过组织机构id获取角色列表
	@RequestMapping(value = "/ajax/role/listRole/{orgId}", method = RequestMethod.GET)
	public Object listRole(HttpServletRequest request, @PathVariable("orgId") String orgId) throws Exception {
		return sysRoleService.findByOrgId(Integer.valueOf(orgId));
	}
	
	

}
