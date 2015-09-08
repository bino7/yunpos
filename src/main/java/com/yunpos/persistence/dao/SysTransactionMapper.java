package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SysTransaction;

public interface SysTransactionMapper extends EntityMapper<SysTransaction>{
	@Select("select * from sys_transaction")
	List<SysTransaction> findAll();
	
	List<SysTransaction> findByCondition(SearchRequest searchRequest);
	
	SysTransaction findByTransNum(String orderNo);
}

