package com.yunpos.web.payment;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.yunpos.model.PayReq;
import com.yunpos.model.SysMerchant;
import com.yunpos.service.SysMerchantService;
import com.yunpos.utils.HttpTookit;
import com.yunpos.utils.MD5Utils;
import com.yunpos.web.BaseController;

/**
 * 
 * 功能描述：支付宝异步回调Controller
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
public class PayTestController extends BaseController{
	Logger log = LoggerFactory.getLogger(PayTestController.class);
	
	ObjectMapper mapper = new ObjectMapper(); 
	@Autowired
	private SysMerchantService sysMerchantService;
	 
	@RequestMapping(value = "/ajax/alipay/bar/test")
	@ResponseBody
	public String alipayBar(@Valid PayReq payReq) {
		Map<String,String> params = payReq.toMap();
		String returnStr = "";
		if(!Strings.isNullOrEmpty(payReq.getMerchant_num())){
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(payReq.getMerchant_num());
			String queryString = MD5Utils.sign(params, "MD5", sysMerchant.getMd5Key(), "UTF-8");
			log.info("签名结果："+queryString);
			returnStr = HttpTookit.doGet(payReq.getUrl(), queryString, "UTF-8", true);
			log.info("返回字符串="+returnStr);
		}
		return returnStr;
	}
	
	
	@RequestMapping(value = "/ajax/alipay/scan/test")
	@ResponseBody
	public String alipayScan(@Valid PayReq payReq) {
		Map<String,String> params = payReq.toMap();
		String returnStr = "";
		if(!Strings.isNullOrEmpty(payReq.getMerchant_num())){
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(payReq.getMerchant_num());
			String queryString = MD5Utils.sign(params, "MD5", sysMerchant.getMd5Key(), "UTF-8");
			log.info("签名结果："+queryString);
			returnStr = HttpTookit.doGet(payReq.getUrl(), queryString, "UTF-8", true);
			log.info("返回字符串="+returnStr);
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/ajax/wechat/bar/test")
	@ResponseBody
	public String wechatBar(@Valid PayReq payReq) {
		Map<String,String> params = payReq.toMap();
		String returnStr = "";
		if(!Strings.isNullOrEmpty(payReq.getMerchant_num())){
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(payReq.getMerchant_num());
			String queryString = MD5Utils.sign(params, "MD5", sysMerchant.getMd5Key(), "UTF-8");
			log.info("签名结果："+queryString);
			returnStr = HttpTookit.doGet(payReq.getUrl(), queryString, "UTF-8", true);
			log.info("返回字符串="+returnStr);
		}
		return returnStr;
	}
	
	
	@RequestMapping(value = "/ajax/wechat/scan/test")
	@ResponseBody
	public String wechatScan(@Valid PayReq payReq) {
		Map<String,String> params = payReq.toMap();
		String returnStr = "";
		if(!Strings.isNullOrEmpty(payReq.getMerchant_num())){
			SysMerchant sysMerchant = sysMerchantService.findBySerialNo(payReq.getMerchant_num());
			String queryString = MD5Utils.sign(params, "MD5", sysMerchant.getMd5Key(), "UTF-8");
			log.info("签名结果："+queryString);
			returnStr = HttpTookit.doGet(payReq.getUrl(), queryString, "UTF-8", true);
			log.info("返回字符串="+returnStr);
		}
		return returnStr;
	}
}
