package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrderEcommerce;

public interface SysOrderEcommerceMapper extends EntityMapper<SysOrderEcommerce>{

	@Select("SELECT * FROM sys_order_ecommerce a WHERE a.baseOrderId=#{baseOrderId}")
	public SysOrderEcommerce findByBaseOrderId(@Param(value = "baseOrderId") int baseOrderId);

}