package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysFans;
import com.yunpos.model.SysUser;

public interface SysFansMapper extends EntityMapper<SysFans>{
	@Select("select * from sys_fans")
	List<SysUser> findAll();
	
	@Select("select * from sys_fans r WHERE r.openId=#{openId} or r.appid_userId=#{userId}")
	SysFans findByOpenIdorUserId(@Param(value = "openId") String openId, @Param(value = "userId") String userId);
	
	@Select("select * from sys_fans r WHERE r.appid_userId=#{oid}")
	SysFans findByOid(@Param(value = "oid") int oid);
}
