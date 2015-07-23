package com.yunpos.service;

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
}
