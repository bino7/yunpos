package com.yunpos.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysUserSession;

public interface SysUserSessionMapper extends EntityMapper<SysUserSession> {
	@Select("select * from sys_users_session t where sessionId=#{sessionId} ")
	SysUserSession findBySessionId(Serializable sessionId);
	
	void update(SysUserSession userSession);
	
	@Select("select * from sys_users_session")
	List<SysUserSession> findAll();
}