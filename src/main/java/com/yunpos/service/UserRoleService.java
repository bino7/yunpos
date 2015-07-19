package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.UserRole;
import com.yunpos.persistence.dao.UserRoleMapper;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
    public List< UserRole> list() {
        return userRoleMapper.findAll();
    }
    
    public int insert(UserRole record) {
    	return userRoleMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return userRoleMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(UserRole record) {
    	return userRoleMapper.updateByPrimaryKey(record);
    }
    
    public UserRole findById(int id){
    	return userRoleMapper.selectByPrimaryKey(id);
    }

	public List<UserRole> findByUserId(int id) {
		return userRoleMapper.selectByUserId(id);
	}

	public boolean exists(int id) {
		UserRole userRole =userRoleMapper.selectByPrimaryKey(id);
		if(userRole!=null){
			return true;
		}
		return false;
	}
}
