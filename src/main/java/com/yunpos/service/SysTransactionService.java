package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SearchRespose;
import com.yunpos.model.SysTransaction;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysTransactionMapper;

@Service
public class SysTransactionService extends EntityService<SysTransaction> {
	@Autowired
	private SysTransactionMapper sysTransactionMapper;

	@Override
	public EntityMapper<SysTransaction> getMapper() {
		return sysTransactionMapper;
	}

	public SearchRespose<SysTransaction> search(SearchRequest searchRequest) {
		List<SysTransaction> list = sysTransactionMapper.findByCondition(searchRequest);
		return new SearchRespose<>(searchRequest.getPage(), searchRequest.getRows(), list.size(), list);
	}

}
