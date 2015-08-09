package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Cashdesk;
import com.alipaybox.service.CashdeskService;
import com.alipaybox.service.EntityService;
import com.alipaybox.webservice.request.CashdeskRequest;


@Component
public class CashdeskWebServiceClient extends BaseWebserviceClient<CashdeskRequest,Cashdesk>{
	@Autowired
	CashdeskService cashdeskService;

	@Override
	public EntityService<Cashdesk> getService() {
		return cashdeskService;
	}

}
