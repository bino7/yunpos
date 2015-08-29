package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public GridResponse<SysMember> search(GridRequest gridRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SysMember> findAll() {
		return sysMemberMapper.findAll();
	}

}
