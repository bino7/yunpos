package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysPrivilege;
import com.yunpos.service.SysPrivilegeService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

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
@RestController
public class SysPrivilegeController extends BaseController{
	@Autowired
	private SysPrivilegeService sysPrivilegeService;
	
	@RequestMapping(value="/ajax/privilege",method = GET)
	public JqGridResponse<SysPrivilege> list(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysPrivilege> dataResponse = sysPrivilegeService.findPageUsers(gridRequest);
		return new JqGridResponse<SysPrivilege>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/privilege/{id}", method = GET)
	public SysPrivilege read(@PathVariable("id") int id) {
		return sysPrivilegeService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/privilege", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysPrivilege privilege) {
		sysPrivilegeService.save(privilege);
		return new GridRowResponse(privilege.getId());
	}

	@RequestMapping(value = "/ajax/privilege/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysPrivilege privilege, @PathVariable("id") int id) {
		privilege.setId(id);
		sysPrivilegeService.update(privilege);
		return new GridRowResponse(privilege.getId());
	}

	@RequestMapping(value = "/ajax/privilege/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysPrivilegeService.delete(id);
	}
}
