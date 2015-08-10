package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardExchange;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardExchangeService;
import com.alipaybox.webservice.request.MemberCardExchangeRequest;


@Component
public class MemberCardExchangeWebServiceClient extends BaseWebserviceClient<MemberCardExchangeRequest,MemberCardExchange>{
	@Autowired
	MemberCardExchangeService memberCardExchangeService;

	@Override
	public EntityService<MemberCardExchange> getService() {
		return memberCardExchangeService;
	}

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
