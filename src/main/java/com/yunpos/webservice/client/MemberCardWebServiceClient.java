package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardCreate;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardCreateService;
import com.alipaybox.webservice.request.MemberCardCreateRequest;


@Component
public class MemberCardWebServiceClient extends BaseWebserviceClient<MemberCardCreateRequest,MemberCardCreate>{
	@Autowired
	MemberCardCreateService memberCardCreateService;

	@Override
	public EntityService<MemberCardCreate> getService() {
		return memberCardCreateService;
	}

	@Override
	public MemberCardCreate[] dataProcess(MemberCardCreate[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}

}
