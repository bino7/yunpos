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
import com.yunpos.model.card.SysMembercardTemplate;
import com.yunpos.service.card.SysMembercardTemplateService;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.web.BaseController;

/**
 * 会员卡
 */
@RestController
public class SysMembercardTemplateController extends BaseController {
	
	@Autowired
	private  SysMembercardTemplateService sysMembercardTemplateService;
	
	/**
	 * 会员卡列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/membercardTemplate",method = GET)
	public List<SysMembercardTemplate> list()throws ServiceException{
		return sysMembercardTemplateService.findAll();
		
	}
	
	/**
	 * 会员卡详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/membercardTemplate/{id}", method = GET)
	public SysMembercardTemplate read(@PathVariable("id") int id) {
		return sysMembercardTemplateService.findById(id);
	}
	
	/**
	 * 新增会员卡
	 * @param sysCardTemplate
	 * @return
	 */
	@RequestMapping(value = "/ajax/membercardTemplate", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMembercardTemplate sysMembercardTemplate) {
		sysMembercardTemplateService.save(sysMembercardTemplate);
		return new GridRowResponse(-2);
	}
}
