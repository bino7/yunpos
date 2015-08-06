package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Users;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.UsersMapper;

@Service
public class UsersService extends EntityService<Users> {
	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public EntityMapper<Users> getMapper() {
		return usersMapper;
	}
	

}