package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysMember;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMemberMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMemberService extends EntityService<SysMember> {
	@Autowired
	private SysMemberMapper sysMemberMapper;

	@Override
	public EntityMapper<SysMember> getMapper() {
		return sysMemberMapper;
	}

	public GridResponse<SysMember> search(SysMember sysMember) {
		GridResponse<SysMember> response = new GridResponse<SysMember>();
		List<SysMember> list =  sysMemberMapper.selectByParm(sysMember);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(list);
		response.setTotalRowCount(list.size());
		return response;
	}

	public List<SysMember> findAll() {
		return sysMemberMapper.findAll();
	}

}
