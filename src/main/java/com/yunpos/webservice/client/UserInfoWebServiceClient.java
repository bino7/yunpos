package com.yunpos.webservice.client;

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
	public UserInfo[] dataProcess(UserInfo[] list){
		// TODO Auto-generated method stub
		return list;
	}


}
