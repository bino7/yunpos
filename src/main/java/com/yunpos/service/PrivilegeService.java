package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Privilege;
import com.yunpos.persistence.dao.PrivilegeMapper;

@Service
public class PrivilegeService {
	@Autowired
	private PrivilegeMapper privilegeMapper;
	

    public List<Privilege> list() {
        return privilegeMapper.findAll();
    }
    
    public int insert(Privilege record) {
    	return privilegeMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return privilegeMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(Privilege record) {
    	return privilegeMapper.updateByPrimaryKey(record);
    }

	public Privilege findById(int id) {
		return privilegeMapper.selectByPrimaryKey(id);
	}

	public boolean exists(int id) {
		Privilege privilege =privilegeMapper.selectByPrimaryKey(id);
		if(privilege!=null){
			return true;
		}
		return false;
	}

}
