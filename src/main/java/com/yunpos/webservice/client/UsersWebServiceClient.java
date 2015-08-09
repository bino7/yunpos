package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Users;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.UsersService;
import com.alipaybox.webservice.request.UsersRequest;


@Component
public class UsersWebServiceClient extends BaseWebserviceClient<UsersRequest,Users>{
	@Autowired
	UsersService usersService;

	@Override
	public EntityService<Users> getService() {
		return usersService;
	}

}
