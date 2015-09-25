package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.SearchRequest;
import com.yunpos.model.SysTransaction;

public interface SysTransactionMapper extends EntityMapper<SysTransaction>{
	@Select("select * from sys_transaction")
	List<SysTransaction> findAll();
	
	List<SysTransaction> findByCondition(SearchRequest searchRequest);
	
	@Select("select * from sys_transaction where transNum=#{transNum}")
	SysTransaction findByTransNum(String transNum);
   
	/**
	 * 根据订单流水参数查询
	 * @param SysTransaction
	 * @return
	 */
	List<SysTransaction> selectByParm(SysTransaction sysTransaction);
	
	/**
	 * 根据订单流水参数查询总数
	 * @param SysTransaction
	 * @return
	 */
	int selectCountByParm(SysTransaction sysTransaction);
	
	@Select("select * from sys_transaction where user_order_no=#{orderNo} and serialNo=#{merchantNo}")
	SysTransaction findbyOrderNoAndMerchantNo(@Param("orderNo")String orderNo, @Param("merchantNo")String merchantNo);

}
