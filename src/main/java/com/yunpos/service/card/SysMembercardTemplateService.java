package com.yunpos.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.card.SysCardBaseinfo;
import com.yunpos.model.card.SysMembercardTemplate;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.card.SysCardBaseinfoMapper;
import com.yunpos.persistence.dao.card.SysMembercardTemplateMapper;
import com.yunpos.service.EntityService;

@Service
public class SysMembercardTemplateService extends EntityService<SysMembercardTemplate> {
	@Autowired
	private SysMembercardTemplateMapper sysMembercardTemplateMapper;
	
	@Autowired
	private SysCardBaseinfoMapper sysCardBaseinfoMapper;
	
	@Override
	public EntityMapper<SysMembercardTemplate> getMapper() {
		return sysMembercardTemplateMapper;
	}

	public List<SysMembercardTemplate> findAll() {
		return sysMembercardTemplateMapper.findAll();
	}
	
	public void saveMembercard(SysCardBaseinfo sysCardBaseinfo,SysMembercardTemplate sysMembercardTemplate){
		//保存会员卡
		sysCardBaseinfoMapper.insert(sysCardBaseinfo);
		sysMembercardTemplate.setBase_info_id(sysCardBaseinfo.getId());
		sysMembercardTemplateMapper.insert(sysMembercardTemplate);	
	}

}
