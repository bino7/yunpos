package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysOrder;

public interface SysOrderMapper extends EntityMapper<SysOrder>{
	@Select("select * from sys_order")
	public List<SysOrder> findAll();
	
	List<SysOrder> search();
}
