package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

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
	public ProductCartWithBLOBs[] dataProcess(ProductCartWithBLOBs[] list) {
		// 数据处理
		List<ProductCartWithBLOBs> resultList = new ArrayList<ProductCartWithBLOBs>();
		for (ProductCartWithBLOBs entity : list) {
			String token = entity.getToken();
			if (token == null || token.length() == 0) {
				continue;
			}
			String orderId = entity.getOrderid();//订单号
			if (orderId == null || orderId.length() == 0) {
				continue;
			}
			
			String wechaId = entity.getWecha_id();//购买用户
			if (wechaId == null || wechaId.length() == 0) {
				continue;
			}

			resultList.add(entity);
		}

		return resultList.toArray(new ProductCartWithBLOBs[] {});
	}

}
