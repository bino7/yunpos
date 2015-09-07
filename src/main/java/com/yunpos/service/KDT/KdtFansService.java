package com.yunpos.service.KDT;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.KDT.KdtApiClient;
import com.yunpos.model.SysFans;
import com.yunpos.model.SysShop;
import com.yunpos.service.SysFansService;
import com.yunpos.service.SysShopService;

/**
 * 有赞粉丝服务类，由于外部定时器定时调用
 * @author janice
 *
 */
@Component
public class KdtFansService {
	private static final int PAGE_SIZE = 50; //每页50条
	private static final String ShopMethod = "kdt.users.weixin.followers.get";
	
	@Autowired
	SysShopService sysShopService;
	
	@Autowired
	KdtTokenService kdtTokenService;
	
	@Autowired
	SysFansService sysFansService;
	
	public void getFans() {
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH, -200);
		//cal.add(Calendar.HOUR, 12);
			
		Date startDate = cal.getTime();
			
		List<SysShop> shops = sysShopService.findAllByToken();
		//对应其中的每一个店铺，进行粉丝数据的获取
		for (SysShop shop : shops) {
			try {
				pullAndUpdate(shop.getSerialNo(), shop.getAccessToken(), shop.getRefreshToken(), startDate, endDate, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 获取某一个店铺的粉丝信息
	 * @param serialNo
	 * @param accessToken
	 * @param refreshToken
	 * @param start_follow
	 * @param end_follow
	 * @param page_no
	 * @throws Exception 
	 */
	public void pullAndUpdate(String serialNo, String accessToken, String refreshToken, Date start_follow,
			Date end_follow,int page_no) throws Exception {
		// 通过access_token获取当前店铺的粉丝
		StringBuffer url = new StringBuffer(KdtTokenService.KDTURL);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		url = url.append("?access_token=").append(accessToken).append("&method=").append(ShopMethod)
				.append("&start_follow=").append(sdf.format(start_follow))
				.append("&end_follow=").append(sdf.format(end_follow))
				.append("&page_no=").append(page_no).append("&page_size=").append(PAGE_SIZE);	
		String urltmp = URLEncoder.encode(url.toString(), "UTF-8")
				.replace("%3A", ":")
				.replace("%2F", "/")
				.replace("%26", "&")
				.replace("%3D", "=")
				.replace("%3F", "?");
		
		KdtApiClient kdtApiClient = new KdtApiClient();
		HttpResponse response = kdtApiClient.get(urltmp);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity, "UTF-8");
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(content);	
			if (root.path("error").asText().length() > 0) {//token过期，需重新过期Token
				if (refreshToken != null && refreshToken.length() > 0) {
					//根据refresh Token获取新的token
					String accessToken_new = kdtTokenService.freshToken(refreshToken);
					//用新的Token再执行一次
					pullAndUpdate(serialNo, accessToken_new, null, start_follow, end_follow, page_no);
				}		
			}else {//正常获取到数据
				JsonNode node = root.path("response");		
				int total_results = node.path("total_results").asInt();//总记录数
				int total_pages =  total_results/PAGE_SIZE + 1;//总页数
							
				for (JsonNode kdtFan : node.path("users")) {				
					SysFans fans = new SysFans();
					fans.setAppid_userId(kdtFan.path("user_id").asText());
					fans.setOpenId(kdtFan.path("weixin_openid").asText());
					fans.setNickName(kdtFan.path("nick").asText());//昵称
					fans.setHeadimgurl(kdtFan.path("avatar").asText());//头像
					
					fans.setSubscribeTime(sdf.parse(kdtFan.path("follow_time").asText()));//关注时间
					fans.setSubscribeFlag(Byte.valueOf("1"));//关注标识为已关注
					
					String sex = kdtFan.path("sex").asText();
					if (sex == null || sex.length() == 0) {
						fans.setSex(Byte.valueOf("0"));
					} else {
						fans.setSex(sex.equals("m") ? Byte.valueOf("1") : Byte.valueOf("2"));
					}
					fans.setProv(kdtFan.path("province").asText());//省份
					fans.setCity(kdtFan.path("city").asText());//城市
					fans.setUnionid(kdtFan.path("union_id").asText());//粉丝的unionId
					
					fans.setSourceType(Byte.valueOf("0"));//有赞来源只有微信公众号
					fans.setSerialNo(serialNo);//商户编号
					//通过商户编号serialNo，获取该商户的公众号，暂时未知，以后补上				
					//fans.setWxalipayId("unknown");
					
					//将有赞的粉丝标签，存于系统的remark字段中
					Map<Integer,String> tags = new HashMap<Integer,String>();
					for (JsonNode tag : kdtFan.get("tags")) {
						int tagId = tag.path("id").asInt();
						if (!tags.containsKey(tagId)) {
							tags.put(tagId, tag.path("name").asText());
						}			
					}				
					StringBuffer remark = new StringBuffer();
					for (int tagId : tags.keySet()) {
						remark.append(tags.get(tagId));	
					}
					fans.setRemark(remark.toString());
					//保存粉丝信息
					sysFansService.updateByOpenIdorUserId(fans);
				}	
				
				//获取下一页数据
				if (page_no < total_pages) {
					pullAndUpdate(serialNo, accessToken, refreshToken, start_follow, end_follow, page_no + 1);
				}		
			}	
		}
	}
	

}
