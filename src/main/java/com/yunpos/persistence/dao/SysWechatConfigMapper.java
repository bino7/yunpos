package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysWechatConfig;
import com.yunpos.model.SysWechatConfigWithBLOBs;

public interface SysWechatConfigMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(SysWechatConfigWithBLOBs record);

    int insertSelective(SysWechatConfigWithBLOBs record);

    SysWechatConfigWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysWechatConfigWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysWechatConfigWithBLOBs record);

    int updateByPrimaryKey(SysWechatConfig record);
    
    @Select("select * from sys_wechat_config where mchId=#{mchId}")
	SysWechatConfigWithBLOBs findByMchId(String mchId);
}