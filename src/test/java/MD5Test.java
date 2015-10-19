import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import com.yunpos.payment.alipay.sign.MD5;
import com.yunpos.payment.alipay.util.Constant;
import com.yunpos.utils.MD5Utils;

public class MD5Test {
	private String text = "pay_channel=alipay&total_fee=0.01&merchant_num=201510160002&terminal_unique_no=1&cashier_num=1&client_type=PC&user_order_no=201509300111";
	//private String key = "9gq2tx2r0yk1z6s6z8iumzjvdp7at3c0mbyl";
	private String key = "a1h1iqlwkkx2yenfh2wzg3o15lqejya3uvuo";
	private String input_charset = "utf-8";
	@Test
	public void sign() throws UnknownHostException {
		Map<String,String> params = new HashedMap();
		params.put("merchant_num", "201509250001");
		params.put("terminal_unique_no", "1");
		params.put("cashier_num", "1");
		params.put("client_type", "PC");
		params.put("pay_channel", "wechat");
		//params.put("trace_num", "15099499712512");
		//params.put("refund_amount", "0.01");
		params.put("total_fee", "0.01");
		params.put("sign_type", "MD5");
//		params.put("dynamic_id", "130373695908066812");
		params.put("user_order_no", "201509300009");
		params.put("body", "微信支付商品测试");
		System.out.println(MD5Utils.sign(params, "MD5", key, input_charset));
		
		
		
		
//		Map<String,String> params = new HashedMap();
//		params.put("merchant_num", "201509250001");
//		params.put("terminal_unique_no", "1");
//		params.put("cashier_num", "1");
//		params.put("client_type", "PC");
//		params.put("pay_channel", "alipay");
//		//params.put("trace_num", "15099499712512");
//		//params.put("refund_amount", "0.01");
//		params.put("total_fee", "0.01");
//		//params.put("dynamic_id", "280025777883645590");
//		params.put("user_order_no", "201509250009");
//		System.out.println(MD5Utils.sign(params, "MD5", key, input_charset));
		
		//微信
//		Map<String,String> params = new HashedMap();
//		params.put("merchant_num", "201509020001");
//		params.put("terminal_unique_no", "1");
//		params.put("cashier_num", "1");
//		params.put("client_type", "PC");
//		params.put("pay_channel", "alipay");
//		//params.put("trace_num", "414508884997603328");
//		//params.put("refund_amount", "0.01");
//		params.put("total_fee", "0.01");
//		params.put("dynamic_id", "130182814228099253");
//		params.put("user_order_no", "201509250004");
//		System.out.println(MD5Utils.sign(params, "MD5", key, input_charset));
		
		
//		Map<String,String> params = new HashedMap();
//		params.put("merchant_num", "201509020001");
//		params.put("terminal_unique_no", "1");
//		params.put("cashier_num", "1");
//		params.put("client_type", "PC");
//		params.put("pay_channel", "alipay");
//		//params.put("trace_num", "414508884997603328");
//		//params.put("refund_amount", "0.01");
//		params.put("total_fee", "0.01");
//		//params.put("dynamic_id", "130182814228099253");
//		params.put("user_order_no", "201509250006");
//		System.out.println(MD5Utils.sign(params, "MD5", key, input_charset));
	}
	
	//微信
	
	@Test
	public void verify() {
//		MD5.verify(text, "MD5", key, input_charset);//
		String s = MD5Utils.sign(text, "MD5", key, input_charset);
		System.out.println(s);
		System.out.println(URLEncoder.encode("http://pay.o2o520.com/pay/wechatpaysl/wap/create?"+s));
	}
	
	@Test
	public void password() {
		//System.out.println(MD5Utils.genRandomNum(36));
		//System.out.println(Constant.getAlipayTradeStatus("TRADE_SUCCESS"));
		Float a = (float) 5.90;
		System.out.println(a.toString());
		
	}
	
	
	
	

	


}
