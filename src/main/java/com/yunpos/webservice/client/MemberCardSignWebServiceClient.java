package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

	@Override
	public MemberCardSign[] dataProcess(MemberCardSign[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}


}
