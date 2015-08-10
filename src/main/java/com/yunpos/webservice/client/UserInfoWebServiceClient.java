package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.UserInfo;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.UserInfoService;
import com.alipaybox.webservice.request.UserInfoRequest;


@Component
public class UserInfoWebServiceClient extends BaseWebserviceClient<UserInfoRequest,UserInfo>{
	@Autowired
	UserInfoService userInfoService;

	@Override
	public EntityService<UserInfo> getService() {
		return userInfoService;
	}

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
