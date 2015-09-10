package com.yunpos.service.KDT;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.KDT.KdtApiClient;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysOrderEcommerce;
import com.yunpos.model.SysShop;
import com.yunpos.service.SysOrderEcommerceService;
import com.yunpos.service.SysOrderService;
import com.yunpos.service.SysShopService;

/**
 * 有赞订单服务类，由外部定时器定时调用
 * @author janice
 *
 */
@Component
public class KdtOrderService {
	private static final int PAGE_SIZE = 50; //每页50条
	private static final String OrderMethod = "kdt.trades.sold.get";
	
	@Autowired
	SysShopService sysShopService;
	
	@Autowired
	KdtTokenService kdtTokenService;
	
	@Autowired
	SysOrderService sysOrderService;

	@Autowired
	SysOrderEcommerceService sysOrderEcommerceService;
	
	/**
	 * 
	 * @param field  维度，比如天(Calendar.DAY_OF_MONTH)，小时(Calendar.HOUR)
	 * @param amount 值
	 */
	public void getOrders(int field,int amount) {
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(field, amount);
		//cal.add(Calendar.DAY_OF_MONTH, -200);
		//cal.add(Calendar.HOUR, 12);			
		Date startDate = cal.getTime();
		
		List<SysShop> shops = sysShopService.findAllByToken();
		//对应其中的每一个店铺，进行订单数据的获取
		for (SysShop shop : shops) {
			try {
				pullAndUpdate(shop.getOid(),shop.getAccessToken(), shop.getRefreshToken(), startDate, endDate, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
	}
	
	/**
	 * 获取一段时间内状态有更新的订单，进行订单退款、退货、收货确认状态的更新
	 * @param shopId
	 * @param accessToken
	 * @param start_update
	 * @param end_update
	 * @param page_no
	 * @throws Exception
	 */
	public void pullAndUpdate(String shopId, String accessToken, String refreshToken, Date start_update,
			Date end_update, int page_no) throws Exception {
		// 通过access_token获取一段时间
		StringBuffer url = new StringBuffer(KdtTokenService.KDTURL);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		url = url.append("?access_token=").append(accessToken).append("&method=").append(OrderMethod)
				.append("&start_update=").append(sdf.format(start_update))
				.append("&end_update=").append(sdf.format(end_update))
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
			if (root.path("error").asText().length() > 0) {// token过期，需重新过期Token
				if (refreshToken != null && refreshToken.length() > 0) {
					// 根据refresh Token获取新的token
					String accessToken_new = kdtTokenService.freshToken(refreshToken);
					// 用新的Token再执行一次
					pullAndUpdate(shopId, accessToken_new, null, start_update, end_update, page_no);
				}
			} else {// 正常获取到数据
				JsonNode node = root.path("response");
				int total_results = node.path("total_results").asInt();// 总记录数
				int total_pages = total_results / PAGE_SIZE + 1;// 总页数

				for (JsonNode kdtOrder : node.path("trades")) {			
					String update_time = kdtOrder.path("update_time").asText();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date updateTime = format.parse(update_time);//订单更新时间
					
					String orderId = kdtOrder.path("tid").asText();// 订单号
					// 通过订单和店铺，查询对应的sysOrder和sysOrderEcommerce
					SysOrder order = sysOrderService.findByshopIdAndOrderid(shopId, orderId);
					int baseOrderId = 0;
					if (order != null) {
						baseOrderId = order.getId();
					}
					SysOrderEcommerce orderEcommerce = sysOrderEcommerceService.findByBaseOrderId(baseOrderId);
					if (order != null && orderEcommerce != null) {
						Boolean updateFlag = false; //是否要做修改操作的标识，默认为false
						
						String refundState = kdtOrder.path("refund_state").asText();// 退款状态
						if (refundState != null) {
							switch (refundState) {
							case "PARTIAL_REFUNDING": // 部分退款中
							case "FULL_REFUNDING": // 全额退款中
								order.setPayStatus(Byte.valueOf("4"));// 退款中
								updateFlag = true;
								break;
							case "PARTIAL_REFUNDED": // 已部分退款
							case "FULL_REFUNDED": // 已全额退款
								order.setPayStatus(Byte.valueOf("3"));// 已退款
								updateFlag = true;
								break;
							case "PARTIAL_REFUND_FAILED":// 部分退款失败
							case "FULL_REFUND_FAILED": // 全额退款失败
								order.setPayStatus(Byte.valueOf("5"));// 退款失败
								updateFlag = true;
							default:
								break;
							}
						}

						int feedback = kdtOrder.path("feedback").asInt();// 交易维权状态
						if (feedback >= 106) {
							orderEcommerce.setSendStatus(Byte.valueOf("3"));// 发货状态为已退货
							orderEcommerce.setCheckFlag(Byte.valueOf("2"));// 签收状态为已退货
							orderEcommerce.setCheckTime(updateTime);// 签收时间
							updateFlag = true;
						}

						String status = kdtOrder.path("status").asText();// 交易状态
						if (status.equals("TRADE_BUYER_SIGNED")) {// 买家已签收
							orderEcommerce.setCheckFlag(Byte.valueOf("1"));// 签收状态为已签收
							updateFlag = true;
						}
						
						if (updateFlag) { //只有updateFlag为true，才更新数据库
							order.setUpdatedAt(updateTime);// 更新时间
							// 进行电商订单主从表更新
							sysOrderEcommerceService.updateOrderEcommerce(order, orderEcommerce);
						}
					}			
				}
				
				
				// 获取下一页数据
				if (page_no < total_pages) {
					pullAndUpdate(shopId, accessToken, refreshToken, start_update, end_update, page_no + 1);
				}

			}
		}

	}
}
