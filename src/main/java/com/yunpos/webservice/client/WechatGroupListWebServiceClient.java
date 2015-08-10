package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.WechatGroupList;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.WechatGroupListService;
import com.alipaybox.webservice.request.WechatGroupListRequest;


@Component
public class WechatGroupListWebServiceClient extends BaseWebserviceClient<WechatGroupListRequest,WechatGroupList>{
	
	@Autowired
	private WechatGroupListService wechatGroupListService;

	@Override
	public EntityService<WechatGroupList> getService() {
		return wechatGroupListService;
	}

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	
}	
	
	
	
	

