package com.yunpos.persistence.dao;

import java.util.List;

import com.yunpos.model.Privilege;

public interface PrivilegeMapper extends EntityMapper<Privilege> {
	
	List<Privilege> findListByIds(Object[] array);
}