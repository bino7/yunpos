//package com.yunpos.payment.wxpay;
//
//import static org.junit.Assert.fail;
//
//import org.junit.Test;
//
//import com.yunpos.payment.wxpay.business.RefundBusiness;
//import com.yunpos.payment.wxpay.business.RefundBusiness.ResultListener;
//import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundReqData;
//import com.yunpos.payment.wxpay.protocol.refund_protocol.RefundResData;
//
//public class WXPayTest {
//
////	@Test
////	public void testRequestScanPayService() {
////		String authCode = "130517936528673201"; // 条码
////		String body = "test";
////		String attach = "订单额外描述";
////		String outTradeNo = "201508140005";
////		int totalFee = 1; // 单位分
////		String deviceInfo = "1000";
////		String spBillCreateIP = "192.168.0.116";
////		String timeStart = DateUtil.getNow();// yyyyMMddHHmmss
////		String timeExpire = DateUtil.getDateAfter(timeStart, "yyyyMMddHHmmss", 1);
////		String goodsTag = "刷卡支付";
////
////		ScanPayReqData scanPayReqData = new ScanPayReqData(authCode, body, attach, outTradeNo, totalFee, deviceInfo,
////				spBillCreateIP, timeStart, timeExpire, goodsTag);
////		try {
////			ScanPayBusiness scanPayBusiness = new ScanPayBusiness();
////
////			ResultListener resultListener = new ResultListener() {
////				public void onSuccess(ScanPayResData scanPayResData, String transactionID) {
////					System.out.println(scanPayResData.toString());
////
////				}
////
////				public void onFailBySignInvalid(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByReverseSignInvalid(ReverseResData reverseResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByReturnCodeFail(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByReturnCodeError(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByQuerySignInvalid(ScanPayQueryResData scanPayQueryResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByMoneyNotEnough(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByAuthCodeInvalid(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFailByAuthCodeExpire(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////
////				public void onFail(ScanPayResData scanPayResData) {
////					// TODO Auto-generated method stub
////
////				}
////			};
////			scanPayBusiness.run(scanPayReqData, resultListener);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		fail("Not yet implemented");
////	}
//
//	@Test
//	public void testRequestScanPayQueryService() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRequestRefundService() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
//
//		RefundBusiness refundBusiness = new RefundBusiness();
//		String transactionID = "1002120689201508180646107489"; // 微信订单号
//		String outTradeNo = "15099499675648"; // 系统订单号
//		String deviceInfo = "设备信息"; // 设备信息
//		String outRefundNo = "0000000001"; // 商户退款单号
//		int totalFee = 1;
//		int refundFee = 1;
//		String opUserID = "1";
//		String refundFeeType = "CNY";
//
//		RefundReqData refundReqData = new RefundReqData(transactionID, outTradeNo, deviceInfo, outRefundNo, totalFee,
//				refundFee, opUserID, refundFeeType);
//
//		com.yunpos.payment.wxpay.business.RefundBusiness.ResultListener resultListener  =new ResultListener() {
//			
//			@Override
//			public void onRefundSuccess(RefundResData refundResData) {
//				System.out.println("xxxx");
//				
//			}
//			
//			@Override
//			public void onRefundFail(RefundResData refundResData) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onFailBySignInvalid(RefundResData refundResData) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onFailByReturnCodeFail(RefundResData refundResData) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onFailByReturnCodeError(RefundResData refundResData) {
//				// TODO Auto-generated method stub
//			}
//			
//		};
//
//		try {
//			refundBusiness.run(refundReqData, resultListener);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void testRequestRefundQueryService() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRequestReverseService() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRequestDownloadBillService() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDoScanPayBusiness() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDoRefundBusiness() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDoRefundQueryBusiness() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDoDownloadBillBusiness() {
//		fail("Not yet implemented");
//	}
//
//}
