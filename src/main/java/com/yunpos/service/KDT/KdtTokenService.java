package com.yunpos.service.KDT;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.KDT.KdtApiClient;
import com.yunpos.model.SysShop;
import com.yunpos.model.KDT.KdtToken;
import com.yunpos.service.SysShopService;

/**
 * 获取有赞商户的店铺的Token，进行保存
 * @author janice
 *
 */
@Service
public class KdtTokenService {
	private static final Logger log = LoggerFactory.getLogger(KdtTokenService.class);
	public static final String TokenURL = "https://open.koudaitong.com/oauth/token";
	public static final String KDTURL = "https://open.koudaitong.com/api/oauthentry";
	public static final String ShopMethod = "kdt.shop.basic.get";
	public static final String client_id = "7718b70dad904f6f3b";
	public static final String client_secret = "05703a2a336f893f53f72d6406bce731";
	
	@Autowired
	SysShopService sysShopService;

	private String version;
	Map<String, String> apps;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, String> getApps() {
		return apps;
	}

	public void setApps(Map<String, String> apps) {
		this.apps = apps;
	}
	
	/**
	 * 获取Token，并进行保存操作
	 * @param grant_type
	 * @param code
	 * @param redirect_uri
	 * @param serialNos 商户编号
	 * @throws Exception 
	 */
	public void getToken(String grant_type, String code, String redirect_uri, String serialNo)
			throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);
		params.put("grant_type", grant_type);
		params.put("code", code);
		params.put("redirect_uri", redirect_uri);

		//通过Code获取Token
		KdtApiClient kdtApiClient = new KdtApiClient();
		HttpResponse response = kdtApiClient.post(TokenURL, params);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity, "UTF-8");
			ObjectMapper objectMapper = new ObjectMapper();
			KdtToken token = objectMapper.readValue(content, KdtToken.class);
			String access_token = token.getAccess_token();
			String refresh_token = token.getRefresh_token();

			// 通过access_token获取当前商户的店铺
			StringBuffer url = new StringBuffer(KDTURL);
			url = url.append("?access_token=").append(access_token).append("&method=").append(ShopMethod);
			response = kdtApiClient.get(url.toString());
			entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");

				JsonNode root = objectMapper.readTree(content);
				JsonNode node = root.path("response");			
				int shopSid = node.path("sid").asInt();// 店铺Id
				String shopName = node.path("name").asText();// 店铺名称

				// 保存当前商户的店铺信息
				SysShop shop = new SysShop();
				shop.setType(Byte.valueOf("0"));// 线上店铺
				shop.setSource("KDT");// 有赞店铺
				shop.setSerialNo(serialNo);// 店铺编号
				shop.setName(shopName);// 店铺名称
				shop.setOid(String.valueOf(shopSid));// 店铺Id
				shop.setAccessToken(access_token);
				shop.setRefreshToken(refresh_token);
				shop.setCreatedAt(new Date());
				sysShopService.updateByOid(shop);
			}
		}
	}

	/**
	 * 通过refresh_token获取新的Token，用于Token过期的情况
	 * @param refresh_token
	 * @return 新的Token
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String freshToken(String refresh_token) throws ClientProtocolException, IOException {
		String access_token="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "refresh_token");
		params.put("refresh_token", refresh_token);
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);

		// refresh_token获取新的Token
		KdtApiClient kdtApiClient = new KdtApiClient();
		HttpResponse response = kdtApiClient.post(TokenURL, params);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity, "UTF-8");
			ObjectMapper objectMapper = new ObjectMapper();
			KdtToken token = objectMapper.readValue(content, KdtToken.class);
			access_token = token.getAccess_token();
			String refresh_token_new = token.getRefresh_token();

			SysShop shop = sysShopService.findByRefreshtoken(refresh_token);
			shop.setAccessToken(access_token);//更新为新的access_token
			shop.setRefreshToken(refresh_token_new);//更新为新的refresh_token_new
			sysShopService.update(shop);		
		}
		return access_token;

	}
}
