package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Product;
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
	public ProductDetail[] dataProcess(ProductDetail[] list) {
		// 数据处理
		List<ProductDetail> resultList = new ArrayList<ProductDetail>();
		for (ProductDetail entity : list) {
			String format = entity.getFormat();
			
			if (format == null || format.length() == 0) {
				continue;
			}

			resultList.add(entity);
		}

		return resultList.toArray(new ProductDetail[] {});
	}

}
