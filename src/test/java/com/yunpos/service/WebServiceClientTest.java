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
import com.yunpos.webservice.client.CashdeskWebServiceClient;
import com.yunpos.webservice.client.CashierWebServiceClient;
import com.yunpos.webservice.client.ProductCartListWebServiceClient;
import com.yunpos.webservice.client.ProductCartWebServiceClient;
import com.yunpos.webservice.client.ProductDetailWebServiceClient;
import com.yunpos.webservice.client.ProductWebServiceClient;
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
	
	@Autowired
	ProductCartWebServiceClient productCartWebServiceClient;
	
	@Autowired
	ProductCartListWebServiceClient productCartListWebServiceClient;
	
	@Autowired
	ProductWebServiceClient productWebServiceClient;
	
	@Autowired
	ProductDetailWebServiceClient  productDetailWebServiceClient;
	
	@Autowired
	CashdeskWebServiceClient cashdeskWebServiceClient;
	
	@Autowired
	CashierWebServiceClient cashierWebServiceClient;

	@Test
	public void pullAndUpdate()  {	
		try {
			/*			
			usersWebServiceClient.setDefaultUri(uri);
			usersWebServiceClient.pullAndUpdate();
			
			wxuserWebServiceClient.setDefaultUri(uri);
			wxuserWebServiceClient.pullAndUpdate();

			wechatGroupListWebServiceClient.setDefaultUri(uri);
			wechatGroupListWebServiceClient.pullAndUpdate();
           		
			
			productCartWebServiceClient.setDefaultUri(uri);
			productCartWebServiceClient.pullAndUpdate();
			 
			
			productCartListWebServiceClient.setDefaultUri(uri);
			productCartListWebServiceClient.pullAndUpdate();
			
		
			productWebServiceClient.setDefaultUri(uri);
			productWebServiceClient.pullAndUpdate();
			*/
			productDetailWebServiceClient.setDefaultUri(uri);
			productDetailWebServiceClient.pullAndUpdate();
			/*
			cashdeskWebServiceClient.setDefaultUri(uri);
			cashdeskWebServiceClient.pullAndUpdate();
			*/
			
			/*
			cashierWebServiceClient.setDefaultUri(uri);
			cashierWebServiceClient.pullAndUpdate();
			*/
			
		
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }
