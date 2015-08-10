package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.ProductCartList;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.ProductCartListService;
import com.alipaybox.webservice.request.ProductCartListRequest;


@Component
public class ProductCartListWebServiceClient extends BaseWebserviceClient<ProductCartListRequest,ProductCartList>{
	@Autowired
	ProductCartListService productCartListService;

	@Override
	public EntityService<ProductCartList> getService() {
		return productCartListService;
	}

	@Override
	public ProductCartList[] dataProcess(ProductCartList[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return null;
	}


}
