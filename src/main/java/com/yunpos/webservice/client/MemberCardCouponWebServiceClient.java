package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardCoupon;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardCouponService;
import com.alipaybox.webservice.request.MemberCardCouponRequest;


@Component
public class MemberCardCouponWebServiceClient extends BaseWebserviceClient<MemberCardCouponRequest,MemberCardCoupon>{
	@Autowired
	MemberCardCouponService memberCardCouponService;

	@Override
	public EntityService<MemberCardCoupon> getService() {
		return memberCardCouponService;
	}

	@Override
	public MemberCardCoupon[] dataProcess(MemberCardCoupon[] list){
		// TODO Auto-generated method stub
		return list;
	}

}
