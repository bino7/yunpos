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
import com.yunpos.model.SysApp;
import com.yunpos.service.SysAppService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：app资源控制器
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
public class SysAppController extends BaseController{
	
	@Autowired
	private  SysAppService sysAppService;
	
	@RequestMapping(value="/ajax/app",method = GET)
	public JqGridResponse<SysApp> list(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysApp> dataResponse = sysAppService.findPageUsers(gridRequest);
		return new JqGridResponse<SysApp>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/app/{id}", method = GET)
	public SysApp read(@PathVariable("id") int id) {
		return sysAppService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/app", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysApp sysApp) {
		sysAppService.save(sysApp);
		return new GridRowResponse(sysApp.getId());
	}

	@RequestMapping(value = "/ajax/app/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysApp sysApp, @PathVariable("id") int id) {
		sysApp.setId(id);
		sysAppService.update(sysApp);
		return new GridRowResponse(sysApp.getId());
	}

	@RequestMapping(value = "/ajax/app/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysAppService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/app/select", method =RequestMethod.GET )
	public List<SysApp> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysApp> roleList = sysAppService.findAll();
		return roleList;
	}
}
