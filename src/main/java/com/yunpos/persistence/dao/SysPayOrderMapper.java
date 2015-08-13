package com.yunpos.persistence.dao;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SysPayOrder;

public interface SysPayOrderMapper extends EntityMapper<SysPayOrder> {
	@Select(" * from sys_pay_order where payOrderNo=#{payOrderNo}")
	SysPayOrder findByPayOrderNo(String payOrderNo);
   
}