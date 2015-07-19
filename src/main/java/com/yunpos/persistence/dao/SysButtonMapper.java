package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysButton;
import com.yunpos.model.SysButtonWithBLOBs;

public interface SysButtonMapper {
    int deleteByPrimaryKey(Integer btnid);

    int insert(SysButtonWithBLOBs record);

    int insertSelective(SysButtonWithBLOBs record);

    SysButtonWithBLOBs selectByPrimaryKey(Integer btnid);

    int updateByPrimaryKeySelective(SysButtonWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysButtonWithBLOBs record);

    int updateByPrimaryKey(SysButton record);

    @Select("select * from SysButton")
	List<SysButton> findAll();
}