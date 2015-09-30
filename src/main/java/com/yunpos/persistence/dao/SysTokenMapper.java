package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysToken;

public interface SysTokenMapper extends EntityMapper<SysToken> {
	@Select("SELECT * FROM sys_token r WHERE r.appid=#{appid}  AND r.appSecret=#{appSecret}")
	public SysToken findByappidAndappSecret(@Param(value = "appid") String appid, @Param(value = "appSecret") String appSecret);
	
	@Select("SELECT * FROM sys_token r WHERE r.access_token=#{access_token}")
	public SysToken findByAccessToken(@Param(value = "access_token") String accessToken);
	
}
