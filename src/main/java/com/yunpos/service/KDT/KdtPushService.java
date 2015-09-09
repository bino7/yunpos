package com.yunpos.service.KDT;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youzan.push.client.event.EventType;
import com.youzan.push.client.event.MessageEvent;
import com.youzan.push.client.event.MessageListener;
import com.youzan.push.client.event.MessageSource;
import com.youzan.push.client.remoting.ConnectorState;
import com.youzan.push.protocol.message.Message;
import com.youzan.push.protocol.message.MessageType;
import com.youzan.push.protocol.message.business.BusinessEvent;
import com.youzan.push.protocol.message.business.goods.Goods;
import com.youzan.push.protocol.message.business.trade.Order;
import com.youzan.push.protocol.message.business.trade.Trade;
import com.yunpos.KDT.client.TradeAndProductClient;
import com.yunpos.model.ProductBean;
import com.yunpos.model.SysOrder;
import com.yunpos.model.SysOrderEcommerce;
import com.yunpos.service.SysFansService;
import com.yunpos.service.SysOrderEcommerceService;
import com.yunpos.service.SysOrderService;

/**
 * 有赞PUSH服务，自动推送订单和商品的变更数据过来
 * 
 * @author janice
 *
 */
@Component
public class KdtPushService {
	private static final Logger log = LoggerFactory.getLogger(KdtPushService.class);
	private static final String VERSION = "v2";
	private static final String client_id = "7718b70dad904f6f3b";
	private static final String client_secret = "05703a2a336f893f53f72d6406bce731";

	private TradeAndProductClient client = new TradeAndProductClient();

	@Autowired
	SysFansService sysFansService;

	@Autowired
	SysOrderService sysOrderService;

	@Autowired
	SysOrderEcommerceService sysOrderEcommerceService;

	/**
	 * 获取Trade数据，并进行相应处理
	 * 
	 * @throws Exception
	 */
	public void pushAndUpdate() throws Exception {
		Map<String, String> apps = new HashMap<String, String>();
		apps.put(client_id, client_secret);

		client.setVersion(VERSION);
		client.setApps(apps);
		client.connect();
		ConnectorState.startReconnectListener();
		MessageSource.addListener(new MessageListener() {
			@Override
			public void handleEvent(MessageEvent event) {
				if (EventType.CONNECTION_START == event.getEventType()) {// 连接成功
					log.info("Connect Success.");
				} else if (EventType.BUSINESS == event.getEventType()) {
					Message message = event.getMessage();
					short type = message.getMessageType();
					if (type == MessageType.TRADE) {// 交易
						Trade trade = (Trade) message;
						if (trade.getBussiness_event() == BusinessEvent.INSERT) {
							// do insert
							log.info("tradeinsert:" + trade.toString());

							SysOrder order = new SysOrder();
							SysOrderEcommerce orderEcommerce = new SysOrderEcommerce();
							order.setOrderId(trade.getTid());// 订单号

							String oid = String.valueOf(trade.getWeixin_user_id());
							order.setMemberId(oid);// 下单用户ID
							order.setOpenId(sysFansService.findByOid(oid).getOpenId());// 用户的OpenId
							order.setNickName(trade.getBuyer_nick());// 买家昵称
							order.setCreatedAt(trade.getCreated());// 下单时间
							order.setTrueName(trade.getReceiver_name());// 订单消费用户
							order.setTel(trade.getReceiver_mobile());// 订单消费用户联系电话
							// 构建productList
							List<ProductBean> products = new ArrayList<ProductBean>();
							for (Order kdtOrder : trade.getOrders()) {
								ProductBean productBean = new ProductBean();
								productBean.setName(kdtOrder.getTitle());// 商品名称
								productBean.setPrice(Float.valueOf(kdtOrder.getPrice()));// 商品价格
								productBean.setNum(kdtOrder.getNum());// 商品数量
								products.add(productBean);
							}
							ObjectMapper objectMapper = new ObjectMapper();
							Map<String, Object> productList = new HashMap<String, Object>();
							productList.put("products", products);
							try {
								order.setProductList(objectMapper.writeValueAsString(productList));
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}

							order.setProductPrice(Float.valueOf(trade.getTotal_fee()));// 商品总价格
							order.setTotal(trade.getNum());// 商品数量
							order.setMailPrice(Float.valueOf(trade.getPost_fee()));// 运费
							order.setCouponPrice(Float.valueOf(trade.getDiscount_fee()));// 优惠金额
							order.setTotalPrice(Float.valueOf(trade.getPayment()));// 订单总价格

							Date pay_time = trade.getPay_time();// 付款时间
							if (pay_time != null && pay_time.after(order.getCreatedAt())) {
								order.setPayTime(pay_time);
							}

							// 支付方式和支付类型
							String payType = trade.getPay_type();
							if (payType != null) {
								switch (payType) {
								case "WEIXIN": // 微信支付
									order.setPayMode(Byte.valueOf("0"));// 在线支付
									order.setPayType(Byte.valueOf("2"));// 微信支付
									break;
								case "ALIPAY": // 支付宝支付
									order.setPayMode(Byte.valueOf("0"));// 在线支付
									order.setPayType(Byte.valueOf("1"));// 支付宝支付
									break;
								case "BANKCARDPAY": // 银行卡支付
									order.setPayMode(Byte.valueOf("0"));// 在线支付
									order.setPayType(Byte.valueOf("0"));// 银联支付
									break;
								case "CODPAY": // 货到付款
									order.setPayMode(Byte.valueOf("1"));// 货到付款
									order.setPayType(Byte.valueOf("4"));// 其他
									break;
								case "BAIDUPAY": // 百度钱包支付
									order.setPayMode(Byte.valueOf("0"));// 在线支付
									order.setPayType(Byte.valueOf("3"));// 百度钱包支付
									break;
								default:// 其他
									order.setPayMode(Byte.valueOf("3"));// 其他
									order.setPayType(Byte.valueOf("4"));// 其他
									break;
								}
							}
							// 付款状态和订单处理状态
							String status = trade.getStatus();
							switch (status) {
							case "TRADE_NO_CREATE_PAY": // 没有创建支付交易
								order.setPayStatus(Byte.valueOf("0"));// 未付款
								orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
								orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
								order.setHandleStatus(Byte.valueOf("0"));// 订单未处理状态
								break;
							case "WAIT_BUYER_PAY": // 等待买家付款
								order.setPayStatus(Byte.valueOf("1"));// 付款中
								orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
								orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
								order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
								break;
							case "WAIT_SELLER_SEND_GOODS": // 等待卖家发货，即：买家已付款
								order.setPayStatus(Byte.valueOf("2"));// 已付款
								orderEcommerce.setSendStatus(Byte.valueOf("1"));// 正在发货
								orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
								order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
								break;
							case "WAIT_BUYER_CONFIRM_GOODS": // 等待买家确认收货，即：卖家已发货
								order.setPayStatus(Byte.valueOf("2"));// 已付款
								orderEcommerce.setSendStatus(Byte.valueOf("2"));// 已发货
								orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
								order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
								Date consign_time = trade.getConsign_time();// 发货时间
								if (consign_time != null && consign_time.after(order.getCreatedAt())) {
									orderEcommerce.setSendTime(consign_time);
								}
								break;
							case "TRADE_BUYER_SIGNED": // 买家已签收
								order.setPayStatus(Byte.valueOf("2"));// 已付款
								orderEcommerce.setSendStatus(Byte.valueOf("2"));// 已发货
								orderEcommerce.setCheckFlag(Byte.valueOf("1"));// 已签收
								order.setHandleStatus(Byte.valueOf("2"));// 订单已处理状态
								Date sign_time = trade.getSign_time();// 签收时间
								if (sign_time != null && sign_time.after(order.getCreatedAt())) {
									orderEcommerce.setCheckTime(sign_time);// 签收时间
								}
								break;
							case "TRADE_CLOSED": // 付款以后用户退款成功，交易自动关闭
								order.setPayStatus(Byte.valueOf("3"));// 已退款
								order.setHandleStatus(Byte.valueOf("2"));// 订单已处理状态
							case "TRADE_CLOSED_BY_USER": // 付款以前，卖家或买家主动关闭交易
								order.setPayStatus(Byte.valueOf("0"));// 未付款
								orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
								orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
								order.setHandleStatus(Byte.valueOf("3"));// 订单取消状态
								break;
							default:
								break;
							}
							order.setShopId(String.valueOf(trade.getShop_id()));// 店铺Id
							orderEcommerce.setPostcode(trade.getReceiver_zip());// 邮编
							orderEcommerce.setAddress(trade.getReceiver_state() + trade.getReceiver_city()
									+ trade.getReceiver_district() + trade.getReceiver_address());// 收货地址
							orderEcommerce.setMsg(trade.getBuyer_message());// 买家留言

							// 进行电商订单主从表保存
							sysOrderEcommerceService.insertOrderEcommerce(order, orderEcommerce);

						} else if (trade.getBussiness_event() == BusinessEvent.UPDATE) {
							// do update
							log.info("tradeupdate:" + trade.toString());

							String orderId = trade.getTid();
							String shopId = String.valueOf(trade.getShop_id());
							// 通过订单和店铺，查询对应的sysOrder和sysOrderEcommerce
							SysOrder order = sysOrderService.findByshopIdAndOrderid(shopId, orderId);
							int baseOrderId = 0;
							if (order!=null) {
								baseOrderId = order.getId();
							}						
							SysOrderEcommerce orderEcommerce = sysOrderEcommerceService.findByBaseOrderId(baseOrderId);
							if (order != null && orderEcommerce != null) {
								Date pay_time = trade.getPay_time();
								if (pay_time != null && pay_time.after(order.getCreatedAt())) {
									order.setPayTime(trade.getPay_time());// 付款时间
								}
								String payment = trade.getPayment();
								if (payment != null && payment.length() > 0) {
									order.setTotalPrice(Float.valueOf(payment));// 订单总价格
								}
								// 支付方式和支付类型
								String payType = trade.getPay_type();
								if (payType != null) {
									switch (payType) {
									case "WEIXIN": // 微信支付
										order.setPayMode(Byte.valueOf("0"));// 在线支付
										order.setPayType(Byte.valueOf("2"));// 微信支付
										break;
									case "ALIPAY": // 支付宝支付
										order.setPayMode(Byte.valueOf("0"));// 在线支付
										order.setPayType(Byte.valueOf("1"));// 支付宝支付
										break;
									case "BANKCARDPAY": // 银行卡支付
										order.setPayMode(Byte.valueOf("0"));// 在线支付
										order.setPayType(Byte.valueOf("0"));// 银联支付
										break;
									case "CODPAY": // 货到付款
										order.setPayMode(Byte.valueOf("1"));// 货到付款
										order.setPayType(Byte.valueOf("4"));// 其他
										break;
									case "BAIDUPAY": // 百度钱包支付
										order.setPayMode(Byte.valueOf("0"));// 在线支付
										order.setPayType(Byte.valueOf("3"));// 百度钱包支付
										break;
									default:// 其他
										order.setPayMode(Byte.valueOf("3"));// 其他
										order.setPayType(Byte.valueOf("4"));// 其他
										break;
									}
								}
								// 付款状态和订单处理状态
								String status = trade.getStatus();
								switch (status) {
								case "TRADE_NO_CREATE_PAY": // 没有创建支付交易
									order.setPayStatus(Byte.valueOf("0"));// 未付款
									orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
									orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
									order.setHandleStatus(Byte.valueOf("0"));// 订单未处理状态
									break;
								case "WAIT_BUYER_PAY": // 等待买家付款
									order.setPayStatus(Byte.valueOf("1"));// 付款中
									orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
									orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
									order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
									break;
								case "WAIT_SELLER_SEND_GOODS": // 等待卖家发货，即：买家已付款
									order.setPayStatus(Byte.valueOf("2"));// 已付款
									orderEcommerce.setSendStatus(Byte.valueOf("1"));// 正在发货
									orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
									order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
									break;
								case "WAIT_BUYER_CONFIRM_GOODS": // 等待买家确认收货，即：卖家已发货
									order.setPayStatus(Byte.valueOf("2"));// 已付款
									orderEcommerce.setSendStatus(Byte.valueOf("2"));// 已发货
									orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
									order.setHandleStatus(Byte.valueOf("1"));// 订单处理中状态
									if (trade.getUpdate_time().after(order.getCreatedAt())) {
										orderEcommerce.setSendTime(trade.getUpdate_time());// 发货时间
									}
									break;
								case "TRADE_BUYER_SIGNED": // 买家已签收
									order.setPayStatus(Byte.valueOf("2"));// 已付款
									orderEcommerce.setSendStatus(Byte.valueOf("2"));// 已发货
									orderEcommerce.setCheckFlag(Byte.valueOf("1"));// 已签收
									order.setHandleStatus(Byte.valueOf("2"));// 订单已处理状态
									if (trade.getUpdate_time().after(order.getCreatedAt())) {
										orderEcommerce.setCheckTime(trade.getUpdate_time());// 签收时间
									}
									break;
								case "TRADE_CLOSED": // 付款以后用户退款成功，交易自动关闭
									order.setPayStatus(Byte.valueOf("3"));// 已退款
									order.setHandleStatus(Byte.valueOf("2"));// 订单已处理状态
								case "TRADE_CLOSED_BY_USER": // 付款以前，卖家或买家主动关闭交易
									order.setPayStatus(Byte.valueOf("0"));// 未付款
									orderEcommerce.setSendStatus(Byte.valueOf("0"));// 未发货
									orderEcommerce.setCheckFlag(Byte.valueOf("0"));// 未签收
									order.setHandleStatus(Byte.valueOf("3"));// 订单取消状态
									break;
								default:
									break;
								}

								order.setUpdatedAt(trade.getUpdate_time());// 更新时间
								// 进行电商订单主从表更新
								sysOrderEcommerceService.updateOrderEcommerce(order, orderEcommerce);
							}
						}

					} else if (type == MessageType.GOODS) {// 商品
						Goods goods = (Goods) message;
						if (goods.getBussiness_event() == BusinessEvent.UPDATE) {
							// do goods update
							log.info("goodsUpdate:" + goods.toString());
						} else if (goods.getBussiness_event() == BusinessEvent.INSERT) {
							// do goods insert
							log.info("goodsinsert:" + goods.toString());
						}
					}
				}

			}
		});

	}

}
