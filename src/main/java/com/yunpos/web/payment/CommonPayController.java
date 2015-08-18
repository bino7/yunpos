//package com.yunpos.web.payment;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.google.common.base.Strings;
//import com.yunpos.model.SysPayOrder;
//import com.yunpos.service.SysPayOrderService;
//import com.yunpos.service.SysUserService;
//import com.yunpos.service.payment.AlipayService;
//import com.yunpos.service.payment.Message.PayStatus;
//import com.yunpos.service.payment.PayParam;
//import com.yunpos.utils.DateUtil;
//import com.yunpos.utils.IdWorker;
//import com.yunpos.utils.Message;
//import com.yunpos.utils.WorkingTimeUtil;
//
///**
// * 
// * 功能描述：通用支付订单创建Controller
// * <p>
// * 版权所有：小牛信息科技有限公司
// * <p>
// * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
// * 
// * @author Devin_Yang 新增日期：2015年8月12日
// * @author Devin_Yang 修改日期：2015年8月12日
// *
// */
//@Controller
//@RequestMapping("/pay")
//public class CommonPayController {
//	Logger log = LoggerFactory.getLogger(AlipayController.class);
//	@Autowired
//	private SysPayOrderService sysPayOrderService;
//	@Autowired
//	private AlipayService alipayService;
//	@Autowired
//	private SysUserService sysUserService;
//
//	/**
//	 * 支付宝订单生成接口
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/alipay/create")
//	@ResponseBody
//	public Object alipayCreate(HttpServletRequest request, HttpServletResponse response){
//		long startTime = System.currentTimeMillis();
//		PayParam param = buildPayParam(request);
//		
//		if(Strings.isNullOrEmpty(param.getRoleId())||Strings.isNullOrEmpty(param.getUserId())
//				||Strings.isNullOrEmpty(param.getPrice())||Strings.isNullOrEmpty(param.getChannel())
//				||Strings.isNullOrEmpty(param.getImei())||Strings.isNullOrEmpty(param.getDeviceType())
//				||Strings.isNullOrEmpty(param.getBarCode())){
//			return new Message(false,"","传递参数为空！");
//		}
//		
//		try {
//			SysPayOrder payOrder = new SysPayOrder();
//			//生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//		    IdWorker iw = new IdWorker(idepo);
//		    long orderNo = iw.getId();
//		    System.out.println("支付宝生成订单号："+orderNo);
//		    payOrder.setPayOrderNo(orderNo+"");
//		    payOrder.setStatus(Byte.parseByte("2"));//支付中
//		    payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
//		    payOrder.setUserId(Integer.getInteger(param.getUserId()));
//		    payOrder.setPrice(1l);
//		    payOrder.setBarCode(param.getBarCode());
//		    payOrder.setImei(param.getImei());
//		    payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
//		    payOrder.setCreateAt(new Date());
//		    payOrder.setCreateBy(Integer.getInteger(param.getUserId()));	
//		    //保存支付流水
//		    sysPayOrderService.save(payOrder);
//		    
//		    //调用支付接口发起支付请求
//		    param.setOrderNo(orderNo+"");
//		    param.setOrderTitle("支付宝条码支付");
//		    com.yunpos.service.payment.Message payMsg =  alipayService.pay(param);
//			if(payMsg.getStatus() == PayStatus.REQUEST_SUCCESS){
//				//操作时间记录日志
//				//StringBuffer actionInfo = WorkingTimeUtil.doTime(startTime,"支付宝条码支付成功");
//				//log.info(actionInfo.toString());
//				//payOrder.setStatus(Byte.parseByte("1"));
//				//sysPayOrderService.update(payOrder);
//				return new Message(true,"","支付宝条码支付成功！");
//			}
//		} catch (Exception e) {
//			log.error("支付出现异常：",e);
//			return new Message(false,"","支付出现异常"+e.getMessage());
//		}
//		return new Message(true,"","支付宝条码支付成功！");
//	}
//	
//	
//	/**
//	 * 微信支付订单生成接口
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/wechat/create")
//	@ResponseBody
//	public Object wechatCreate(HttpServletRequest request, HttpServletResponse response){
//		long startTime = System.currentTimeMillis();
//		PayParam param = buildPayParam(request);
//		
//		if(Strings.isNullOrEmpty(param.getRoleId())||Strings.isNullOrEmpty(param.getUserId())
//				||Strings.isNullOrEmpty(param.getPrice())||Strings.isNullOrEmpty(param.getChannel())
//				||Strings.isNullOrEmpty(param.getImei())||Strings.isNullOrEmpty(param.getDeviceType())
//				||Strings.isNullOrEmpty(param.getBarCode())){
//			return new Message(false,"","传递参数为空！");
//		}
//		
//		try {
//			SysPayOrder payOrder = new SysPayOrder();
//			//生成流水表信息
//			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
//		    IdWorker iw = new IdWorker(idepo);
//		    long orderNo = iw.getId();
//		    System.out.println("微信生成订单号："+orderNo);
//		    payOrder.setPayOrderNo(orderNo+"");
//		    payOrder.setStatus(Byte.parseByte("2"));//支付中
//		    payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
//		    payOrder.setUserId(Integer.getInteger(param.getUserId()));
//		    payOrder.setPrice(Long.parseLong(param.getPrice())*100);	//微信支付单位分
//		    payOrder.setBarCode(param.getBarCode());
//		    payOrder.setImei(param.getImei());
//		    payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
//		    payOrder.setCreateAt(new Date());
//		    payOrder.setCreateBy(Integer.getInteger(param.getUserId()));	
//		    //保存支付流水
//		    sysPayOrderService.save(payOrder);
//		    
//		    //调用支付接口发起支付请求
//		    param.setOrderNo(orderNo+"");
//		    String timeStart = DateUtil.getNow();
//		    param.setTimeStart(timeStart);
//		    param.setTimeExpire(DateUtil.getDateAfter(timeStart, "yyyyMMddHHmmss", 1));
//		    param.setOrderTitle("微信条码支付");
//		   // param.setSpBillCreateIP(request.getl);
//		    com.yunpos.service.payment.Message payMsg =  alipayService.pay(param);
//			if(payMsg.getStatus() == PayStatus.REQUEST_SUCCESS){
//				//操作时间记录日志
//				
//				StringBuffer actionInfo = WorkingTimeUtil.doTime(startTime,"微信条码支付成功");
//				log.info(actionInfo.toString());
//				return new Message(true,"","微信条码支付成功！");
//			}
//		} catch (Exception e) {
//			log.error("微信支付出现异常：",e);
//			return new Message(false,"","微信支付出现异常"+e.getMessage());
//		}
//		return new Message(true,"","微信条码支付成功！");
//	}
//	
//	
//	
//	
//	
//	
//	
//	private PayParam buildPayParam(HttpServletRequest request){
//		 PayParam param = new PayParam();
//		 param.setBarCode(request.getParameter("barCode"));
//		 param.setChannel(request.getParameter("channel"));
//		 param.setDeviceType(request.getParameter("deviceType"));
//		 param.setImei(request.getParameter("imei"));
//		 param.setPrice(request.getParameter("price"));
//		 param.setRoleId(request.getParameter("roleId"));
//		 param.setUserId(request.getParameter("userId"));
//		 log.info("支付传递参数："+param.toString());
//		 return param;
//	}
//
//
//}
