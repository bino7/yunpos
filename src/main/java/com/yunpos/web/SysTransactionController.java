package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysTransaction;
import com.yunpos.service.SysTransactionService;
import com.yunpos.utils.jqgrid.GridRowResponse;
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
public class SysTransactionController extends BaseController {
	@Autowired
	private SysTransactionService  sysTransactionService;
	

	/**
	 * 流水查询
	 * @param sysTransaction
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/ajax/transaction/search", method = RequestMethod.GET)
	public JqGridResponse<SysTransaction> search(SysTransaction sysTransaction) throws ServiceException {
		JqGridResponse<SysTransaction> list = sysTransactionService.findPageDataByParms(sysTransaction);
		return list;
	}
	
	@RequestMapping(value = "/ajax/transaction/{id}", method = GET)
	public SysTransaction read(@PathVariable("id") int id) {
		return sysTransactionService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/transaction", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysTransaction sysTransaction) {
		sysTransactionService.save(sysTransaction);
		return new GridRowResponse(sysTransaction.getId());
	}
	
	@RequestMapping(value = "/ajax/transaction/update/{id}")
	public GridRowResponse update(@Valid SysTransaction sysTransaction, @PathVariable("id") int id) {
		sysTransaction.setId(id);
		sysTransactionService.update(sysTransaction);
		return new GridRowResponse(sysTransaction.getId());
	}
	
	@RequestMapping(value = "/ajax/transaction/updateByTransNum/{transNum}")
	public GridRowResponse updateByTransNum(@Valid SysTransaction sysTransaction, @PathVariable("transNum") String transNum) {
		SysTransaction	trans = sysTransactionService.findByTransNum(transNum);
		sysTransaction.setId(trans.getId());
		sysTransactionService.update(sysTransaction);
		return new GridRowResponse(sysTransaction.getId());
	}
}
	
	
