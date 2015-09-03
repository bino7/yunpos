package com.yunpos.service.payment;

import static java.lang.Thread.sleep;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.yunpos.model.SysTransaction;
import com.yunpos.model.SysWechatConfig;
import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.payment.PayResData;
import com.yunpos.payment.QueryResData;
import com.yunpos.payment.RefundQueryResData;
import com.yunpos.payment.RefundResData;
import com.yunpos.payment.RefundResData.PayChannel;
import com.yunpos.payment.wxpay.common.Configure;
import com.yunpos.payment.wxpay.common.HttpsRequest;
import com.yunpos.payment.wxpay.common.Signature;
import com.yunpos.payment.wxpay.common.Util;
import com.yunpos.payment.wxpay.common.report.ReporterFactory;
import com.yunpos.payment.wxpay.common.report.protocol.ReportReqData;
import com.yunpos.payment.wxpay.common.report.service.ReportService;
import com.yunpos.payment.wxpay.config.WechatPayConfig;
import com.yunpos.payment.wxpay.protocol.close_protocol.CloseOrderReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanCodePayReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanCodePayResData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.yunpos.payment.wxpay.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundReqData;
import com.yunpos.payment.wxpay.protocol.refund_query_protocol.RefundQueryReqData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseReqData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseResData;
import com.yunpos.service.SysTransactionService;
import com.yunpos.service.SysWechatConfigService;
import com.yunpos.utils.Message;
import com.yunpos.utils.Message.ErrorCode;
import com.yunpos.utils.Message.ResultCode;
import com.yunpos.utils.XMLUtil;

/**
 * 
 * 功能描述：微信支付业务类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月12日
 * @author Devin_Yang 修改日期：2015年8月12日
 * 微信刷卡支付地址:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=5_1
 * 微信支付扫码支付：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_1
 * 微信支付公众号支付：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_1
 *
 */
@Service
public class WechatPayService {
	private final static Logger log = LoggerFactory.getLogger(WechatPayService.class);
	public static final String FLAG_NAME = "isSuccess";
	public static final String MSG_NAME = "msg";
	// 是否需要再调一次撤销，这个值由撤销API回包的recall字段决定
	private boolean needRecallReverse = false;
	private int waitingTimeBeforeReverseServiceInvoked = 5000;
	@Autowired
	private SysWechatConfigService sysWechatConfigService;
	@Autowired
	private SysTransactionService sysTransactionService;



	/**
	 * 微信支付同步方法业务逻辑处理
	 * 
	 * @param param
	 * @return
	 */
	public Message scanPay(ScanPayReqData scanPayReqData,SysWechatConfigWithBLOBs sysWechatConfig) throws Exception {
		log.info("支付宝条码支付请求参数:" + scanPayReqData.toMap().toString());
		try {// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest();
			String payServiceResponseString = serviceRequest.sendPost(WechatPayConfig.PAY_API, scanPayReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("同步返回结果：" + payServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(payServiceResponseString);
			// 异步发送统计请求
			ReportReqData reportReqData = new ReportReqData(scanPayReqData.getDevice_info(), Configure.PAY_API,
					(int) (totalTimeCost), // 本次请求耗时
					responseXml.get("return_code"), responseXml.get("return_msg"), responseXml.get("result_code"),
					responseXml.get("err_code"), responseXml.get("err_code_des"), responseXml.get("out_trade_no"),
					responseXml.get("spbill_create_ip"));
			long timeAfterReport;
			if (Configure.isUseThreadToDoReport()) {
				ReporterFactory.getReporter(reportReqData).run();
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			} else {
				ReportService.request(reportReqData);
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			}

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
					log.info("【一次性支付成功】");
					PayResData payResData = new PayResData(PayChannel.WECHAT, responseXml,
							scanPayReqData.toStringMap());
					return new Message(ResultCode.SUCCESS.name(), "", "支付成功", payResData.toMap()); // 支付宝交易流水号
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

	/**
	 * 支付查询
	 * 
	 * @param outTradeNo
	 *            订单号
	 * @return
	 * @throws Exception
	 */
	public Message query(String outTradeNo,String merchantNo) throws Exception {
		try {
			SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merchantNo);
			
			ScanPayQueryReqData scanPayQueryReqData = new ScanPayQueryReqData("", outTradeNo);
			HttpsRequest serviceRequest = new HttpsRequest();
			String payQueryServiceResponseString = serviceRequest.sendPost(WechatPayConfig.PAY_QUERY_API,
					scanPayQueryReqData);
			log.info("支付订单查询API返回的数据如下：" + payQueryServiceResponseString);
			Map<String, String> responseXml = XMLUtil.parse(payQueryServiceResponseString);

			if (responseXml == null || Strings.isNullOrEmpty(responseXml.get("return_code"))) {
				log.info("支付订单查询请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
				return new Message(ResultCode.FAIL.name(), "RETURN_CODE_NULL", "支付订单查询请求逻辑错误，请仔细检测传过去的每一个参数是否合法", null);
			}

			if (responseXml.get("return_code").equals("FAIL")) {
				// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
				log.error("支付订单查询API系统返回失败，失败信息为");
				// resultListener.onFailByReturnCodeFail(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "FAIL", responseXml.get("return_msg"), null);
			} else {
				log.info("支付API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(payQueryServiceResponseString,sysWechatConfig.getAppKey())) {
					log.error("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
					// resultListener.onFailBySignInvalid(scanPayResData);
					return new Message(ResultCode.FAIL.name(), "CONTEXT_INCONSISTENT", "支付请求API返回的数据签名验证失败，有可能数据被篡改了",
							null);
				}
				if (responseXml.get("result_code").equals("SUCCESS")
						&& responseXml.get("trade_state").equals("SUCCESS")) {
					log.info("查询到订单支付成功");
					QueryResData queryResData = new QueryResData(PayChannel.WECHAT, responseXml, null);
					return new Message(ResultCode.SUCCESS.name(), "", "支付成功", queryResData.toMap()); // 支付宝交易流水号
				} else {// result_code FAIL
					String errorCode = responseXml.get("err_code");
					String errorCodeDes = responseXml.get("err_code_des");
					log.error("查询出错，错误码err_code:" + errorCode + "|err_code_des:" + errorCodeDes);
					return new Message(ResultCode.FAIL.name(), errorCode, errorCodeDes, null);

				}
			}
		} catch (Exception e) {
			log.error("支付查询异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
	}

	/**
	 * 退款
	 * 
	 * @param refundReqData
	 * @return
	 * @throws Exception
	 */
	public Message refund(RefundReqData refundReqData,SysWechatConfigWithBLOBs sysWechatConfig) throws Exception {
		// 把请求参数打包成数组
		log.info("支付宝条码支付请求参数:" + refundReqData.toMap().toString());
		try {
			// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest refundService = new HttpsRequest();
			String refundServiceResponseString = refundService.sendPost(WechatPayConfig.REFUND_API, refundReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("返回参数:" + refundServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(refundServiceResponseString);
			// 异步发送统计请求
			ReportReqData reportReqData = new ReportReqData(refundReqData.getDevice_info(), WechatPayConfig.REFUND_API,
					(int) (totalTimeCost), // 本次请求耗时
					responseXml.get("return_code"), responseXml.get("return_msg"), responseXml.get("result_code"),
					responseXml.get("err_code"), responseXml.get("err_code_des"), responseXml.get("out_trade_no"),
					responseXml.get("spbill_create_ip"));
			long timeAfterReport;
			if (Configure.isUseThreadToDoReport()) {
				ReporterFactory.getReporter(reportReqData).run();
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			} else {
				ReportService.request(reportReqData);
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			}

			// 返回业务处理
			if (responseXml == null || Strings.isNullOrEmpty(responseXml.get("return_code"))) {
				log.error("退款API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
				// resultListener.onFailByReturnCodeError(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "RETURN_CODE_NULL",
						"退款API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问", null);
			}

			if (responseXml.get("return_code").equals("FAIL")) {
				// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
				log.error("退款API系统返回失败，请检测Post给API的数据是否规范合法");
				// resultListener.onFailByReturnCodeFail(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "FAIL", responseXml.get("return_msg"), null);
			} else {
				log.info("退款API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(refundServiceResponseString,sysWechatConfig.getAppKey())) {
					log.error("退款请求API返回的数据签名验证失败，有可能数据被篡改了");
					// resultListener.onFailBySignInvalid(scanPayResData);
					return new Message(ResultCode.FAIL.name(), "CONTEXT_INCONSISTENT", "退款请求API返回的数据签名验证失败，有可能数据被篡改了",
							null);
				}

				if (responseXml.get("result_code").equals("SUCCESS")) {
					log.info("退款成功");
					RefundResData refundResData = new RefundResData(PayChannel.WECHAT, responseXml,
							refundReqData.toMap2());
					return new Message(ResultCode.SUCCESS.name(), "", "退款成功", refundResData.toMap()); // 支付宝交易流水号

				} else {// result_code FAIL
					String errorCode = responseXml.get("err_code");
					String errorCodeDes = responseXml.get("err_code_des");
					log.error("业务返回失败,err_code" + errorCode + "|err_code_des:" + errorCodeDes);
					return new Message(ResultCode.FAIL.name(), errorCode, errorCodeDes, null);
				}
			}
		} catch (Exception e) {
			log.error("微信退款异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
	}

	/**
	 * 退款查询
	 * 
	 * @param refundReqData
	 * @return
	 * @throws Exception
	 */
	public Message refundQuery(RefundQueryReqData refundQueryReqData,SysWechatConfigWithBLOBs sysWechatConfig) throws Exception {
		// 把请求参数打包成数组
		log.info("退款查询请求参数:" + refundQueryReqData.toMap().toString());
		try {
			// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest();
			String refundQueryServiceResponseString = serviceRequest.sendPost(WechatPayConfig.REFUND_QUERY_API,
					refundQueryReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms" + "|退款查询API返回的数据如下:" + refundQueryServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(refundQueryServiceResponseString);
			// 异步发送统计请求
			ReportReqData reportReqData = new ReportReqData(refundQueryReqData.getDevice_info(),
					WechatPayConfig.REFUND_QUERY_API, (int) (totalTimeCost), // 本次请求耗时
					responseXml.get("return_code"), responseXml.get("return_msg"), responseXml.get("result_code"),
					responseXml.get("err_code"), responseXml.get("err_code_des"), responseXml.get("out_trade_no"),
					responseXml.get("spbill_create_ip"));
			long timeAfterReport;
			if (Configure.isUseThreadToDoReport()) {
				ReporterFactory.getReporter(reportReqData).run();
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			} else {
				ReportService.request(reportReqData);
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			}

			// 返回业务处理
			if (responseXml == null || Strings.isNullOrEmpty(responseXml.get("return_code"))) {
				log.error("退款查询API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
				// resultListener.onFailByReturnCodeError(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "RETURN_CODE_NULL",
						"退款查询API请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问", null);
			}

			if (responseXml.get("return_code").equals("FAIL")) {
				// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
				log.error("退款查询API系统返回失败，请检测Post给API的数据是否规范合法");
				// resultListener.onFailByReturnCodeFail(scanPayResData);
				return new Message(ResultCode.FAIL.name(), "FAIL", "退款查询API系统返回失败，请检测Post给API的数据是否规范合法", null);
			} else {
				log.info("退款查询API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(refundQueryServiceResponseString,sysWechatConfig.getAppKey())) {
					log.error("退款查询API返回的数据签名验证失败，有可能数据被篡改了");
					return new Message(ResultCode.FAIL.name(), "CONTEXT_INCONSISTENT", "退款查询API返回的数据签名验证失败，有可能数据被篡改了",
							null);
				}

				if (responseXml.get("result_code").equals("SUCCESS")) {
					log.info("退款查询成功");
					RefundQueryResData refundQueryResData = new RefundQueryResData(PayChannel.WECHAT, responseXml,
							null);
					return new Message(ResultCode.SUCCESS.name(), "", "查询成功", refundQueryResData.toMap()); // 微信支付订单号
				} else {// result_code FAIL
					String errorCode = responseXml.get("err_code");
					String errorCodeDes = responseXml.get("err_code_des");
					log.error("业务返回失败,err_code" + errorCode + "|err_code_des:" + errorCodeDes);
					return new Message(ResultCode.FAIL.name(), errorCode, errorCodeDes, null);
				}
			}
		} catch (Exception e) {
			log.error("微信退款查询异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
	}

	/**
	 * 撤单
	 * 
	 * @param outTradeNo
	 * @param resultListener
	 * @return
	 * @throws Exception
	 */
	private boolean doOneReverse(String outTradeNo) throws Exception {

		sleep(waitingTimeBeforeReverseServiceInvoked);// 等待一定时间再进行查询，避免状态还没来得及被更新

		String reverseResponseString;

		ReverseReqData reverseReqData = new ReverseReqData("", outTradeNo);
		HttpsRequest serviceRequest = new HttpsRequest();
		reverseResponseString = serviceRequest.sendPost(WechatPayConfig.REVERSE_API, reverseReqData);

		log.info("撤销API返回的数据如下：");
		log.info(reverseResponseString);
		// 将从API返回的XML数据映射到Java对象
		ReverseResData reverseResData = (ReverseResData) Util.getObjectFromXML(reverseResponseString,
				ReverseResData.class);
		if (reverseResData == null) {
			log.info("支付订单撤销请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
			return false;
		}
		if (reverseResData.getReturn_code().equals("FAIL")) {
			// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
			log.info("支付订单撤销API系统返回失败，失败信息为：" + reverseResData.getReturn_msg());
			return false;
		} else {

			if (!Signature.checkIsSignValidFromResponseString(reverseResponseString)) {
				log.info("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
				// resultListener.onFailByReverseSignInvalid(reverseResData);
				needRecallReverse = false;// 数据被窜改了，不需要再重试
				return false;
			}

			if (reverseResData.getResult_code().equals("FAIL")) {
				log.info("撤销出错，错误码：" + reverseResData.getErr_code() + "     错误信息：" + reverseResData.getErr_code_des());
				if (reverseResData.getRecall().equals("Y")) {
					// 表示需要重试
					needRecallReverse = true;
					return false;
				} else {
					// 表示不需要重试，也可以当作是撤销成功
					needRecallReverse = false;
					return true;
				}
			} else {
				// 查询成功，打印交易状态
				log.info("支付订单撤销成功");
				return true;
			}
		}
	}

	/**
	 * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，
	 * 是否需要继续循环调用撤销API由撤销API回包里面的recall字段决定。
	 *
	 * @param outTradeNo
	 *            商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
	 * @param resultListener
	 *            商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
	 * @throws InterruptedException
	 */
	private void doReverseLoop(String outTradeNo) throws Exception {
		// 初始化这个标记
		needRecallReverse = true;
		// 进行循环撤销，直到撤销成功，或是API返回recall字段为"Y"
		while (needRecallReverse) {
			if (doOneReverse(outTradeNo)) {
				return;
			}
		}
	}

	/**
	 * 微信扫码支付统一下单
	 * 
	 * @param scanCodePayReqData
	 * @return
	 */
	public Message unifiedOrder(ScanCodePayReqData scanCodePayReqData,SysWechatConfigWithBLOBs sysWechatConfig) {
		log.info("支付宝扫码统一下单请求参数:" + scanCodePayReqData.toMap().toString());
		try {// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest();
			String payServiceResponseString = serviceRequest.sendPost(WechatPayConfig.scan_unifiedorder_api,
					scanCodePayReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("同步返回结果：" + payServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(payServiceResponseString);
			// 异步发送统计请求
			ReportReqData reportReqData = new ReportReqData(responseXml.get("device_info"),
					WechatPayConfig.scan_unifiedorder_api, (int) (totalTimeCost), // 本次请求耗时
					responseXml.get("return_code"), responseXml.get("return_msg"), responseXml.get("result_code"),
					responseXml.get("err_code"), responseXml.get("err_code_des"), responseXml.get("out_trade_no"),
					responseXml.get("spbill_create_ip"));
			long timeAfterReport;
			if (Configure.isUseThreadToDoReport()) {
				ReporterFactory.getReporter(reportReqData).run();
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			} else {
				ReportService.request(reportReqData);
				timeAfterReport = System.currentTimeMillis();
				log.info("pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
			}

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
					ScanCodePayResData scanCodePayResData = new ScanCodePayResData(responseXml);
					return new Message(ResultCode.SUCCESS.name(), "", "下单成功", scanCodePayResData.toMap()); // 支付宝交易流水号
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

	/**
	 * 关闭订单
	 * 
	 * @param closeOrderReqData
	 * @return
	 */
	public Message closeOrder(CloseOrderReqData closeOrderReqData) {
		log.info("支付宝扫码统一下单请求参数:" + closeOrderReqData.toMap().toString());
		try {// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest();
			String payServiceResponseString = serviceRequest.sendPost(WechatPayConfig.CLOSE_ORDER_API,
					closeOrderReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("同步返回结果：" + payServiceResponseString);

			Map<String, String> responseXml = XMLUtil.parse(payServiceResponseString);

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
				return new Message(ResultCode.FAIL.name(), "FAIL", responseXml.get("return_msg"), null);
			} else {
				log.info("支付API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
					log.error("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
					// resultListener.onFailBySignInvalid(scanPayResData);
					return new Message(ResultCode.FAIL.name(), "CONTEXT_INCONSISTENT", "支付请求API返回的数据签名验证失败，有可能数据被篡改了",
							null);
				}
				log.info("【关闭订单成功】");
				ScanCodePayResData scanCodePayResData = new ScanCodePayResData(responseXml);
				return new Message(ResultCode.SUCCESS.name(), "", "关闭订单成功", scanCodePayResData.toMap()); // 支付宝交易流水号
			}
		} catch (Exception e) {
			log.error("微信支付异常:", e);
			return new Message(ResultCode.FAIL.name(), ErrorCode.SYSTEM_EXCEPTION.name(), "支付出现异常！", null);
		}
	}

	/**
	 * 支付宝异步回调处理函数
	 * 
	 * @param orderNo
	 * @param isSuccess
	 * @param paymentDate
	 * @param resultMsg
	 * @return
	 */

	 public Map<String, Object> scanNotify(Map<String, String> params,
	 boolean isSuccess, String resultMsg) {
		 Map<String, Object> returnMap = Maps.newHashMap();
			String orderNo = params.get("out_trade_no");

			if (Strings.isNullOrEmpty(orderNo)) {
				String msg = "无法完成支付，支付接口的回调没有订单号信息！";
				log.error(msg);
				returnMap.put(FLAG_NAME, false);
				returnMap.put(MSG_NAME, msg);
				return returnMap;
			}

			
			SysTransaction  sysTransaction = sysTransactionService.findByTransNum(orderNo);
			if (sysTransaction == null) {
				String msg = "无法完成支付，支付接口返回的订单号[" + orderNo + "]无效！";
				log.error(msg);
				returnMap.put(FLAG_NAME, false);
				returnMap.put(MSG_NAME, msg);
				return returnMap;
			}

			if (sysTransaction.getStatus() == Byte.valueOf("2")) {
				String msg = "支付接口返回的订单流水[" + orderNo + "]已经完成支付，无需再次处理！";
				log.warn(msg);
				returnMap.put(FLAG_NAME, true);
				returnMap.put(MSG_NAME, msg);
				return returnMap;
			}

			if (isSuccess) {
				sysTransaction.setStatus(Byte.valueOf("2"));// 支付成功
				sysTransaction.setTransPrice(Float.valueOf(params.get("total_fee"))); //实际交易金额
				sysTransactionService.update(sysTransaction);
				String msg = "订单[" + orderNo+ "]支付交易成功！";
				log.info(msg);
				returnMap.put(FLAG_NAME, true);
				returnMap.put(MSG_NAME, msg);
				return returnMap;
			} else {
				sysTransaction.setStatus(Byte.valueOf("6"));// 付款失败
				sysTransactionService.update(sysTransaction);
				String msg = "支付接口返回的订单[" + orderNo+ "]支付失败！";
				log.info(msg);
				returnMap.put(FLAG_NAME, false);
				returnMap.put(MSG_NAME, msg);
				return returnMap;
			}
	 }
}
