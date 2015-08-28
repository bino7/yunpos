package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SearchRequest;
import com.yunpos.model.SearchRespose;
import com.yunpos.model.SysRole;
import com.yunpos.model.SysTransaction;
import com.yunpos.service.SysTransactionService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：交易流水控制器
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
public class SysTransactionController {
	@Autowired
	private SysTransactionService  sysTransactionService;
	


	@RequestMapping(value = "/ajax/transaction/search", method = RequestMethod.GET)
	public SearchRespose<SysTransaction> search(SearchRequest searchRequest) throws ServiceException {
		SearchRespose<SysTransaction> dataResponse = sysTransactionService.search(searchRequest);
		return dataResponse;
	}
	
	@RequestMapping(value = "/ajax/transaction/{id}", method = GET)
	public SysTransaction read(@PathVariable("id") int id) {
		return sysTransactionService.findById(id);
	}
	


}
	
	
