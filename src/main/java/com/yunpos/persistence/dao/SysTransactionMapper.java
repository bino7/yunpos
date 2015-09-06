package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SysTransaction;

public interface SysTransactionMapper extends EntityMapper<SysTransaction>{
	
	List<SysTransaction> findByCondition(SearchRequest searchRequest);
	
	@Select("select * from sys_transaction where transNum=#{transNum}")
	SysTransaction findByTransNum(String transNum);
   
}