package com.yunpos.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.card.SysCardBaseinfo;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.card.SysCardBaseinfoMapper;
import com.yunpos.service.EntityService;

@Service
public class SysCardBaseinfoService extends EntityService<SysCardBaseinfo> {
	@Autowired
	private SysCardBaseinfoMapper sysCardBaseinfoMapper;
	
	@Override
	public EntityMapper<SysCardBaseinfo> getMapper() {
		return sysCardBaseinfoMapper;
	}

	public List<SysCardBaseinfo> findAll() {
		return sysCardBaseinfoMapper.findAll();
	}

}
