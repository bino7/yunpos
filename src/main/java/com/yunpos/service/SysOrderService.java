package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysOrder;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysOrderMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysOrderService extends EntityService<SysOrder> {
	@Autowired
	private SysOrderMapper sysOrderMapper;
	
	@Override
	public EntityMapper<SysOrder> getMapper() {
		return sysOrderMapper;
	}

	public List<SysOrder> findAll() {
		// TODO Auto-generated method stub
		return sysOrderMapper.findAll();
	}
	
	public GridResponse<SysOrder> search(GridRequest gridRequest) {
		GridResponse<SysOrder> response = new GridResponse<SysOrder>();
		List<SysOrder> sysOrder =  sysOrderMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysOrder);
		response.setTotalRowCount(sysOrder.size());
		return response;
	}
	
	public SysOrder findByshopIdAndOrderid(String shopId, String orderId) {
		return sysOrderMapper.findByshopIdAndOrderid(shopId, orderId);
	}
	

	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<SysOrder> findByParms(SysOrder sysOrder) {
		List<SysOrder> list = sysOrderMapper.selectByParm(sysOrder);
		return list;
	}
}
