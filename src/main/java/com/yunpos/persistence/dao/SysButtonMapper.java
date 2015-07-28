package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysButtonWithBLOBs;

public interface SysButtonMapper extends EntityMapper<SysButtonWithBLOBs> {
	
	@Select("select * from sys_button")
	public List<SysButtonWithBLOBs> findAll();

}