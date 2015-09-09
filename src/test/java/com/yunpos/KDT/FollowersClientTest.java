package com.yunpos.KDT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.KDT.client.FansClient;
import com.yunpos.service.KDT.KdtFansService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FollowersClientTest {
	private final String appid = "d1010f3103bc748b06";

	private final String appSecret = "4b86ab7d347041c84d2ad8a55c473659";
	
	
	@Autowired
	FansClient fansClient;

	//@Test
	public void pullAndUpdate()  {	
		fansClient.setStartDate("2015-05-20 11:11:11");
		fansClient.setEndDate("2015-09-01 11:11:11");
		
		fansClient.setAppid(appid);
		fansClient.setAppSecret(appSecret);
		try {
			fansClient.pullData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Autowired
	KdtFansService kdtFansService;
	
	@Test
	public void getFans() {
		kdtFansService.getFans(5,-200);
	}
	
}
