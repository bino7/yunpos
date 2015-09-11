package com.yunpos.service.payment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.payment.wxpay.common.HttpsRequest;
import com.yunpos.payment.wxpay.common.Signature;
import com.yunpos.payment.wxpay.common.report.ReporterFactory;
import com.yunpos.payment.wxpay.common.report.protocol.ReportReqData;
import com.yunpos.payment.wxpay.common.report.service.ReportService;
import com.yunpos.payment.wxpay.config.WechatPayConfig;
import com.yunpos.payment.wxwap.model.WapPayReqData;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;
import com.yunpos.utils.XMLUtil;

/**
 * 
 * 功能描述：微信公众在线支付（公众账号支付、wap支付）
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年9月9日
 * @author Devin_Yang 修改日期：2015年9月9日
 *
 */
@Service
public class WechatWapPayService {
	private final static Logger log = LoggerFactory.getLogger(WechatPayService.class);
	private final static String  UNIFIEDORDER_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	
	/**
	 * 
	 * @param wapPayReqData
	 * @param sysWechatConfig
	 * @return
	 */
	public Message unifiedOrder(WapPayReqData wapPayReqData,SysWechatConfigWithBLOBs sysWechatConfig) {
		log.info("支付宝wap统一下单请求参数:" + wapPayReqData.toMap().toString());
		try {// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest(sysWechatConfig.getCertLocalPath(),sysWechatConfig.getCertPassword());
			String payServiceResponseString = serviceRequest.sendPost(UNIFIEDORDER_API,wapPayReqData,sysWechatConfig);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("同步返回结果：" + payServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(payServiceResponseString);
//			// 异步发送统计请求
//			ReportReqData reportReqData = new ReportReqData(responseXml.get("device_info"),
//					UNIFIEDORDER_API, (int) (totalTimeCost), // 本次请求耗时
//					responseXml.get("return_code"), responseXml.get("return_msg"), responseXml.get("result_code"),
//					responseXml.get("err_code"), responseXml.get("err_code_des"), responseXml.get("out_trade_no"),
//					responseXml.get("spbill_create_ip"),sysWechatConfig);
//			long timeAfterReport;
//			if (WechatPayConfig.useThreadToDoReport) {
//				ReporterFactory.getReporter(reportReqData,sysWechatConfig).run();
//				timeAfterReport = System.currentTimeMillis();
//				log.info("pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
//			} else {
//				ReportService.request(reportReqData,sysWechatConfig);
//				timeAfterReport = System.currentTimeMillis();
//				log.info("pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
//			}

			// 返回业务处理
			if (responseXml == null || Strings.isNullOrEmpty(responseXml.get("return_code"))) {
				log.error("【支付失败】支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
				// resultListener.onFailByReturnCodeError(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "RETURN_CODE_NULL",
						"支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问", null);
			}

			if (responseXml.get("return_code").equals("FAIL")) {
				// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
				log.error("【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
				return new Message(ResultCode.FAIL.name(), "FAIL", "支付API系统返回失败，请检测Post给API的数据是否规范合法", null);
			} else {
				log.info("支付API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString,sysWechatConfig.getAppKey())) {
					log.error("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
					// resultListener.onFailBySignInvalid(scanPayResData);
					return new Message(ResultCode.FAIL.name(), "CONTEXT_INCONSISTENT", "支付请求API返回的数据签名验证失败，有可能数据被篡改了",
							null);
				}

				if (responseXml.get("result_code").equals("SUCCESS")) {
					log.info("【统一下单成功】");
					//WapPayResData wapPayResData = new WapPayResData(responseXml,dtoMap);
					
					return new Message(ResultCode.SUCCESS.name(), "", "下单成功", responseXml); // 支付宝交易流水号
				} else {// result_code FAIL
					String errorCode = responseXml.get("err_code");
					String errorCodeDes = responseXml.get("err_code_des");
					log.error("业务返回失败,err_code" + errorCode + "|err_code_des:" + errorCodeDes);

					// 对于扣款明确失败的情况直接走撤销逻辑
					// doReverseLoop(scanPayReqData.getOut_trade_no());
					return new Message(ResultCode.FAIL.name(), errorCode, errorCodeDes, null);
				}
			}
		} catch (Exception e) {
			log.error("微信支付异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
	}

}
