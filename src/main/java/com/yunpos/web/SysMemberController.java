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
import com.yunpos.model.SysMember;
import com.yunpos.service.SysMemberService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：会员信息控制器
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
public class SysMemberController extends BaseController{
	@Autowired
	private   SysMemberService sysMemberService;
	
	@RequestMapping(value="/ajax/member",method = GET)
	public List<SysMember> list()throws ServiceException{
		return sysMemberService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/member/search",method = GET)
	public JqGridResponse<SysMember> search(SysMember sysMember)throws ServiceException{
		GridResponse<SysMember> dataResponse = sysMemberService.search(sysMember);
		return new JqGridResponse<SysMember>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/member/{id}", method = GET)
	public SysMember read(@PathVariable("id") int id) {
		return sysMemberService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/member", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMember sysMember) {
		sysMemberService.save(sysMember);
		return new GridRowResponse(sysMember.getId());
	}

	@RequestMapping(value = "/ajax/member/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMember sysMember, @PathVariable("id") int id) {
		sysMember.setId(id);
		sysMemberService.update(sysMember);
		return new GridRowResponse(sysMember.getId());
	}

	@RequestMapping(value = "/ajax/member/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMemberService.delete(id);
	}
}
