package com.yunpos.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.card.SysMembercardTemplate;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.card.SysMembercardTemplateMapper;
import com.yunpos.service.EntityService;

@Service
public class SysMembercardTemplateService extends EntityService<SysMembercardTemplate> {
	@Autowired
	private SysMembercardTemplateMapper sysMembercardTemplateMapper;
	
	@Override
	public EntityMapper<SysMembercardTemplate> getMapper() {
		return sysMembercardTemplateMapper;
	}

	public List<SysMembercardTemplate> findAll() {
		return sysMembercardTemplateMapper.findAll();
	}

}
