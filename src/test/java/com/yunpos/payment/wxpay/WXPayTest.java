package com.yunpos.payment.wxpay;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.yunpos.payment.wxpay.business.ScanPayBusiness;
import com.yunpos.payment.wxpay.business.ScanPayBusiness.ResultListener;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayReqData;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayResData;
import com.yunpos.payment.wxpay.protocol.pay_query_protocol.ScanPayQueryResData;
import com.yunpos.payment.wxpay.protocol.reverse_protocol.ReverseResData;
import com.yunpos.utils.DateUtil;

public class WXPayTest {

	@Test
	public void testRequestScanPayService() {
		String authCode = "130517936528673201"; // 条码
		String body = "test";
		String attach = "订单额外描述";
		String outTradeNo = "201508140005";
		int totalFee = 1; // 单位分
		String deviceInfo = "1000";
		String spBillCreateIP = "192.168.0.116";
		String timeStart = DateUtil.getNow();//yyyyMMddHHmmss
		String timeExpire = DateUtil.getDateAfter(timeStart, "yyyyMMddHHmmss", 1);
		String goodsTag = "刷卡支付";

		ScanPayReqData scanPayReqData = new ScanPayReqData(authCode, body, attach, outTradeNo, totalFee, deviceInfo,
				spBillCreateIP, timeStart, timeExpire, goodsTag);
		try {
			ScanPayBusiness scanPayBusiness = new ScanPayBusiness();
			
			ResultListener resultListener = new ResultListener() {
				public void onSuccess(ScanPayResData scanPayResData, String transactionID) {
					System.out.println(scanPayResData.toString());
					
				}
				
				public void onFailBySignInvalid(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByReverseSignInvalid(ReverseResData reverseResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByReturnCodeFail(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByReturnCodeError(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByQuerySignInvalid(ScanPayQueryResData scanPayQueryResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByMoneyNotEnough(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByAuthCodeInvalid(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFailByAuthCodeExpire(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
				
				public void onFail(ScanPayResData scanPayResData) {
					// TODO Auto-generated method stub
					
				}
			};
			scanPayBusiness.run(scanPayReqData, resultListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		fail("Not yet implemented");
	}

	@Test
	public void testRequestScanPayQueryService() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestRefundService() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestRefundQueryService() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestReverseService() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequestDownloadBillService() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoScanPayBusiness() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoRefundBusiness() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoRefundQueryBusiness() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoDownloadBillBusiness() {
		fail("Not yet implemented");
	}

}
