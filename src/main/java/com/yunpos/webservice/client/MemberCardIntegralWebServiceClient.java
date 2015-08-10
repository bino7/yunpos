package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
