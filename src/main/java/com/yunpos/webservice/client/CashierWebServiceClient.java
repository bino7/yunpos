package com.yunpos.webservice.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
	public Cashier[] dataProcess(Cashier[] list)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		return list;
	}


}
