package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardIntegral;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardIntegralService;
import com.alipaybox.webservice.request.MemberCardIntegralRequest;


@Component
public class MemberCardIntegralWebServiceClient extends BaseWebserviceClient<MemberCardIntegralRequest,MemberCardIntegral>{
	@Autowired
	MemberCardIntegralService memberCardIntegralService;

	@Override
	public EntityService<MemberCardIntegral> getService() {
		return memberCardIntegralService;
	}

}
