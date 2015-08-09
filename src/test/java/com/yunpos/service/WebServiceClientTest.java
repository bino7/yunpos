package com.yunpos.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yunpos.Application;
import com.yunpos.webservice.client.UsersWebServiceClient;
import com.yunpos.webservice.client.WechatGroupListWebServiceClient;
import com.yunpos.webservice.client.WxuserWebServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WebServiceClientTest {
	private final String uri="http://localhost:8080/services";
	
	@Autowired
	UsersWebServiceClient usersWebServiceClient;

	@Autowired
	WxuserWebServiceClient wxuserWebServiceClient;
	
	@Autowired
	WechatGroupListWebServiceClient wechatGroupListWebServiceClient;

	@Test
	public void pullAndUpdate()  {	
		try {
			usersWebServiceClient.setDefaultUri(uri);
			usersWebServiceClient.pullAndUpdate();

			wxuserWebServiceClient.setDefaultUri(uri);
			wxuserWebServiceClient.pullAndUpdate();

			wechatGroupListWebServiceClient.setDefaultUri(uri);
			wechatGroupListWebServiceClient.pullAndUpdate();

		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }
