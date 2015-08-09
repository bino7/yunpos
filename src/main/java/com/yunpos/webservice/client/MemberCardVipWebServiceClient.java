package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardVip;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardVipService;
import com.alipaybox.webservice.request.MemberCardVipRequest;


@Component
public class MemberCardVipWebServiceClient extends BaseWebserviceClient<MemberCardVipRequest,MemberCardVip>{
	@Autowired
	MemberCardVipService memberCardVipService;

	@Override
	public EntityService<MemberCardVip> getService() {
		return memberCardVipService;
	}

}
