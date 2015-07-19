package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.User;
import com.yunpos.persistence.dao.UserMapper;

@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public List<User> findAllUsers() {
        return userMapper.findAll();
    }
    
    public User findById( int id) {
        return userMapper.selectByPrimaryKey(id);
    }
    
    public int insertUsers(User record) {
    	return userMapper.insert(record);
    }
    
    public int delUsers(int uid) {
    	return userMapper.deleteByPrimaryKey(uid);
    }
    
    public int updateUsers(User record) {
    	return userMapper.updateByPrimaryKey(record);
    }
    
    
    public boolean exists(int id){
    	User user = userMapper.selectByPrimaryKey(id);
    	if(user!=null){
    		return true;
    	}
    	return false;
    }
    
}
