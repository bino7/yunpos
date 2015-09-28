package com.yunpos.payment.wxpay.protocol.pay_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.payment.wxpay.common.RandomStringGenerator;
import com.yunpos.payment.wxpay.common.Signature;
import com.yunpos.payment.wxpay.config.WechatPayConfig;

/**
 * 请求被扫支付API需要提交的数据
 */
public class ScanPayReqData {

    //每个字段具体的意思请查看API文档
    private String appid = "";
    private String mch_id = "";
    private String device_info = "";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";
    private String attach = "";
    private String out_trade_no = "";
    private int total_fee = 0;
    private String spbill_create_ip = "";
//    private String time_start = "";
//    private String time_expire = "";
    private String goods_tag = "";
    private String auth_code = "";
    private String sdk_version;
    
    
	// 业务附加传递信息（非接口参数）
//	private String pay_channel = "";
//	private String terminal_unique_no = "";
//	private String merchant_num = "";
//	private String merchant_name = "";

    /**
     * @param authCode 这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @param body 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @param attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
     * @param totalFee 订单总金额，单位为“分”，只能整数
     * @param deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
     * @param spBillCreateIP 订单生成的机器IP
     * @param timeStart 订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     * @param timeExpire 订单失效时间，格式同上
     * @param goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
     */
    public ScanPayReqData(String authCode,String body,String attach,String outTradeNo,int totalFee,String deviceInfo,String spBillCreateIP,String goodsTag,SysWechatConfigWithBLOBs sysWechatConfig){

        setSdk_version(WechatPayConfig.sdkVersion);

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(sysWechatConfig.getAppId());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(sysWechatConfig.getMchId());

        //这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
        //调试的时候可以在微信上打开“钱包”里面的“刷卡”，将扫码页面里的那一串14位的数字输入到这里来，进行提交验证
        //记住out_trade_no这个订单号可以将这一笔支付进行退款
        setAuth_code(authCode);

        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);

        //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
        setAttach(attach);

        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);

        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);

        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);

        //订单生成的机器IP
        setSpbill_create_ip(spBillCreateIP);

        //订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
        //setTime_start(DateUtil.getNow("yyyyMMddHHmmss"));

        //订单失效时间，格式同上
        //setTime_expire(DateUtil.getDateAfter(DateUtil.getNow("yyyyMMddHHmmss"), "yyyyMMddHHmmss", 1));

        //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        setGoods_tag(goodsTag);

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap(),sysWechatConfig.getAppKey());
        setSign(sign);//把签名数据设置到Sign这个属性中
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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

//    public String getTime_start() {
//        return time_start;
//    }
//
//    public void setTime_start(String time_start) {
//        this.time_start = time_start;
//    }

//    public String getTime_expire() {
//        return time_expire;
//    }
//
//    public void setTime_expire(String time_expire) {
//        this.time_expire = time_expire;
//    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getSdk_version(){
        return sdk_version;
    }

    public void setSdk_version(String sdk_version) {
        this.sdk_version = sdk_version;
    }
    
    

//    public String getPay_channel() {
//		return pay_channel;
//	}
//
//
//
//	public void setPay_channel(String pay_channel) {
//		this.pay_channel = pay_channel;
//	}
//
//
//
//	public String getTerminal_unique_no() {
//		return terminal_unique_no;
//	}
//
//
//
//	public void setTerminal_unique_no(String terminal_unique_no) {
//		this.terminal_unique_no = terminal_unique_no;
//	}
//
//
//
//	public String getMerchant_num() {
//		return merchant_num;
//	}
//
//
//
//	public void setMerchant_num(String merchant_num) {
//		this.merchant_num = merchant_num;
//	}
//
//
//
//	public String getMerchant_name() {
//		return merchant_name;
//	}
//
//
//
//	public void setMerchant_name(String merchant_name) {
//		this.merchant_name = merchant_name;
//	}



	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
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
    
    public Map<String,String> toStringMap(){
        Map<String,String> map = new HashMap<String, String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
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
