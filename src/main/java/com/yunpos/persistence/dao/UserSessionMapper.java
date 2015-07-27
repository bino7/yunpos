package com.yunpos.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.UserSession;

public interface UserSessionMapper extends EntityMapper<UserSession> {
	@Select("select * from sys_users_session t where session_id=#{sessionId} ")
	UserSession findBySessionId(Serializable sessionId);
	
	void update(UserSession userSession);
	
	@Select("select * from sys_users_session")
	List<UserSession> findAll();


}
