package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Org;

public interface OrgMapper extends EntityMapper<Org> {
	@Select("select * from org")
	public List<Org> findAll(); 
  
}