package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Cashier;
import com.alipaybox.service.CashierService;
import com.alipaybox.service.EntityService;
import com.alipaybox.webservice.request.CashierRequest;


@Component
public class CashierWebServiceClient extends BaseWebserviceClient<CashierRequest,Cashier>{
	@Autowired
	CashierService cashierService;

	@Override
	public EntityService<Cashier> getService() {
		return cashierService;
	}

	@Override
	public Cashier[] dataProcess(Cashier[] list){
		// 数据处理
		List<Cashier> resultList = new ArrayList<Cashier>();
		for (Cashier entity : list) {
			String token = entity.getToken();
			if (token == null || token.length() == 0) {
				continue;
			}
			String username = entity.getUsername();//用户名
			if (username == null || username.length() == 0) {
				continue;
			}
			
			resultList.add(entity);
		}

		return resultList.toArray(new Cashier[]{});
	}


}
