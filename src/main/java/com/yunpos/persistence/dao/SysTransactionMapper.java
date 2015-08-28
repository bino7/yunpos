package com.yunpos.persistence.dao;

import java.util.List;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SysTransaction;

public interface SysTransactionMapper extends EntityMapper<SysTransaction>{
	
	List<SysTransaction> findByCondition(SearchRequest searchRequest);
   
}