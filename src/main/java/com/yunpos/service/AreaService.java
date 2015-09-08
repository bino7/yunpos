package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Area;
import com.yunpos.persistence.dao.AreaMapper;
import com.yunpos.persistence.dao.EntityMapper;
@Service
public class AreaService extends EntityService<Area>{
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public EntityMapper<Area> getMapper() {
		return areaMapper;
	}

	/**
	 * 根据代理商参数查询
	 * @param Area
	 * @return
	 */
	public List<Area> findByParms(Area area) {
		List<Area> list = areaMapper.selectByParm(area);
		return list;
	}
	
	
	public Area findById(Long id) {
		return areaMapper.selectByPrimaryKey(id);
	}

	public void delete(Long id) {
		areaMapper.deleteByPrimaryKey(id);
	}
	
}
