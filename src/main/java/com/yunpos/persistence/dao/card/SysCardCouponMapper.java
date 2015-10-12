package com.yunpos.persistence.dao.card;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.card.SysCardCoupon;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysCardCouponMapper  extends EntityMapper<SysCardCoupon>{
    int deleteByPrimaryKey(Integer id);

    int insert(SysCardCoupon record);

    int insertSelective(SysCardCoupon record);

    SysCardCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCardCoupon record);

    int updateByPrimaryKey(SysCardCoupon record);
    
	@Select("select * from sys_card_coupon order by id")
	List<SysCardCoupon> findAll();

	List<SysCardCoupon> search(GridRequest gridRequest);
	
	List<SysCardCoupon> selectByParm(SysCardCoupon sysCardCoupon);
    
}