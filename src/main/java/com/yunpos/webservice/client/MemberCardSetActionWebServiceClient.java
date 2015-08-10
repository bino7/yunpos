package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.MemberCardSetAction;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.MemberCardSetActionService;
import com.alipaybox.webservice.request.MemberCardSetActionRequest;


@Component
public class MemberCardSetActionWebServiceClient extends BaseWebserviceClient<MemberCardSetActionRequest,MemberCardSetAction>{
	@Autowired
	MemberCardSetActionService memberCardSetActionService;

	@Override
	public EntityService<MemberCardSetAction> getService() {
		return memberCardSetActionService;
	}

	@Override
	public MemberCardSetAction[] dataProcess(MemberCardSetAction[] list){
		// TODO Auto-generated method stub
		return list;
	}


}
