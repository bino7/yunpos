package com.yunpos.persistence.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMemberpay;

public interface SysMemberpayMapper extends EntityMapper<SysMemberpay>{
  

	@Select("select * from sys_memberpay order by id")
	List<SysMemberpay> findAll();
	
	List<SysMemberpay> search();

	/**
	 * 根据会员卡参数查询
	 * @param SysMemberpay
	 * @return
	 */
	List<SysMemberpay> selectByParm(SysMemberpay sysMemberpay);
	
	@Select("SELECT * FROM sys_memberpay r WHERE r.source=#{source} AND r.payorderId=#{payorderId}")
	public SysMemberpay findByorderId(@Param(value = "source")String source, @Param(value = "payorderId")String payorderId);
	
	@Select("SELECT * FROM sys_memberpay r WHERE r.appid_userId=#{appid_userId} AND r.createdAt BETWEEN #{startTime} AND #{endTime}")
	public List<SysMemberpay> findByuserIdAndTime(@Param(value = "appid_userId")String appid_userId, @Param(value = "startTime")Date startTime, @Param(value = "endTime")Date endTime);
	
	@Select("SELECT * FROM sys_memberpay r WHERE r.appid_userId=#{appid_userId}")
	public List<SysMemberpay> findByuserId(@Param(value = "appid_userId")String appid_userId);
}