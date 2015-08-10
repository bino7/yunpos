package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Product;
import com.alipaybox.model.ProductCartList;
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
	public Product[] dataProcess(Product[] list) {
		// 数据处理
		List<Product> resultList = new ArrayList<Product>();
		for (Product entity : list) {
			String token = entity.getToken();
			if (token == null || token.length() == 0) {
				continue;
			}

			String name = entity.getName();
			if (name == null || name.length() == 0) {
				continue;
			}

			resultList.add(entity);
		}

		return resultList.toArray(new Product[] {});
	}


}
