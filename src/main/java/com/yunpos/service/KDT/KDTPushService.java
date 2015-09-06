package com.yunpos.service.KDT;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youzan.push.client.event.EventType;
import com.youzan.push.client.event.MessageEvent;
import com.youzan.push.client.event.MessageListener;
import com.youzan.push.client.event.MessageSource;
import com.youzan.push.client.remoting.ConnectorState;
import com.youzan.push.protocol.message.Message;
import com.youzan.push.protocol.message.MessageType;
import com.youzan.push.protocol.message.business.BusinessEvent;
import com.youzan.push.protocol.message.business.goods.Goods;
import com.youzan.push.protocol.message.business.trade.Trade;
import com.yunpos.KDT.client.TradeAndProductClient;

/**
 * 有赞PUSH服务，自动推送订单和商品的变更数据过来
 * @author janice
 *
 */
public class KDTPushService {
	private static final Logger log = LoggerFactory.getLogger(KDTPushService.class);
	private static final String VERSION = "v2";
	private static final String client_id = "7718b70dad904f6f3b";
	private static final String client_secret = "05703a2a336f893f53f72d6406bce731";
	
	private TradeAndProductClient client = new TradeAndProductClient();
	
	/**
	 * 获取Trade数据，并进行相应处理
	 * @throws Exception
	 */
	public void pullAndUpdate() throws Exception {
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
							log.info("tradeinsert" + trade.toString());
							
							
							
							
							
							
							
						} else if (trade.getBussiness_event() == BusinessEvent.UPDATE) {
							// do update
							log.info("tradeupdate" + trade.toString());
						}

					} 
					/*else if (type == MessageType.GOODS) {// 商品
						Goods goods = (Goods) message;
						if (goods.getBussiness_event() == BusinessEvent.UPDATE) {
							// do goods update
							log.info("goodsUpdate:" + goods.toString());
						} else if (goods.getBussiness_event() == BusinessEvent.INSERT) {
							// do goods insert
							log.info("goodsinsert:" + goods.toString());
						}
					}
					*/
				}

			}
		});

	}

}
