package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrder;

public interface SysOrderMapper extends EntityMapper<SysOrder>{
	@Select("select * from sys_order order by id")
	List<SysOrder> findAll();

	List<SysOrder> search();
   
	/**
	 * 根据订单参数查询
	 * @param SysOrder
	 * @return
	 */
	List<SysOrder> selectByParm(SysOrder sysOrder);
}