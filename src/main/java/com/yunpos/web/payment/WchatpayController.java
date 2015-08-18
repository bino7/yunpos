package com.yunpos.web.payment;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.payment.wxpay.config.WechatPayConfig;
import com.yunpos.service.SysPayOrderService;
import com.yunpos.service.payment.Message.PayStatus;
import com.yunpos.service.payment.PayParam;
import com.yunpos.service.payment.WechatPayService;
import com.yunpos.utils.AmountUtils;
import com.yunpos.utils.DateUtil;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.WorkingTimeUtil;

/**
 * 
 * 功能描述：微信异步回调Controller
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
@RequestMapping("/pay/wechatpay")
public class WchatpayController {
	Logger log = LoggerFactory.getLogger(WchatpayController.class);

	@Autowired
	private WechatPayService wechatPayService;
	@Autowired
	private SysPayOrderService sysPayOrderService;

	/**
	 * 微信支付订单生成接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public Object wechatCreate(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		PayParam param = buildPayParam(request);

		if (Strings.isNullOrEmpty(param.getRoleId()) || Strings.isNullOrEmpty(param.getUserId())
				|| Strings.isNullOrEmpty(param.getPrice()) || Strings.isNullOrEmpty(param.getChannel())
				|| Strings.isNullOrEmpty(param.getImei()) || Strings.isNullOrEmpty(param.getDeviceType())
				|| Strings.isNullOrEmpty(param.getBarCode())) {
			return new Message(false, "", "传递参数为空！");
		}

		try {
			SysPayOrder payOrder = new SysPayOrder();
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			long orderNo = iw.getId();
			System.out.println("微信生成订单号：" + orderNo);
			String price = AmountUtils.changeY2F(param.getPrice());
			payOrder.setPayOrderNo(orderNo + "");
			payOrder.setStatus(Byte.parseByte("2"));// 支付中
			payOrder.setRoleId(Integer.getInteger(param.getRoleId()));
			payOrder.setUserId(Integer.getInteger(param.getUserId()));
			payOrder.setPrice(Long.valueOf(price)); // 微信支付单位分
			payOrder.setBarCode(param.getBarCode());
			payOrder.setImei(param.getImei());
			payOrder.setDeviceType(Byte.valueOf(param.getDeviceType()));
			payOrder.setCreateAt(new Date());
			payOrder.setCreateBy(Integer.getInteger(param.getUserId()));
			// 保存支付流水
			sysPayOrderService.save(payOrder);

			// 调用支付接口发起支付请求
			param.setOrderNo(orderNo + "");
			com.yunpos.service.payment.Message payMsg = wechatPayService.scanPay(param);
			if (payMsg.getStatus() == PayStatus.REQUEST_SUCCESS) {
				// 操作时间记录日志
				//StringBuffer actionInfo = WorkingTimeUtil.doTime(startTime, "微信条码支付成功");
				//log.info(actionInfo.toString());
				return new Message(true, "", "微信条码支付成功！");
			}
		} catch (Exception e) {
			log.error("微信支付出现异常：", e);
			return new Message(false, "", "微信支付出现异常" + e.getMessage());
		}
		return new Message(true, "", "微信条码支付成功！");
	}

	/**
	 * 支付宝异步通知
	 * 
	 * @param request
	 * @param response
	 */
//	@SuppressWarnings("rawtypes")
//	@RequestMapping("/notify")
//	public void asynNotify(HttpServletRequest request, HttpServletResponse response) {
//		log.info("收到微信支付异步通知");
//		try {
//			PrintWriter writer = response.getWriter();
//			// 获取支付宝POST过来反馈信息
//			Map<String, String> params = new HashMap<String, String>();
//			Map requestParams = request.getParameterMap();
//			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//				String name = (String) iter.next();
//				String[] values = (String[]) requestParams.get(name);
//				String valueStr = "";
//				for (int i = 0; i < values.length; i++) {
//					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//				}
//				params.put(name, valueStr);
//			}
//			log.info("支付宝异步通知参数：", params);
//			// 商户网站唯一订单号
//			String out_trade_no = request.getParameter("out_trade_no");
//			// 交易状态
//			String trade_status = request.getParameter("trade_status");
//			if (AlipayNotify.verify(params)) {// 验证成功
//				if (!Objects.equal("TRADE_CLOSED", trade_status)) {
//					alipayService.notifyPayment(params, true, "");
//				} else {
//					alipayService.notifyPayment(params, false, "TRADE_CLOSED");
//				}
//				writer.write("success");
//				writer.flush();
//			} else {// 验证失败
//				log.info("支付宝异步通知请求验证失败...");
//				writer.write("fail");
//				writer.flush();
//			}
//		} catch (IOException e) {
//			log.error("处理支付宝异步通知异常", e);
//		}
//	}

	private PayParam buildPayParam(HttpServletRequest request) {
		PayParam param = new PayParam();
		//接口传递参数
		param.setBarCode(request.getParameter("barCode"));
		param.setChannel(request.getParameter("channel"));
		param.setDeviceType(request.getParameter("deviceType"));
		param.setImei(request.getParameter("imei"));
		param.setPrice(request.getParameter("price"));
		param.setRoleId(request.getParameter("roleId"));
		param.setUserId(request.getParameter("userId"));
		log.info("支付传递参数：" + param.toString());
		
		//其他参数
		String timeStart = DateUtil.getNow("yyyyMMddHHmmss");
		param.setTimeStart(timeStart);
		param.setTimeExpire(DateUtil.getDateAfter(timeStart, "yyyyMMddHHmmss", 1));
		param.setBody("微信条码支付测试");
		param.setAttach("微信条码支付订单附加数据");
		param.setSpBillCreateIP("192.168.0.116");
		param.setGoodsTag("条码支付");
		return param;
	}

}
