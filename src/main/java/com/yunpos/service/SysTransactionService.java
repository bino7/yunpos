package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SearchRespose;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysTransaction;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysTransactionMapper;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;

@Service
public class SysTransactionService extends EntityService<SysTransaction> {
	@Autowired
	private SysTransactionMapper sysTransactionMapper;

	@Override
	public EntityMapper<SysTransaction> getMapper() {
		return sysTransactionMapper;
	}

	public SearchRespose<SysTransaction> search(SearchRequest searchRequest) {
		List<SysTransaction> list = sysTransactionMapper.findByCondition(searchRequest);
		return new SearchRespose<>(searchRequest.getPage(), searchRequest.getRows(), list.size(), list);
	}
	
	
	public SysTransaction findByTransNum(String orderNo) {
		return sysTransactionMapper.findByTransNum(orderNo);
	}

	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<SysTransaction> findByParms(SysTransaction sysTransaction) {
		List<SysTransaction> list = sysTransactionMapper.selectByParm(sysTransaction);
		return list;
	}
	
	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public int findCountByParms(SysTransaction sysTransaction) {
		int count = sysTransactionMapper.selectCountByParm(sysTransaction);
		return count;
	}
	
	/**
	 * 根据代理商参数分页查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public JqGridResponse<SysTransaction> findPageDataByParms(SysTransaction sysTransaction) {
		List<SysTransaction> list = findByParms(sysTransaction);
		GridResponse<SysTransaction> response = new GridResponse<SysTransaction>();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(list);
		response.setTotalRowCount(findCountByParms(sysTransaction));
		return new JqGridResponse<SysTransaction>(response);
	}
}
