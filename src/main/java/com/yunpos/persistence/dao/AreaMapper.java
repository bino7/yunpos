package com.yunpos.persistence.dao;

import java.util.List;

import com.yunpos.model.Area;

public interface AreaMapper extends EntityMapper<Area>{
    int deleteByPrimaryKey(Long id);

    int insert(Area area);

    int insertSelective(Area area);

    List<Area> selectByExample(Area area);

    Area selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Area area);

    int updateByPrimaryKey(Area area);
    
	/**
	 * 根据地址参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	List<Area> selectByParm(Area area);
}