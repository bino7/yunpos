package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysFans;
import com.yunpos.service.IndustryService;
import com.yunpos.service.SysFansService;
import com.yunpos.service.SysOrgService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
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
public class SysFansController extends BaseController{
	@Autowired
	private  SysFansService sysFansService;

	/**所以商户信息
	 * 商户
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/Fans",method = GET)
	public List<SysFans> list()throws ServiceException{
		return sysFansService.findAll();
		
	}
	
	/**
	 * 查询商户列表
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/Fans/search",method = GET)
	public JqGridResponse<SysFans> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysFans> dataResponse = sysFansService.search(gridRequest);
		return new JqGridResponse<SysFans>(dataResponse);
	}
	
	/**
	 * 商户详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/Fans/{id}", method = GET)
	public SysFans read(@PathVariable("id") int id) {
		return sysFansService.findById(id);
	}
	
	
}
