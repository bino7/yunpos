package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.WxUser;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.WxuserService;
import com.alipaybox.webservice.request.WxuserRequest;


@Component
public class WxuserWebServiceClient extends BaseWebserviceClient<WxuserRequest,WxUser>{
	@Autowired
	private WxuserService wxuserService;

	@Override
	public EntityService<WxUser> getService() {
		return wxuserService;
	}

}
