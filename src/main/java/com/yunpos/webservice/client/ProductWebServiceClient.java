package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Product;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.ProductService;
import com.alipaybox.webservice.request.ProductRequest;


@Component
public class ProductWebServiceClient extends BaseWebserviceClient<ProductRequest,Product>{
	@Autowired
	ProductService productService;

	@Override
	public EntityService<Product> getService() {
		return productService;
	}

	@Override
	public Product[] dataProcess(Product[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}


}
