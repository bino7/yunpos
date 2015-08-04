package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMenu;


public interface SysMenuMapper extends EntityMapper<SysMenu>{
	
	@Select("select * from sys_menu")
	List<SysMenu> findAll();

	List<SysMenu> findListByIds(Object[] array);
	

}