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
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.service.SysAgentMerchantService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：代理商信息控制器 
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
public class SysAgentMerchantController {
	
	@Autowired
	private  SysAgentMerchantService sysAgentMerchantService;
	
	@RequestMapping(value="/ajax/agentmerchant",method = GET)
	public List<SysAgentMerchant> list()throws ServiceException{
		return sysAgentMerchantService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/agentmerchant/search",method = GET)
	public JqGridResponse<SysAgentMerchant> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysAgentMerchant> dataResponse = sysAgentMerchantService.search(gridRequest);
		return new JqGridResponse<SysAgentMerchant>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = GET)
	public SysAgentMerchant read(@PathVariable("id") int id) {
		return sysAgentMerchantService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/agentmerchant", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysAgentMerchant sysAgentMerchant) {
		sysAgentMerchantService.save(sysAgentMerchant);
		return new GridRowResponse(sysAgentMerchant.getId());
	}

	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysAgentMerchant sysAgentMerchant, @PathVariable("id") int id) {
		sysAgentMerchant.setId(id);
		sysAgentMerchantService.update(sysAgentMerchant);
		return new GridRowResponse(sysAgentMerchant.getId());
	}

	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysAgentMerchantService.delete(id);
	}
	


}
