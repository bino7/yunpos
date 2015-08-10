package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
