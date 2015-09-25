package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysFans;

public interface SysFansMapper extends EntityMapper<SysFans>{
	@Select("select * from sys_fans")
	List<SysFans> findAll();
	
	@Select("select * from sys_fans r WHERE r.openId=#{openId} or r.appid_userId=#{userId}")
	SysFans findByOpenIdorUserId(@Param(value = "openId") String openId, @Param(value = "userId") String userId);
	
	@Select("select * from sys_fans r WHERE r.appid_userId=#{oid}")
	SysFans findByOid(@Param(value = "oid") String oid);
	
	/**
	 * 根据粉丝参数查询
	 * @param sysAgentFans
	 * @return
	 */
	List<SysFans> selectByParm(SysFans sysFans);
	
	List<SysFans> search();
	
	@Select("SELECT * FROM sys_fans r WHERE r.serialNo=#{serialNo}")
	List<SysFans> findByMerchant(@Param(value = "serialNo") String serialNo);
	

}
