package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysOrder;
import com.yunpos.service.SysOrderService;
import com.yunpos.utils.ExcelUtils;
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

	
	@RequestMapping(value = "/ajax/order/exportExcel")
	public void exportExcel(HttpServletRequest request , HttpServletResponse response) {
		String filename = "1111";
		String sheetName = "2222";
		List<String> columns = new ArrayList();
		columns.add("订单号");
		columns.add("下单时间");
		columns.add("订单金额");
		columns.add("交易状态");
		columns.add("商户");
		columns.add("所属行业");
		columns.add("来源类型");
		columns.add("支付流水号");
		List<SysOrder> list = sysOrderService.findAll();
		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		for(SysOrder sysOrder : list){
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("orderId", sysOrder.getOrderId());
			map.put("createdAt", sysOrder.getCreatedAt().toString());
			map.put("totalPrice", sysOrder.getTotalPrice());
			map.put("payStatus", sysOrder.getPayStatus());
			map.put("MerchantSerialNo", sysOrder.getSysMerchantSerialNo());
			listData.add(map);
		}
		ExcelUtils.exportexcle(response, filename, listData, sheetName, columns);  

	}
}
