package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMenu;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMenuMapper;
@Service
public class SysMenuService extends EntityService<SysMenu>{
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public EntityMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}
	
	public List<SysMenu> findAll(){
		return sysMenuMapper.findAll();
	}

  

}
