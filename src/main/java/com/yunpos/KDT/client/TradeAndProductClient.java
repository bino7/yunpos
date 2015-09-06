package com.yunpos.KDT.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youzan.push.client.MessageClient;
import com.youzan.push.client.remoting.MessageConnector;
import com.youzan.push.protocol.message.MessageType;
import com.youzan.push.protocol.message.system.Register;

/**
 * 实时获取有赞订单和商品客户端
 * @author janice
 *
 */
public class TradeAndProductClient {
	private static final String IP = "180.150.190.134";
	private static final int PORT = 8888;
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

	private static final Logger log = LoggerFactory.getLogger(MessageClient.class);
	private MessageConnector connector;

	public void connect() throws Exception {
		Register registerInfo = new Register();
		registerInfo.setApps(this.apps);
		registerInfo.setVersion(this.version);
		registerInfo.setMessageType(MessageType.REGISTER);

		connector = new MessageConnector(IP, PORT, registerInfo);
		connector.connect();
	}

	public void close() {
		try {
			if (connector != null)
				connector.shutdown();
		} catch (Exception e) {
			log.error("shutdown connector goes wrong at:{}", e);
		}
	}

	public boolean isClose() {
		if (connector != null)
			return connector.isShutDown();
		return true;
	}

}
