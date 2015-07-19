package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysApp;
import com.yunpos.persistence.dao.SysAppMapper;
@Service
public class SysAppService {
	@Autowired
	private SysAppMapper sysAppMapper;
	

    public List< SysApp> list() {
        return sysAppMapper.findAll();
    }
    
    public int insert( SysApp record) {
    	return sysAppMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return sysAppMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(SysApp record) {
    	return sysAppMapper.updateByPrimaryKey(record);
    }

	public SysApp findById(int id) {
		return sysAppMapper.selectByPrimaryKey(id);
	}

	public boolean exists(int id) {
		SysApp sysApp =sysAppMapper.selectByPrimaryKey(id);
		if(sysApp!=null){
			return true;
		}
		return false;
	}
	

}
