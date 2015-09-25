package com.yunpos.persistence.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysMemberuse;

public interface SysMemberuseMapper extends EntityMapper<SysMemberuse>{

	@Select("select * from sys_memberuse order by id")
	List<SysMemberuse> findAll();
	
	List<SysMemberuse> search();

	/**
	 * 根据会员卡参数查询
	 * @param SysMemberuse
	 * @return
	 */
	List<SysMemberuse> selectByParm(SysMemberuse sysMemberuse);
	
	@Select("SELECT * FROM sys_memberuse r WHERE r.source=#{source} AND r.appid_useId=#{appid_useId}")
	public SysMemberuse findMemberUseByuseId(@Param(value = "source")String source, @Param(value = "appid_useId")String appid_useId);
	
	@Select("SELECT * FROM sys_memberuse r WHERE r.appid_userId=#{appid_userId} AND r.createdAt BETWEEN #{startTime} AND #{endTime}")
	public List<SysMemberuse> findByuserIdAndTime(@Param(value = "appid_userId")String appid_userId, @Param(value = "startTime")Date startTime, @Param(value = "endTime")Date endTime); 
	
	@Select("SELECT * FROM sys_memberuse r WHERE r.appid_userId=#{appid_userId}")
	public List<SysMemberuse> findByuserId(@Param(value = "appid_userId")String appid_userId); 

}