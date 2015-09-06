package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysOrder;
import com.yunpos.service.SysOrderService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：订单信息控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月28日
 * @author Devin_Yang 修改日期：2015年8月28日
 *
 */
@RestController
public class SysOrderController {
	@Autowired
	private SysOrderService sysOrderService;
	
	@RequestMapping(value="/ajax/order",method = GET)
	public List<SysOrder> list()throws ServiceException{
		return sysOrderService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/order/search",method = GET)
	public JqGridResponse<SysOrder> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysOrder> dataResponse = sysOrderService.search(gridRequest);
		return new JqGridResponse<SysOrder>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/order/{id}", method = GET)
	public SysOrder read(@PathVariable("id") int id) {
		return sysOrderService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/order", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysOrder sysOrder) {
		sysOrderService.save(sysOrder);
		return new GridRowResponse(sysOrder.getId());
	}

	@RequestMapping(value = "/ajax/order/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysOrder sysOrder, @PathVariable("id") int id) {
		sysOrder.setId(id);
		sysOrderService.update(sysOrder);
		return new GridRowResponse(sysOrder.getId());
	}

	@RequestMapping(value = "/ajax/order/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysOrderService.delete(id);
	}

}
