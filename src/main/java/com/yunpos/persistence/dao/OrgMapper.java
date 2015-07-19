package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(Integer orgid);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Integer orgid);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
    
    @Select("select * from Org")
    List<Org> findAll();
}