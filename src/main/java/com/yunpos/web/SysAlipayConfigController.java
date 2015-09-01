package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.model.SysAlipayConfigWithBLOBs;
import com.yunpos.service.SysAlipayConfigService;
import com.yunpos.utils.jqgrid.GridRowResponse;

/**
 * 
 * 功能描述：支付宝配置信息控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年9月1日
 * @author Devin_Yang 修改日期：2015年9月1日
 *
 */
@RestController
public class SysAlipayConfigController {
	
	@Autowired
	private SysAlipayConfigService sysAlipayConfigService;
	

	
	@RequestMapping(value = "/ajax/alipayconfig/{id}", method = GET)
	public SysAlipayConfigWithBLOBs read(@PathVariable("id") int id) {
		return sysAlipayConfigService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/alipayconfig", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysAlipayConfigWithBLOBs sysalipayconfig) {
		sysAlipayConfigService.save(sysalipayconfig);
		return new  GridRowResponse(sysalipayconfig.getId());
	}

	@RequestMapping(value = "/ajax/alipayconfig/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysAlipayConfigWithBLOBs sysalipayconfig, @PathVariable("id") int id) {
		sysalipayconfig.setId(id);
		sysAlipayConfigService.update(sysalipayconfig);
		return new GridRowResponse(sysalipayconfig.getId());
		
	}

	@RequestMapping(value = "/ajax/alipayconfig/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysAlipayConfigService.delete(id);
	}
	


}
