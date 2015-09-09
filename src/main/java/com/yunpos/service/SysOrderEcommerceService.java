package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysOrder;
import com.yunpos.model.SysOrderEcommerce;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysOrderEcommerceMapper;
import com.yunpos.persistence.dao.SysOrderMapper;

@Service
public class SysOrderEcommerceService extends EntityService<SysOrderEcommerce> {
	@Autowired
	private SysOrderEcommerceMapper SysOrderEcommerceMapper;
	
	@Autowired
	private SysOrderMapper sysOrderMapper;
	
	@Override
	public EntityMapper<SysOrderEcommerce> getMapper() {
		return SysOrderEcommerceMapper;
	}
	
	public void insertOrderEcommerce(SysOrder baseOrder,SysOrderEcommerce orderEcommerce) {
		sysOrderMapper.insert(baseOrder);
		orderEcommerce.setBaseOrderId(baseOrder.getId());//关联的主订单的Id
		SysOrderEcommerceMapper.insert(orderEcommerce);
	}
	
	public void updateOrderEcommerce(SysOrder baseOrder,SysOrderEcommerce orderEcommerce){
		sysOrderMapper.updateByPrimaryKeySelective(baseOrder);
		SysOrderEcommerceMapper.updateByPrimaryKeySelective(orderEcommerce);
	}
	
	public SysOrderEcommerce findByBaseOrderId(int baseOrderId) {
		return SysOrderEcommerceMapper.findByBaseOrderId(baseOrderId);	
	}

}
