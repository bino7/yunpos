package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.User;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.UserMapper;

@Service
public class UserService extends EntityService<User> {
	@Autowired
	UserMapper userMapper;

	@Override
	public EntityMapper<User> getMapper() {
		return userMapper;
	}

	public User findByUserName(String username) {
		return userMapper.findByUserName(username);
	}
	
	public List<User> findAll(){
		return userMapper.findAll();
	}
	
	//通过邮箱查询
	public User findByEmail(String email) {
		return userMapper.findByEmail(email);
	}
	
}
