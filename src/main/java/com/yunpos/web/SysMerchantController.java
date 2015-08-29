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
import com.yunpos.model.SysMerchant;
import com.yunpos.service.SysMerchantService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：商户信息控制器
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
public class SysMerchantController extends BaseController{
	@Autowired
	private  SysMerchantService sysMerchantService;
	
	@RequestMapping(value="/ajax/merchant",method = GET)
	public List<SysMerchant> list()throws ServiceException{
		return sysMerchantService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/merchant/search",method = GET)
	public JqGridResponse<SysMerchant> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysMerchant> dataResponse = sysMerchantService.search(gridRequest);
		return new JqGridResponse<SysMerchant>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/merchant/{id}", method = GET)
	public SysMerchant read(@PathVariable("id") int id) {
		return sysMerchantService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/merchant", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMerchant sysMerchant) {
		sysMerchantService.save(sysMerchant);
		return new GridRowResponse(sysMerchant.getId());
	}

	@RequestMapping(value = "/ajax/merchant/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMerchant sysMerchant, @PathVariable("id") int id) {
		sysMerchant.setId(id);
		sysMerchantService.update(sysMerchant);
		return new GridRowResponse(sysMerchant.getId());
	}

	@RequestMapping(value = "/ajax/merchant/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMerchantService.delete(id);
	}
}
