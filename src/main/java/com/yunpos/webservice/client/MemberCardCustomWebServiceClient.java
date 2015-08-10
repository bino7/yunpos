package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardCustom;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardCustomService;
import com.alipaybox.webservice.request.MemberCardCustomRequest;


@Component
public class MemberCardCustomWebServiceClient extends BaseWebserviceClient<MemberCardCustomRequest,MemberCardCustom>{
	@Autowired
	MemberCardCustomService memberCardCustomService;

	@Override
	public EntityService<MemberCardCustom> getService() {
		return memberCardCustomService;
	}

	@Override
	public MemberCardCustom[] dataProcess(MemberCardCustom[] list) {
		// TODO Auto-generated method stub
		return list;
	}


}
