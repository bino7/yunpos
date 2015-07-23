package com.yunpos.persistence.dao;

import java.util.List;

import com.yunpos.model.Role;

public interface RoleMapper extends EntityMapper<Role> {

	List<Role> findByIdsMap(Object[] objects);
}