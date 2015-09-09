package com.yunpos.KDT.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Component;

import com.yunpos.KDT.KdtApiClient;

/**
 * 获取有赞粉丝用户客户端
 * 
 * @author janice
 *
 */
@Component
public class FansClient {
	private static final String METHOD = "kdt.users.weixin.followers.get";

	private String appid;
	private String appSecret;
	private String startDate;
	private String endDate;

	public FansClient() {
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 提取有赞微信粉丝用户数据，插入到云铺系统中
	 * 
	 * @throws Exception
	 */
	public void pullData() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("start_follow", this.startDate);
		params.put("end_follow", this.endDate);

		KdtApiClient kdtApiClient = new KdtApiClient(this.appid, this.appSecret);
		HttpResponse response = kdtApiClient.get(METHOD, params);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
	}

}
