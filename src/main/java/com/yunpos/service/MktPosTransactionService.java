package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.MktPosTransaction;
import com.yunpos.model.SysOrder;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.MktPosTransactionMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
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
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<MktPosTransaction> findByParms(MktPosTransaction mktPosTransaction) {
		List<MktPosTransaction> list = mktPosTransactionMapper.selectByParm(mktPosTransaction);
		return list;
	}

}
