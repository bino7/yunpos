package com.yunpos.service.payment;

import static java.lang.Thread.sleep;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.payment.alipay.util.Constant;
import com.yunpos.payment.wxpay.common.Configure;
import com.yunpos.payment.wxpay.common.HttpsRequest;
import com.yunpos.payment.wxpay.common.Signature;
import com.yunpos.payment.wxpay.common.Util;
import com.yunpos.payment.wxpay.common.report.ReporterFactory;
import com.yunpos.payment.wxpay.common.report.protocol.ReportReqData;
import com.yunpos.payment.wxpay.common.report.service.ReportService;
import com.yunpos.payment.wxpay.config.WechatPayConfig;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseReqData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseResData;
import com.yunpos.service.payment.Message.PayStatus;
import com.yunpos.utils.AmountUtils;
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
 *
 */
@Service
public class WechatPayService {
	private final static Logger log = LoggerFactory.getLogger(WechatPayService.class);
	public static final String FLAG_NAME = "isSuccess";
	public static final String MSG_NAME = "msg";
	//是否需要再调一次撤销，这个值由撤销API回包的recall字段决定
    private boolean needRecallReverse = false;
    private int waitingTimeBeforeReverseServiceInvoked = 5000;
//    
//	@Autowired
//	private SysPayOrderService sysPayOrderService;


	/**
	 * 微信支付同步方法业务逻辑处理
	 * 
	 * @param param
	 * @return
	 */
	public Message scanPay(PayParam param) throws Exception{
		// 把请求参数打包成数组
		String authCode = param.getBarCode();
		String body = param.getBody();
		String attach = param.getAttach();
		String outTradeNo = param.getOrderNo();
		int totalFee = Integer.valueOf(AmountUtils.changeY2F(param.getPrice()));
		String deviceInfo = param.getDeviceType();
		String spBillCreateIP = param.getSpBillCreateIP();
		String timeStart = param.getTimeStart();
		String timeExpire = param.getTimeExpire();
		String goodsTag = param.getGoodsTag();

		// 支付请求
		ScanPayReqData scanPayReqData = new ScanPayReqData(authCode, body, attach, outTradeNo, totalFee, deviceInfo,
				spBillCreateIP, timeStart, timeExpire, goodsTag);

		log.info("支付宝条码支付请求参数:" + scanPayReqData.toMap().toString());
		try {// 建立请求
			long costTimeStart = System.currentTimeMillis();
			HttpsRequest serviceRequest = new HttpsRequest();
			String payServiceResponseString =serviceRequest.sendPost(WechatPayConfig.PAY_API, scanPayReqData);
			long costTimeEnd = System.currentTimeMillis();
			long totalTimeCost = costTimeEnd - costTimeStart;
			log.info("api请求总耗时：" + totalTimeCost + "ms");
			log.info("同步返回结果：" + payServiceResponseString);

			// 将从API返回的XML数据映射到Java对象
			//ScanPayResData scanPayResData = (ScanPayResData) Util.getObjectFromXML(payServiceResponseString, ScanPayResData.class);
			Map<String,String> responseXml = XMLUtil.parse(payServiceResponseString);
			// 异步发送统计请求
			 ReportReqData reportReqData = new ReportReqData(
		                scanPayReqData.getDevice_info(),
		                Configure.PAY_API,
		                (int) (totalTimeCost),//本次请求耗时
		                responseXml.get("return_code"),
		                responseXml.get("return_msg"),
		                responseXml.get("result_code"),
		                responseXml.get("err_code"),
		                responseXml.get("err_code_des"),
		                responseXml.get("out_trade_no"),
		                responseXml.get("spbill_create_ip")
		        );
//			ReportReqData reportReqData = new ReportReqData(scanPayReqData.getDevice_info(), WechatPayConfig.PAY_API,
//					(int) (totalTimeCost), // 本次请求耗时
//					scanPayResData.getReturn_code(), scanPayResData.getReturn_msg(), scanPayResData.getResult_code(),
//					scanPayResData.getErr_code(), scanPayResData.getErr_code_des(), scanPayResData.getOut_trade_no(),
//					scanPayReqData.getSpbill_create_ip());
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
				return new Message(PayStatus.FAILD, "支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
			}

			if (responseXml.get("return_code").equals("FAIL")) {
				// 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
				log.error("【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
				// resultListener.onFailByReturnCodeFail(scanPayResData);
				return new Message(PayStatus.FAILD, "【支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
			} else {
				log.info("支付API系统成功返回数据");
				// 收到API的返回数据的时候得先验证一下数据有没有被第三方篡改，确保安全
				if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
					log.error("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
					// resultListener.onFailBySignInvalid(scanPayResData);
					return new Message(PayStatus.FAILD, "【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
				}

				if (responseXml.get("result_code").equals("SUCCESS")) {
					log.info("【一次性支付成功】");
					//resultListener.onSuccess(scanPayResData, transactionID);
					return new Message(PayStatus.REQUEST_SUCCESS, responseXml.get("transaction_id")); //微信支付订单号
				}else{//result_code FAIL
		            String errorCode = responseXml.get("err_code");
		            String errorCodeDes = responseXml.get("err_code_des");
		            log.error("业务返回失败,err_code"+errorCode+"|err_code_des:"+errorCodeDes);
		            
		            //对于扣款明确失败的情况直接走撤销逻辑
		            doReverseLoop(outTradeNo);
		            return new Message(PayStatus.FAILD, Constant.getWechatPayErrMsg(errorCode));
				}
			}
		} catch (Exception e) {
			log.error("微信支付异常:", e);
			return new Message(PayStatus.FAILD, e.getMessage());
		}
	}
	
	/**
	 * 撤单
	 * @param outTradeNo
	 * @param resultListener
	 * @return
	 * @throws Exception
	 */
	 private boolean doOneReverse(String outTradeNo) throws Exception {

	        sleep(waitingTimeBeforeReverseServiceInvoked);//等待一定时间再进行查询，避免状态还没来得及被更新

	        String reverseResponseString;

	        ReverseReqData reverseReqData = new ReverseReqData("",outTradeNo);
	        HttpsRequest serviceRequest = new HttpsRequest();
	        reverseResponseString = serviceRequest.sendPost(WechatPayConfig.REVERSE_API, reverseReqData);

	        log.info("撤销API返回的数据如下：");
	        log.info(reverseResponseString);
	        //将从API返回的XML数据映射到Java对象
	        ReverseResData reverseResData = (ReverseResData) Util.getObjectFromXML(reverseResponseString, ReverseResData.class);
	        if (reverseResData == null) {
	            log.info("支付订单撤销请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
	            return false;
	        }
	        if (reverseResData.getReturn_code().equals("FAIL")) {
	            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
	            log.info("支付订单撤销API系统返回失败，失败信息为：" + reverseResData.getReturn_msg());
	            return false;
	        } else {

	            if (!Signature.checkIsSignValidFromResponseString(reverseResponseString)) {
	                log.info("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
	               // resultListener.onFailByReverseSignInvalid(reverseResData);
	                needRecallReverse = false;//数据被窜改了，不需要再重试
	                return false;
	            }


	            if (reverseResData.getResult_code().equals("FAIL")) {
	                log.info("撤销出错，错误码：" + reverseResData.getErr_code() + "     错误信息：" + reverseResData.getErr_code_des());
	                if (reverseResData.getRecall().equals("Y")) {
	                    //表示需要重试
	                    needRecallReverse = true;
	                    return false;
	                } else {
	                    //表示不需要重试，也可以当作是撤销成功
	                    needRecallReverse = false;
	                    return true;
	                }
	            } else {
	                //查询成功，打印交易状态
	                log.info("支付订单撤销成功");
	                return true;
	            }
	        }
	    }


	    /**
	     * 由于有的时候是因为服务延时，所以需要商户每隔一段时间（建议5秒）后再进行查询操作，是否需要继续循环调用撤销API由撤销API回包里面的recall字段决定。
	     *
	     * @param outTradeNo    商户系统内部的订单号,32个字符内可包含字母, [确保在商户系统唯一]
	     * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
	     * @throws InterruptedException
	     */
	    private void doReverseLoop(String outTradeNo) throws Exception {
	        //初始化这个标记
	        needRecallReverse = true;
	        //进行循环撤销，直到撤销成功，或是API返回recall字段为"Y"
	        while (needRecallReverse) {
	            if (doOneReverse(outTradeNo)) {
	                return;
	            }
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

//	public Map<String, Object> notifyPayment(Map<String, String> params, boolean isSuccess, String resultMsg) {
//		Map<String, Object> returnMap = Maps.newHashMap();
//		String orderNo = params.get("out_trade_no");
//
//		if (Strings.isNullOrEmpty(orderNo)) {
//			String msg = "无法完成支付，支付接口的回调没有订单号信息！";
//			log.error(msg);
//			returnMap.put(FLAG_NAME, false);
//			returnMap.put(MSG_NAME, msg);
//			return returnMap;
//		}
//
//		SysPayOrder sysPayOrder = sysPayOrderService.findByPayOrderNo(orderNo);
//		if (sysPayOrder == null) {
//			String msg = "无法完成支付，支付接口返回的订单号[" + orderNo + "]无效！";
//			log.error(msg);
//			returnMap.put(FLAG_NAME, false);
//			returnMap.put(MSG_NAME, msg);
//			return returnMap;
//		}
//
//		if (sysPayOrder.getStatus() == Byte.valueOf("1")) {
//			String msg = "支付接口返回的订单流水[" + sysPayOrder + "]已经完成支付，无需再次处理！";
//			log.warn(msg);
//			returnMap.put(FLAG_NAME, true);
//			returnMap.put(MSG_NAME, msg);
//			return returnMap;
//		}
//
//		if (isSuccess) {
//			sysPayOrder.setStatus(Byte.valueOf("1"));// 支付成功
//			sysPayOrder.setNotify_time(new Date());
//
//			sysPayOrderService.update(sysPayOrder);
//
//			String msg = "订单[" + sysPayOrder.getPayOrderNo() + "]支付交易成功！";
//			log.info(msg);
//			returnMap.put(FLAG_NAME, true);
//			returnMap.put(MSG_NAME, msg);
//			return returnMap;
//		} else {
//			sysPayOrder.setStatus(Byte.valueOf("0"));// 支付失败
//			sysPayOrder.setNotify_time(new Date());
//			sysPayOrderService.update(sysPayOrder);
//
//			String msg = "支付接口返回的订单[" + sysPayOrder.getPayOrderNo() + "]支付失败！";
//			log.info(msg);
//			returnMap.put(FLAG_NAME, false);
//			returnMap.put(MSG_NAME, msg);
//			return returnMap;
//		}
//	}

}
