package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysButton;
import com.yunpos.model.SysButtonWithBLOBs;
import com.yunpos.persistence.dao.SysButtonMapper;
@Service
public class SysButtonService {
	
	@Autowired
	private SysButtonMapper  sysButtonMapper;
	

    public List<SysButton> list() {
        return sysButtonMapper.findAll();
    }
    
    public int insert(SysButtonWithBLOBs record) {
    	return sysButtonMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return sysButtonMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(SysButton record) {
    	return sysButtonMapper.updateByPrimaryKey(record);
    }

	public SysButton findById(int id) {
		return sysButtonMapper.selectByPrimaryKey(id);
	}

	public boolean exists(int id) {
		SysButton sysButton= sysButtonMapper.selectByPrimaryKey(id);
		if(sysButton!=null){
			return true;
		}
		return false;
	}

}
