package com.yunpos.web.card;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.service.card.SysCardTemplateService;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;
import com.yunpos.web.BaseController;

/**
 * 
 * 功能描述：卡券信息控制器 
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
public class SysCardTemplateController extends BaseController {
	
	@Autowired
	private  SysCardTemplateService sysCardTemplateService;
	
	/**
	 * 卡券列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/cardTemplate",method = GET)
	public List<SysCardTemplate> list()throws ServiceException{
		return sysCardTemplateService.findAll();
		
	}
	
	/**
	 * 卡券分页查询查询
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/sysCardTemplate/search",method = GET)
	public JqGridResponse<SysCardTemplate> search(SysCardTemplate sysCardTemplate)throws ServiceException{
		GridResponse<SysCardTemplate> dataResponse = sysCardTemplateService.search(sysCardTemplate);
		return new JqGridResponse<SysCardTemplate>(dataResponse);
	}
	
	/**
	 * 卡券详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = GET)
	public SysCardTemplate read(@PathVariable("id") int id) {
		return sysCardTemplateService.findById(id);
	}
	
	/**
	 * 新增卡券信息，现在卡券用户信息
	 * @param sysCardTemplate
	 * @return
	 */
	@RequestMapping(value = "/ajax/cardTemplate", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysCardTemplate sysCardTemplate) {
		sysCardTemplateService.save(sysCardTemplate);

		return new GridRowResponse(-2);
	}

	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardTemplate
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysCardTemplate sysCardTemplate, @PathVariable("id") int id) {
		if(!Tools.isNullOrEmpty(sysCardTemplate)){
			sysCardTemplate.setId(id);
			sysCardTemplateService.update(sysCardTemplate);
		}
		return new GridRowResponse(sysCardTemplate.getId());
	}

	
	
	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardTemplate
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/updateStatus/{id}", method = RequestMethod.PUT)
	public GridRowResponse updateStatus(@Valid SysCardTemplate sysCardTemplate, @PathVariable("id") int id) {
		SysCardTemplate sCardTemplate = sysCardTemplateService.findById(id);
		 sysCardTemplateService.update(sCardTemplate);
		return new GridRowResponse(sCardTemplate.getId());
	}
	
	/**
	 * 卡券删除
	 * @param id
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysCardTemplateService.delete(id);
	}
	
	
}
