package com.yunpos.webservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.ProductCart;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.ProductCartService;
import com.alipaybox.webservice.request.ProductCartRequest;


@Component
public class ProductCartWebServiceClient extends BaseWebserviceClient<ProductCartRequest,ProductCart>{
	@Autowired
	ProductCartService productCartService;

	@Override
	public EntityService<ProductCart> getService() {
		return productCartService;
	}

}
