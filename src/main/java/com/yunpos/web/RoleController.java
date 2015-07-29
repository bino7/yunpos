package com.yunpos.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.Role;
import com.yunpos.model.ViewPage;
import com.yunpos.service.RoleService;
import com.yunpos.utils.PageDate;

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
	
	
	@RequestMapping(value = "/operate", method = { RequestMethod.POST, RequestMethod.GET })
	public void operate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDate pageParam = this.getPageParam();
		String oper = pageParam.getString("oper");
		String id = pageParam.getString("id");
		if (oper.equals("del") && !Strings.isNullOrEmpty(id)) {
			String[] ids = id.split(",");
			roleService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			Role role = null;
			if (oper.equals("edit")) {
				role = roleService.findById(Integer.valueOf(id));
			}
			Role entity = new Role();
			entity.setCreateDate(new Date());
			entity.setCreateUserId(1);
			
			if(!Strings.isNullOrEmpty(pageParam.getString("orgId"))){
				entity.setOrgId(Integer.valueOf(pageParam.getString("orgId")));
			}
			entity.setRoleDesc(pageParam.getString("roleDesc"));
			entity.setRoleName(pageParam.getString("roleName"));
			
			
			if (oper.equals("edit")) {
				entity.setRoleId(Integer.valueOf(role.getRoleId()));
				roleService.update(entity);
			} else if (oper.equals("add")) {
				roleService.save(entity);
				
			}
		}
	}
}
