package com.yunpos.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.card.SysCardTemplateMapper;
import com.yunpos.service.EntityService;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysCardTemplateService extends EntityService<SysCardTemplate>{

	@Autowired
	private SysCardTemplateMapper sysCardTemplateMapper;
	
	@Override
	public EntityMapper<SysCardTemplate> getMapper() {
		return sysCardTemplateMapper;
	}

	public GridResponse<SysCardTemplate> search(SysCardTemplate SysCardTemplate) {
		GridResponse<SysCardTemplate> response = new GridResponse<SysCardTemplate>();
		List<SysCardTemplate> SysCardTemplateList =  sysCardTemplateMapper.selectByParm(SysCardTemplate);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(SysCardTemplateList);
		response.setTotalRowCount(SysCardTemplateList.size());
		return response;
	}

	public List<SysCardTemplate> findAll() {
		return sysCardTemplateMapper.findAll();
	}

	
	/**
	 * 根据参数查询
	 * @param SysCardTemplate
	 * @return
	 */
	public List<SysCardTemplate> findByParms(SysCardTemplate SysCardTemplate) {
		List<SysCardTemplate> list = sysCardTemplateMapper.selectByParm(SysCardTemplate);
		return list;
	}
}
