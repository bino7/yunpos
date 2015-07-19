package com.yunpos.persistence.dao;

import com.yunpos.model.DataRule;

public interface DataRuleMapper {
    int deleteByPrimaryKey(Integer ruleid);

    int insert(DataRule record);

    int insertSelective(DataRule record);

    DataRule selectByPrimaryKey(Integer ruleid);

    int updateByPrimaryKeySelective(DataRule record);

    int updateByPrimaryKeyWithBLOBs(DataRule record);

    int updateByPrimaryKey(DataRule record);
}