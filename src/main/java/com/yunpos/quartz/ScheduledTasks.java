package com.yunpos.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yunpos.service.KDT.KdtFansService;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
	@Autowired
	KdtFansService dktFansService;

	
//    @Scheduled(fixedRate = 1000 * 30)
//    public void reportCurrentTime(){
//        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
//    }
//
//    //每1分钟执行一次
//    @Scheduled(cron = "0 */1 *  * * * ")
//    public void reportCurrentByCron(){
//        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
//    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
    
    //定期获取有赞粉丝信息
    //cron格式: [秒] [分] [小时] [日] [月] [周] [年]
    @Scheduled(cron = "0 0 1,13 * * ?")
    public void getKdtFans() {
    	System.out.println("定时获取有赞粉丝数据，时间：" + dateFormat ().format (new Date ()));
    	//int field = Calendar.DAY_OF_MONTH;
    	int field = Calendar.HOUR;
		dktFansService.getFans(field, -12);
    }
 
 
 
 
}