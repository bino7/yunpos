package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardSet;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardSetService;
import com.alipaybox.webservice.request.MemberCardSetRequest;


@Component
public class MemberCardSetWebServiceClient extends BaseWebserviceClient<MemberCardSetRequest,MemberCardSet>{
	@Autowired
	MemberCardSetService memberCardSetService;

	@Override
	public EntityService<MemberCardSet> getService() {
		return memberCardSetService;
	}

}
