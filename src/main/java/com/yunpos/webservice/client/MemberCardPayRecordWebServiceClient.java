package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardPayRecord;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardPayRecordService;
import com.alipaybox.webservice.request.MemberCardPayRecordRequest;


@Component
public class MemberCardPayRecordWebServiceClient extends BaseWebserviceClient<MemberCardPayRecordRequest,MemberCardPayRecord>{
	@Autowired
	MemberCardPayRecordService memberCardPayRecordService;

	@Override
	public EntityService<MemberCardPayRecord> getService() {
		return memberCardPayRecordService;
	}

	@Override
	public MemberCardPayRecord[] dataProcess(MemberCardPayRecord[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}


}
