package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMenu;


public interface SysMenuMapper extends EntityMapper<SysMenu>{
	
	@Select("select * from sys_menu order by sequence")
	List<SysMenu> findAll();

	List<SysMenu> findListByIds(Object[] array);

	@Select("select * from sys_menu where menuParentNo=#{id} order by sequence")
	List<SysMenu> findBymenuParentNo(int id);
	
	@Select("select * from sys_menu  where menuName=#{menuName}")
	List<SysMenu> findByMenuName(String menuName);
	
	@Select("select * from sys_menu  where menuParentNo=#{menuNo}")
	List<SysMenu> findChildByParentId(int menuNo);

	@Select("select * from sys_menu where menuParentNo is null order by sequence")
	List<SysMenu> findLevelOne();

}