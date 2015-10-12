package com.yunpos.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.card.SysCardCoupon;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.card.SysCardCouponMapper;
import com.yunpos.service.EntityService;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysCardCouponService extends EntityService<SysCardCoupon>{

	@Autowired
	private SysCardCouponMapper sysCardCouponMapper;
	
	@Override
	public EntityMapper<SysCardCoupon> getMapper() {
		return sysCardCouponMapper;
	}

	public GridResponse<SysCardCoupon> search(SysCardCoupon SysCardCoupon) {
		GridResponse<SysCardCoupon> response = new GridResponse<SysCardCoupon>();
		List<SysCardCoupon> SysCardCouponList =  sysCardCouponMapper.selectByParm(SysCardCoupon);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(SysCardCouponList);
		response.setTotalRowCount(SysCardCouponList.size());
		return response;
	}

	public List<SysCardCoupon> findAll() {
		return sysCardCouponMapper.findAll();
	}

	
	/**
	 * 根据参数查询
	 * @param SysCardCoupon
	 * @return
	 */
	public List<SysCardCoupon> findByParms(SysCardCoupon SysCardCoupon) {
		List<SysCardCoupon> list = sysCardCouponMapper.selectByParm(SysCardCoupon);
		return list;
	}
}
