package com.yunpos.quartz.KDT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.yunpos.service.KDT.KdtPushService;



@Component
public class KdtPushServiceListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger log = LoggerFactory.getLogger(KdtPushServiceListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		if (context.getParent() ==null) {//防止重复执行		
			KdtPushService kdtPushService = (KdtPushService)context.getBean("kdtPushService");
			try {
				//kdtPushService.pushAndUpdate();
				//log.info("-------有赞推送服务KdtPushService已启动--------");
			} catch (Exception e) {
				log.info("-------有赞推送服务KdtPushService启动出错--------");
				e.printStackTrace();
			}
		}	
	}
	
	

}
