package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.MktPosTransaction;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysTransaction;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.MktPosTransactionMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;
@Service
public class MktPosTransactionService extends EntityService<MktPosTransaction> {
	@Autowired
	private MktPosTransactionMapper mktPosTransactionMapper;
	
	@Override
	public EntityMapper<MktPosTransaction> getMapper() {
		return mktPosTransactionMapper;
	}

	public List<MktPosTransaction> findAll() {
		// TODO Auto-generated method stub
		return mktPosTransactionMapper.findAll();
	}

	/**
	 * 根据订单流水参数查询
	 * @param mktPosTransaction
	 * @return
	 */
	public List<MktPosTransaction> findByParms(MktPosTransaction mktPosTransaction) {
		List<MktPosTransaction> list = mktPosTransactionMapper.selectByParm(mktPosTransaction);
		return list;
	}
	
	/**
	 * 根据订单流水参数查询
	 * @param mktPosTransaction
	 * @return
	 */
	public int findCountByParms(MktPosTransaction mktPosTransaction) {
		int count = mktPosTransactionMapper.selectCountByParm(mktPosTransaction);
		return count;
	}
	
	/**
	 * 根据订单流水参数分页查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public JqGridResponse<MktPosTransaction> findPageDataByParms(MktPosTransaction mktPosTransaction) {
		List<MktPosTransaction> list = findByParms(mktPosTransaction);
		GridResponse<MktPosTransaction> response = new GridResponse<MktPosTransaction>();
		response.setPageNumber(mktPosTransaction.getPageNumber());
		response.setPageSize(mktPosTransaction.getPageSize());
		response.setRows(list);
		response.setTotalRowCount(findCountByParms(mktPosTransaction));
		return new JqGridResponse<MktPosTransaction>(response);
	}

}
