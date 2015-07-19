package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMenu;
import com.yunpos.persistence.dao.SysMenuMapper;
@Service
public class SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	

    public List< SysMenu> list() {
        return sysMenuMapper.findAll();
    }
    
    public int insert( SysMenu record) {
    	return sysMenuMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return sysMenuMapper.deleteByPrimaryKey(uid);
    }
    
    public int update( SysMenu record) {
    	return sysMenuMapper.updateByPrimaryKey(record);
    }

	public SysMenu findById(int id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	public boolean exists(int id) {
		SysMenu sysMenu =sysMenuMapper.selectByPrimaryKey(id);
		if(sysMenu!=null){
			return true;
		}
		return false;
	}

}
