package com.yunpos.payment.wxpay.protocol.close_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.payment.wxpay.common.RandomStringGenerator;
import com.yunpos.payment.wxpay.common.Signature;

/**
 * 
 * 功能描述：微信扫码统一下单请求数据封装
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月27日
 * @author Devin_Yang 修改日期：2015年8月27日
 *
 */
public class CloseOrderReqData {
	// 每个字段具体的意思请查看API文档
	// https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	private String appid = "";
	private String mch_id = "";
	private String out_trade_no = "";
	private String nonce_str = "";
	private String sign = "";
    private String sub_appid = "";//子商户公众账号ID
    private String sub_mch_id=""; //子商户号

	//普通商户
	public CloseOrderReqData(String mch_id, String out_trade_no,SysWechatConfigWithBLOBs sysWechatConfig) {
		// ****************必填选项***************************
		// 微信分配的公众号ID（开通公众号之后可以获取到）
		setAppid(sysWechatConfig.getAppId());
		// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
		setMch_id(sysWechatConfig.getMchId());
		// 随机字符串，不长于32 位
		setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		// 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
		setOut_trade_no(out_trade_no); // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
		String sign = Signature.getSign(toMap(),sysWechatConfig.getApiSecret());
		setSign(sign);// 把签名数据设置到Sign这个属性中

	}
	//服务商
	public CloseOrderReqData(String mch_id, String out_trade_no,SysWechatConfigWithBLOBs sysWechatConfigPar,SysWechatConfigWithBLOBs sysWechatConfigSub) {
		// ****************必填选项***************************
		//微信分配的公众号ID（开通公众号之后可以获取到）
		setAppid(sysWechatConfigPar.getAppId());
		//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
		setMch_id(sysWechatConfigPar.getMchId());
		//微信分配的公众号ID（开通公众号之后可以获取到）
		setSub_appid(sysWechatConfigSub.getAppId());
		//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
		setSub_mch_id(sysWechatConfigSub.getMchId());
		// 随机字符串，不长于32 位
		setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		// 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
		setOut_trade_no(out_trade_no); // 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
		String sign = Signature.getSign(toMap(),sysWechatConfigPar.getApiSecret());
		setSign(sign);// 把签名数据设置到Sign这个属性中
		
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSub_appid() {
		return sub_appid;
	}
	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}
	public String getSub_mch_id() {
		return sub_mch_id;
	}
	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null && !obj.equals("")) {
					map.put(field.getName(), obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public Map<String, String> toStringMap() {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
					if (field.getType() == int.class) {
						map.put(field.getName(), String.valueOf(obj));
					} else {
						map.put(field.getName(), (String) obj);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
