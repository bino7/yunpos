package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.ProductCartList;
import com.alipaybox.model.ProductCartWithBLOBs;
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
	public ProductCartList[] dataProcess(ProductCartList[] list) {
		// 数据处理
		List<ProductCartList> resultList = new ArrayList<ProductCartList>();
		for (ProductCartList entity : list) {
			String token = entity.getToken();
			if (token == null || token.length() == 0) {
				continue;
			}
			
			Integer carid = entity.getCartid();//对应的订单号
			if (carid == null || carid < 0) {
				continue;
			}

			resultList.add(entity);
		}

		return resultList.toArray(new ProductCartList[] {});
	}


}
