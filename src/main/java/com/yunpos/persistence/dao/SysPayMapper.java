package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysPay;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysPayMapper extends EntityMapper<SysPay> {
	@Select("select * from sys_pay")
	List<SysPay> findAll();
	@Select("select * from sys_pay")
	List<SysPay> findByCondition(GridRequest gridRequest);

}