package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardNotice;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardNoticeService;
import com.alipaybox.webservice.request.MemberCardNoticeRequest;


@Component
public class MemberCardNoticeWebServiceClient extends BaseWebserviceClient<MemberCardNoticeRequest,MemberCardNotice>{
	@Autowired
	MemberCardNoticeService memberCardNoticeService;

	@Override
	public EntityService<MemberCardNotice> getService() {
		return memberCardNoticeService;
	}

	@Override
	public MemberCardNotice[] dataProcess(MemberCardNotice[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}


}
