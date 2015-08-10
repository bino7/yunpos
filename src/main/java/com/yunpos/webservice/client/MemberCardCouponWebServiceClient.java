package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
