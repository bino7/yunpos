package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
	public MemberCardCustom[] dataProcess(MemberCardCustom[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return null;
	}


}
