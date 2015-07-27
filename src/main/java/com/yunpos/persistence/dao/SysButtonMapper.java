package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysButton;

public interface SysButtonMapper extends EntityMapper<SysButton> {
	
	@Select("select * from sys_button")
	public List<SysButton> findAll();

}