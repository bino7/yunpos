package com.yunpos.persistence.dao;

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
}