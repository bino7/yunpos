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
import com.yunpos.model.SysPay;
import com.yunpos.service.SysPayService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年9月24日
 * @author Devin_Yang 修改日期：2015年9月24日
 *
 */

@RestController
public class SysPayController extends BaseController {
	@Autowired
	private SysPayService sysPayService;

	
	@RequestMapping(value="/ajax/pay",method = RequestMethod.GET)
	
	public JqGridResponse<SysPay> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysPay> dataResponse = sysPayService.findPageUsers(gridRequest);
		return new JqGridResponse<SysPay>(dataResponse);
	}
	
	
	@RequestMapping(value = "/ajax/pay/{id}", method = RequestMethod.GET)
	public SysPay read(@PathVariable("id") int id) throws ServiceException{
		return sysPayService.findById(id);
	}
	
	@RequestMapping(value="/ajax/pay/search",method = GET)
	public JqGridResponse<SysPay> search(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysPay> dataResponse = sysPayService.search(gridRequest);
		return new JqGridResponse<SysPay>(dataResponse);
	}

	@RequestMapping(value="/ajax/pay",method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysPay sysPay)throws ServiceException {
		sysPayService.save(sysPay);
		return new GridRowResponse(sysPay.getId());
	}

	@RequestMapping(value = "/ajax/pay/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysPay sysPay, @PathVariable("id") int id) {
		sysPay.setId(id);
		sysPayService.update(sysPay);
		return new GridRowResponse(sysPay.getId());
	}

	/**
	 * 删除用户需要将对应关联的角色删除
	 * @param id
	 */
	@RequestMapping(value = "/ajax/pay/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysPayService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/pay/select", method =RequestMethod.GET )
	public List<SysPay> getSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysPay> list = sysPayService.findAll();
		return list;
	}
	

}
