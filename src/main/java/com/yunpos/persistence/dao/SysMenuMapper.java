package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMenu;


public interface SysMenuMapper extends EntityMapper<SysMenu>{
	
	@Select("select * from sys_menu order by menuNo")
	List<SysMenu> findAll();

	List<SysMenu> findListByIds(Object[] array);

	@Select("select * from sys_menu where menuParentNo=#{menuNo} order by menuNo")
	List<SysMenu> findBymenuParentNo(String menuNo);
	
	@Select("select * from sys_menu  where menuName=#{menuName}")
	List<SysMenu> findByMenuName(String menuName);
	
	@Select("select * from sys_menu  where menuParentNo=#{menuNo}")
	List<SysMenu> findChildByParentId(int menuNo);

	@Select("select * from sys_menu where menuParentNo ='' order by menuNo")
	List<SysMenu> findLevelOne();
	
	
	public String findMaxMenuNo();
	
	public String findMaxChildMenuNo(String parentMenuNo);
	
	@Select("select * from sys_menu where menuNo=#{menuNo}")
	SysMenu findByMenuNo(String menuNo);



}