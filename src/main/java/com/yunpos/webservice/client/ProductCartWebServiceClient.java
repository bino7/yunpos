package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.ProductCartWithBLOBs;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.ProductCartService;
import com.alipaybox.webservice.request.ProductCartRequest;


@Component
public class ProductCartWebServiceClient extends BaseWebserviceClient<ProductCartRequest,ProductCartWithBLOBs>{
	@Autowired
	ProductCartService productCartService;

	@Override
	public EntityService<ProductCartWithBLOBs> getService() {
		return productCartService;
	}

	@Override
	public void dataProcess() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
	}

}
