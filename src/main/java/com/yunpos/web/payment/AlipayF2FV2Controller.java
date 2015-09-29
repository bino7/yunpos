package com.yunpos.web.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.SysPayOrder;
import com.yunpos.payment.alipay.config.AlipayConfig;
import com.yunpos.payment.alipay.model.AlipayScanPayReqData;
import com.yunpos.utils.IdWorker;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;

/**
 * 
 * 功能描述：支付宝当面付V2控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月28日
 * @author Devin_Yang 修改日期：2015年8月28日
 * 
 * 文档地址：https://app.alipay.com/market/document.htm?name=tiaomazhifu#page-15
 *
 */
@Controller
@RequestMapping("/alipay/v2")
public class AlipayF2FV2Controller {
	Logger log = LoggerFactory.getLogger(AlipayController.class);
	
	/**
	 * 支付宝当面付V2条码支付请求
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bar/pay")
	@ResponseBody
	public Object pay(HttpServletRequest request, HttpServletResponse response) {
		String pay_channel = request.getParameter("pay_channel");
		String total_fee = request.getParameter("total_fee"); // 支付金额（非空）
		String dynamic_id = request.getParameter("dynamic_id"); // 支付码（非空）
		String merchant_num = request.getParameter("merchant_num"); // 商户号（非空）
		String terminal_unique_no = request.getParameter("terminal_unique_no"); // 终端编号（非空）
		String cashier_num = request.getParameter("cashier_num"); // 核销码（可空）
		String client_type = request.getParameter("client_type"); // 客户端类型（PC、Web、POS、DLL）（非空）

		String body = request.getParameter("body"); //商品描述信息，支付成功时用户在支付结果中看到
		if (Strings.isNullOrEmpty(pay_channel) || Strings.isNullOrEmpty(total_fee) || Strings.isNullOrEmpty(dynamic_id)
				|| Strings.isNullOrEmpty(merchant_num) || Strings.isNullOrEmpty(terminal_unique_no)
				|| Strings.isNullOrEmpty(client_type)) {
			return new Message(ResultCode.FAIL.name(),ErrorCode.PARAM_IS_NULL.name(), "传递参数为空！", null);
		}
		
		Message payMsg = null;
		try {
//			SysTransaction sysTransaction = new SysTransaction();
//			sysTransaction.setAgentSerialNo(agentSerialNo);
//			sysTransaction.setChannel(channel);
//			sysTransaction.setCouponCode(couponCode);
//			sysTransaction.setId(id);
//			sysTransaction.setInfo(info);
//			sysTransaction.setMerchantName(merchantName);
//			sysTransaction.setOrderId(orderId);
//			sysTransaction.setScanType(scanType);
//			sysTransaction.setSerialNo(serialNo);
//			sysTransaction.setStatus(status);
//			sysTransaction.setSubChannel(subChannel);
//			sysTransaction.setTerminalNum(terminalNum);
//			sysTransaction.getTitle()
//			sysTransaction.setTotalPrice(totalPrice);
//			sysTransaction.setTransCardNum(transCardNum);
//			sysTransaction.setTransNum(transNum);
//			sysTransaction.setTransPrice(transPrice);
//			sysTransaction.setTransTime(transTime);
//			sysTransaction.setTransType(transType);
			
			SysPayOrder payOrder = new SysPayOrder();
			// 生成流水表信息
			final long idepo = System.currentTimeMillis() - 3600 * 1000L;
			IdWorker iw = new IdWorker(idepo);
			String orderNo = iw.getId() + "";
			
			AlipayScanPayReqData payReqData = new AlipayScanPayReqData(orderNo, AlipayConfig.partner, "支付宝条码支付"
					, total_fee, dynamic_id,"");
			payReqData.setPay_channel(pay_channel);
			payReqData.setTerminal_unique_no(terminal_unique_no);
			payReqData.setMerchant_num(merchant_num);
			
			//payMsg = alipayService.pay(payReqData);
		} catch (Exception e) {
			log.error("支付出现异常：", e);
			return new Message(ResultCode.FAIL.name(),ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
		return payMsg;
	}
	
	/**
	 *  支付宝当面付V2条码支付查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bar/query")
	@ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	/**
	 * 支付宝当面付V2条码支付撤单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bar/cancel")
	@ResponseBody
	public Object cancel(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	/**
	 * 支付宝当面付V2条码支付申请退款
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bar/refund")
	@ResponseBody
	public Object refund(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	
	
	

}
