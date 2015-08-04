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
import com.yunpos.model.SysButtonWithBLOBs;
import com.yunpos.service.SysButtonService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：按钮资源控制器
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
public class SysButtonController extends BaseController{
	@Autowired
	private SysButtonService sysButtonService;
	
	
	@RequestMapping(value="/ajax/button",method = GET)
	public JqGridResponse<SysButtonWithBLOBs> list(JqGridRequest jqGridRequest)throws ServiceException {
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysButtonWithBLOBs> dataResponse = sysButtonService.findPageUsers(gridRequest);
		return new JqGridResponse<SysButtonWithBLOBs>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/button/{id}", method = GET)
	public SysButtonWithBLOBs read(@PathVariable("id") int id) {
		return sysButtonService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/button", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysButtonWithBLOBs sysButton) {
		sysButtonService.save(sysButton);
		return new  GridRowResponse(sysButton.getId());
	}

	@RequestMapping(value = "/ajax/button/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysButtonWithBLOBs sysButton, @PathVariable("id") int id) {
		sysButton.setId(id);
		sysButtonService.update(sysButton);
		return new GridRowResponse(sysButton.getId());
		
	}

	@RequestMapping(value = "/ajax/button/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysButtonService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/button/select", method =RequestMethod.GET )
	public List<SysButtonWithBLOBs> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysButtonWithBLOBs> roleList = sysButtonService.findAll();
		return roleList;
	}

}
