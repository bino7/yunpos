package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Users;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.UsersService;
import com.alipaybox.webservice.request.UsersRequest;

@Component
public class UsersWebServiceClient extends BaseWebserviceClient<UsersRequest, Users> {
	@Autowired
	UsersService usersService;

	@Override
	public EntityService<Users> getService() {
		return usersService;
	}

	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("startId", 12);
		Users[] list = super.marshalSendAndReceive(data);
		// 数据处理

		for (int i = 0; i < list.length; i++) {
			Users us = list[i];
			
		}
		super.pullAndUpdate(list);
	}
}
