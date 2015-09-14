package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysStore;

public interface SysStoreMapper  extends EntityMapper<SysStore> {
	
	@Select("select * from sys_store store by id")
	List<SysStore> findAll();
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysStore record);

    int insertSelective(SysStore record);

    SysStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysStore record);

    int updateByPrimaryKey(SysStore record);
    
    List<SysStore> search();
    
	/**
	 * 根据门店参数查询
	 * @param SysStore
	 * @return
	 */
	List<SysStore> selectByParm(SysStore sysStore);
}