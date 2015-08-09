package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardSign;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardSignService;
import com.alipaybox.webservice.request.MemberCardSignRequest;


@Component
public class MemberCardSignWebServiceClient extends BaseWebserviceClient<MemberCardSignRequest,MemberCardSign>{
	@Autowired
	MemberCardSignService memberCardSignService;

	@Override
	public EntityService<MemberCardSign> getService() {
		return memberCardSignService;
	}

}
