package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpos.model.Privilege;
import com.yunpos.model.ViewPage;
import com.yunpos.service.PrivilegeService;

/**
 * 
 * 功能描述：权限资源控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@Controller
public class PrivilegeController extends BaseController{
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(value="/ajax/privilege",method = GET)
	public @ResponseBody ViewPage<Privilege> list() {
		ViewPage<Privilege> viewPage = new ViewPage<Privilege>();
		List<Privilege> list = privilegeService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}
	
	@RequestMapping(value = "/ajax/privilege/{id}", method = GET)
	public @ResponseBody  Privilege read(@PathVariable("id") int id) {
		return privilegeService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/privilege", method = RequestMethod.POST)
	public void create(@Valid Privilege privilege) {
		privilegeService.save(privilege);
	}

	@RequestMapping(value = "/ajax/privilege/{id}", method = RequestMethod.PUT)
	public void update(@Valid Privilege privilege, @PathVariable("id") int id) {
		privilege.setId(id);
		privilegeService.update(privilege);
	}

	@RequestMapping(value = "/ajax/privilege/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		privilegeService.delete(id);
	}
}
