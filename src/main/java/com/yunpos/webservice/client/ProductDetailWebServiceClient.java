package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.ProductDetail;
import com.alipaybox.service.EntityService;
import com.alipaybox.service.ProductDetailService;
import com.alipaybox.webservice.request.ProductDetailRequest;


@Component
public class ProductDetailWebServiceClient extends BaseWebserviceClient<ProductDetailRequest,ProductDetail>{
	@Autowired
	private ProductDetailService productDetailService;

	@Override
	public EntityService<ProductDetail> getService() {
		return productDetailService;
	}

	@Override
	public ProductDetail[] dataProcess(ProductDetail[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
