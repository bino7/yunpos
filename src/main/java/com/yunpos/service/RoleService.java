package com.yunpos.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Role;
import com.yunpos.persistence.dao.RoleMapper;
@Service
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	

    public List<Role> list() {
        return roleMapper.findAll();
    }
    
    public int insert(Role record) {
    	return roleMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return roleMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(Role record) {
    	return roleMapper.updateByPrimaryKey(record);
    }

	public boolean exists(int id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		if(role!=null){
			return true;
		}
		return false;
	}

	public Role findById(int id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public List<Role> findListByIds(Object[] objects) {
		return roleMapper.findByIdsMap(objects);
	}

}
