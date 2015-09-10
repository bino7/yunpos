package com.yunpos.KDT;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.model.SysFans;
import com.yunpos.service.SysFansService;
import com.yunpos.service.KDT.KdtTokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SysFansServiceTest {

	@Autowired
	SysFansService sysFansService;
	
	//@Test
	public void getFans() {
		SysFans sysFans = sysFansService.findByOid("519261387");
		System.out.println("OpenId:" + sysFans.getOpenId());
	}
	
	//@Test
	public void getOrderbyRefund() throws Exception {
		StringBuffer url = new StringBuffer(KdtTokenService.KDTURL);
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH, -20);	
		Date startDate = cal.getTime();
		String accessToken = "c8cb1eb262f07f08e6bf0a5b29b304fc7535804e";
		String tradeMethod = "kdt.trades.sold.get";
		String status = "TRADE_CLOSED";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		url = url.append("?access_token=").append(accessToken).append("&method=").append(tradeMethod)
				.append("&status=").append(status)
				.append("&start_update=").append(sdf.format(startDate))
				.append("&end_update=").append(sdf.format(endDate))
				.append("&page_no=").append("1").append("&page_size=").append("80");	
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
			System.out.println("content:" + content);
		}		
	}
	
	//@Test
	public void getOrderbytid() throws Exception {
		StringBuffer url = new StringBuffer(KdtTokenService.KDTURL);
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH, -20);	
		Date startDate = cal.getTime();
		String accessToken = "c8cb1eb262f07f08e6bf0a5b29b304fc7535804e";
		String tradeMethod = "kdt.trade.get";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		url = url.append("?access_token=").append(accessToken).append("&method=").append(tradeMethod)
				.append("&tid=").append("E20150910094619089542928");	
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
			System.out.println("content:" + content);
		}		
	}
}
