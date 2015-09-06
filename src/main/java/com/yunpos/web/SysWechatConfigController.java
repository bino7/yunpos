package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.service.SysWechatConfigService;
import com.yunpos.utils.jqgrid.GridRowResponse;

/**
 * 
 * 功能描述：微信配置信息控制器
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
public class SysWechatConfigController {
	
	@Autowired
	private SysWechatConfigService sysWechatConfigService;
	

	
	@RequestMapping(value = "/ajax/wechatconfig/{id}", method = GET)
	public SysWechatConfigWithBLOBs read(@PathVariable("id") int id) {
		return sysWechatConfigService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/wechatconfig", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysWechatConfigWithBLOBs sysWechatConfig) {
		sysWechatConfigService.save(sysWechatConfig);
		return new  GridRowResponse(sysWechatConfig.getId());
	}

	@RequestMapping(value = "/ajax/wechatconfig/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysWechatConfigWithBLOBs sysWechatConfig, @PathVariable("id") int id) {
		sysWechatConfig.setId(id);
		sysWechatConfigService.update(sysWechatConfig);
		return new GridRowResponse(sysWechatConfig.getId());
		
	}

	@RequestMapping(value = "/ajax/wechatconfig/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysWechatConfigService.delete(id);
	}
	


}
