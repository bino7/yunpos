package com.yunpos.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.MktPosTransaction;
import com.yunpos.model.SysTransaction;

public interface MktPosTransactionMapper extends EntityMapper<MktPosTransaction>{
	
    int deleteByPrimaryKey(MktPosTransaction record);

    int insert(MktPosTransaction record);

    int insertSelective(MktPosTransaction record);

    MktPosTransaction selectByPrimaryKey(MktPosTransaction record);

    int updateByPrimaryKeySelective(MktPosTransaction record);

    int updateByPrimaryKey(MktPosTransaction record);
    
    @Select("select * from mkt_pos_transaction ")
	List<MktPosTransaction> findAll();
    
    /**
	 * 根据订单参数查询
	 * @param MktPosTransaction
	 * @return
	 */
	List<MktPosTransaction> selectByParm(MktPosTransaction mktPosTransaction);
	
	/**
	 * 根据订单参数查询
	 * @param MktPosTransaction
	 * @return
	 */
	int selectCountByParm(MktPosTransaction mktPosTransaction);
}