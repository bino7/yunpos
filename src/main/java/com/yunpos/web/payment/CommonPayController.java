package com.yunpos.web.payment;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.AlipayService;
import com.yunpos.service.payment.Message.PayStatus;
import com.yunpos.service.payment.PayParam;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.WorkingTimeUtil;

/**
 * 
 * 功能描述：通用支付Controller
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
@RequestMapping("/common-pay")
public class CommonPayController {
	Logger log = LoggerFactory.getLogger(AlipayController.class);
	@Autowired
	private SysPayOrderService sysPayOrderService;
	private AlipayService alipayService;

	@RequestMapping(value = "/create",headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody
	public Object create(@RequestParam("roleId")String roleId,
			@RequestParam("userId")String userId,
			@RequestParam("price")String price,
			@RequestParam("barCode")String barCode,
			@RequestParam("imei")String imei,
			@RequestParam("deviceType")String deviceType){
		long startTime = System.currentTimeMillis();
		
		if(Strings.isNullOrEmpty(roleId)||Strings.isNullOrEmpty(userId)
				||Strings.isNullOrEmpty(price)||Strings.isNullOrEmpty(barCode)
				||Strings.isNullOrEmpty(imei)||Strings.isNullOrEmpty(deviceType)){
			return new Message(false,"","传递参数为空！");
		}
		
		try {
			SysPayOrder payOrder = new SysPayOrder();
			//生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
		    IdWorker iw = new IdWorker(1, 1, 0, idepo);
		    payOrder.setPayOrderNo(iw.getId()+"");
		    payOrder.setStatus(Byte.parseByte("2"));//支付中
		    //payOrder.setPayCode(payCode);
		    payOrder.setRoleId(Integer.getInteger(roleId));
		    payOrder.setUserId(Integer.getInteger(userId));
		    payOrder.setPrice(Long.parseLong(price));
		    payOrder.setBarCode(barCode);
		    payOrder.setImei(imei);
		    payOrder.setDeviceType(Byte.valueOf(deviceType));
		    payOrder.setCreateAt(new Date());
		    payOrder.setCreateBy(Integer.getInteger(userId));	
		    //保存支付流水
		    sysPayOrderService.save(payOrder);
		    
		    //调用支付接口发起支付请求
		    PayParam payParam  =buildPayParam(payOrder);
		    com.yunpos.service.payment.Message payMsg =  alipayService.pay(payParam);
			if(payMsg.getStatus() == PayStatus.REQUEST_SUCCESS){
				//操作时间记录日志
				StringBuffer actionInfo = WorkingTimeUtil.doTime(startTime,"支付宝条码支付成功");
				log.error(actionInfo.toString());
				return new Message(true,"","支付宝条码支付成功！");
			}
		} catch (Exception e) {
			log.error("支付出现异常：",e);
			return new Message(false,"","支付出现异常"+e.getMessage());
		}
		return new Message(true,"","支付宝条码支付成功！");
	}
	
	
	private PayParam buildPayParam(SysPayOrder sysPayOrder){
		 PayParam param = new PayParam();
		 param.setOrderNo(sysPayOrder.getPayOrderNo());
		 param.setBarCode(sysPayOrder.getBarCode());
		 param.setChannel("alipay");
		 param.setPrice(sysPayOrder.getPrice()+"");
		 param.setOrderTitle("xxxxxxxxxx");
		 return param;
	}


}
