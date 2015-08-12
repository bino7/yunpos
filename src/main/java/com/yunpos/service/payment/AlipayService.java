package com.yunpos.service.payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yunpos.model.SalesOrder;
import com.yunpos.payment.alipay.config.AlipayConfig;
import com.yunpos.payment.alipay.util.AlipaySubmit;
import com.yunpos.payment.alipay.util.Constant;
import com.yunpos.service.payment.Message.PayStatus;
import com.yunpos.utils.XMLUtil;

/**
 * 
 * 功能描述：支付宝支付业务类
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
public class AlipayService {
	private final static Logger log = LoggerFactory.getLogger(AlipayService.class);
	
	/**
	 * 支付宝支付同步方法业务逻辑处理
	 * @param param
	 * @return
	 */
	public Message pay(PayParam param) {
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put(PayConst.SERVICE, AlipayConfig.service);
        sParaTemp.put(PayConst.PARTNER, AlipayConfig.partner);
        sParaTemp.put(PayConst._INPUT_CHARSET, AlipayConfig.input_charset);
        sParaTemp.put(PayConst.SIGN_TYPE, AlipayConfig.sign_type);
        sParaTemp.put(PayConst.NOTIFY_URL, AlipayConfig.notify_url);
        sParaTemp.put(PayConst.OUT_TRADE_NO, param.getOrderNo());
        sParaTemp.put(PayConst.SUBJECT, param.getOrderTitle());
        sParaTemp.put(PayConst.PRODUCT_CODE, param.getBarCode());
        sParaTemp.put(PayConst.IT_B_PAY, AlipayConfig.pay_time_out);
		
		try {//建立请求
			String responseXml = AlipaySubmit.buildRequest("", "", sParaTemp);
			Map<String,String> result = new HashMap<String, String>();
			XMLUtil.parse(responseXml, result);
			if("T".equalsIgnoreCase(result.get("is_success"))){//T代表成功
				return new Message(PayStatus.REQUEST_SUCCESS,result.get("trade_no")); //支付宝交易流水号
			}else{
				String errorCode = result.get("error");
				return new Message(PayStatus.FAILD,Constant.getAlipayErrMsg(errorCode));
			}
		} catch (Exception e) {
			log.error("支付宝支会异常:",e);
			return new Message(PayStatus.FAILD,e.getMessage());
		}
	}
	
	
	
	/**
	 * 支付宝支付异步回调业务逻辑处理
	 * @param order
	 * @param isSuccess
	 * @param paymentDate
	 * @param resultMsg
	 * @return
	 */
	public Map<String, Object> notifyPayment(SalesOrder order,
			boolean isSuccess, Date paymentDate, String resultMsg) {
		return null;
		
	}
	
	public Map<String,Object> notifyPayment(String orderNo, boolean isSuccess, 
			Date paymentDate, String resultMsg) {
		return null;
	}

}
