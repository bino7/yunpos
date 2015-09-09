package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrder;

public interface SysOrderMapper extends EntityMapper<SysOrder>{
	@Select("select * from sys_order")
	public List<SysOrder> findAll();
	
	public List<SysOrder> search();
	
	@Select("SELECT * FROM sys_order a WHERE a.shopId=#{shopId} AND a.orderId=#{orderId}")
	public SysOrder findByshopIdAndOrderid(@Param(value = "shopId") String shopId, @Param(value = "orderId")String orderId);
}
