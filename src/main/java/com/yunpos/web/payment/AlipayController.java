package com.yunpos.web.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Objects;
import com.yunpos.payment.alipay.util.AlipayNotify;
import com.yunpos.service.payment.AlipayService;
import com.yunpos.utils.DateUtil;

/**
 * 
 * 功能描述：支付宝支付Controller
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 *
 */
@Controller
@RequestMapping("/pay/alipay")
public class AlipayController {
	Logger log = LoggerFactory.getLogger(AlipayController.class);
	
	@Autowired
	private AlipayService alipayService;
	
	/**
	 * 支付宝异步通知
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notify")
	public void asynNotify(HttpServletRequest request,HttpServletResponse response){
		log.info("收到支付宝支付异步通知");
		try {
			PrintWriter writer = response.getWriter();
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			log.info("支付宝异步通知参数：",params);
			//商户网站唯一订单号
			String out_trade_no = request.getParameter("out_trade_no");
			//对应商户网站的订单系统中的唯一订单号
			//交易状态
			String trade_status = request.getParameter("trade_status");
			//String subject = request.getParameter("subject");
			//交易目前所处的状态(例如：TRADE_SUCCESS)
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if(AlipayNotify.verify(params)){//验证成功
				if(!Objects.equal("TRADE_CLOSED", trade_status)){
					alipayService.notifyPayment(out_trade_no, true, DateUtil.formatDate(params.get("gmt_payment").trim(), "yyyy-MM-dd HH:mm:ss"),null);
				}else{
					alipayService.notifyPayment(out_trade_no, false, null, "TRADE_CLOSED");
				}
				writer.write("success");
				writer.flush();
			}else{//验证失败
				log.info("支付宝异步通知请求验证失败...");
				writer.write("fail");
				writer.flush();
			}
		} catch (IOException e) {
			log.error("处理支付宝异步通知异常",e);
		}
	}

}
