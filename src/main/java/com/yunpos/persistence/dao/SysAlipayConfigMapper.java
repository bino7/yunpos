package com.yunpos.persistence.dao;

import com.yunpos.model.SysAlipayConfig;
import com.yunpos.model.SysAlipayConfigWithBLOBs;

public interface SysAlipayConfigMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(SysAlipayConfigWithBLOBs record);

    int insertSelective(SysAlipayConfigWithBLOBs record);

    SysAlipayConfigWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAlipayConfigWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysAlipayConfigWithBLOBs record);

    int updateByPrimaryKey(SysAlipayConfig record);
}