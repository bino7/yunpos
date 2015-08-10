package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

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

	public Users[] dataProcess(Users[] list){
		// 数据处理
		List<Users> newlist = new ArrayList<Users>();
		for (int i = 0; i < list.length; i++) {
			Users us = list[i];
			// 用户有效性检查
			if (us.getUser_flg() == true && us.getUsername() != "")
				newlist.add(us);
		}
		return newlist.toArray((new Users[]{}));
	}
}
