package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysShop;

public interface SysShopMapper extends EntityMapper<SysShop> {
	@Select("select * from sys_shop")
	public List<SysShop> findAll();
	
	
	@Select("select * from sys_shop r where r.serialNo=#{serialNo} and r.oid=#{oid}")
	SysShop findByOid(@Param(value = "serialNo") String serialNo, @Param(value = "oid") String oid);
}

