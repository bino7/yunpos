package com.yunpos.KDT;

import java.util.HashMap;
import java.util.Map;

import com.yunpos.service.KDT.KdtPushService;

public class KDTTest {


	public static void main(String[] args) {
		String appid = "d1010f3103bc748b06";

		String appSecret = "4b86ab7d347041c84d2ad8a55c473659";
		
		String client_id = "7718b70dad904f6f3b";
		String client_secret = "05703a2a336f893f53f72d6406bce731";
		
		//String version = "v1";
		String version = "v2";
		Map<String, String> apps = new HashMap<String, String>();
		//apps.put(appid, appSecret);
		apps.put(client_id, client_secret);
		
		KdtPushService kdtPushService = new KdtPushService();
		
		//kdtPushService.setVersion(version);
		//kdtPushService.setApps(apps);
		try {
			kdtPushService.pushAndUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
