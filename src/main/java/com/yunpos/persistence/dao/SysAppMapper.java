package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysApp;

public interface SysAppMapper extends EntityMapper<SysApp> {
	@Select("select * from sys_app")
	List<SysApp> findAll();
   
}