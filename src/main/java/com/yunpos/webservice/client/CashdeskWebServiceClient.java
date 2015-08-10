package com.yunpos.webservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipaybox.model.Cashdesk;
import com.alipaybox.service.CashdeskService;
import com.alipaybox.service.EntityService;
import com.alipaybox.webservice.request.CashdeskRequest;


@Component
public class CashdeskWebServiceClient extends BaseWebserviceClient<CashdeskRequest,Cashdesk>{
	@Autowired
	CashdeskService cashdeskService;

	@Override
	public EntityService<Cashdesk> getService() {
		return cashdeskService;
	}

	@Override
	public Cashdesk[] dataProcess(Cashdesk[] list){
		// 数据处理
		List<Cashdesk> resultList = new ArrayList<Cashdesk>();
		for (Cashdesk entity : list) {
			String token = entity.getToken();
			if (token == null || token.length() == 0) {
				continue;
			}
			String product_code = entity.getProduct_code();
			//product_code为QR_CODE_OFFLINE，表示扫码支付，为BARCODE_PAY_OFFLINE，标识条码支付
			if (product_code == null || product_code.length() == 0) {
				continue;
			}
			Integer ststus = entity.getStstus();
			if (ststus == null || ((ststus != 0) && (ststus != 1) && (ststus != 2))) {
				//ststus值只能为0,1,2;其他的值过滤掉
				continue;
			}
			
			resultList.add(entity);
		}

		return resultList.toArray(new Cashdesk[]{});
	}
	
}
