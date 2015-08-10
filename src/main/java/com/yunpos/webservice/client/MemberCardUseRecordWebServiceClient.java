package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardUseRecord;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardUseRecordService;
import com.alipaybox.webservice.request.MemberCardUseRecordRequest;


@Component
public class MemberCardUseRecordWebServiceClient extends BaseWebserviceClient<MemberCardUseRecordRequest,MemberCardUseRecord>{
	@Autowired
	MemberCardUseRecordService memberCardUseRecordService;

	@Override
	public EntityService<MemberCardUseRecord> getService() {
		return memberCardUseRecordService;
	}

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
