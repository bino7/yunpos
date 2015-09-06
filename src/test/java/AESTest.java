import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yunpos.payment.wxpay.common.Util;
import com.yunpos.payment.wxpay.protocol.pay_protocol.ScanPayResData;
import com.yunpos.utils.AESUtils;
import com.yunpos.utils.Message.ResultCode;

public class AESTest {

	@Test
	public void decrypt() {
		String str = "R4EOAXfmd1pNy2dQOp8q6T07fX/kmOxpN3f+RON/kFvYIBjisOZXNRz0Sg58cUm+";
		String str2 = "YUvmw8H1ud3WPbPkFKXltw==";
	
		
		System.out.println(AESUtils.decrypt(str2));
	}

	@Test
	public void encrypt() {
		//String str = "192.168.0.116";
		// System.out.println(AESUtils.encrypt(AESUtils.key, str));
		//System.out.println(Integer.valueOf(String.valueOf((Float.parseFloat("0.01")) * 100)));
		// System.out.println(Byte.valueOf("2") ==Byte.valueOf("1"));
	}

	@Test
	public void format() {
//		String str = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx9a930755fdc2698d]]></appid>"
//				+ "<mch_id><![CDATA[1241267602]]></mch_id>" + "<device_info><![CDATA[1]]></device_info>"
//				+ "<nonce_str><![CDATA[gXfptWrtmNRwD83Y]]></nonce_str>"
//				+ "<sign><![CDATA[C4CF45BA1B77FD878045324E7BEF1FA2]]></sign>"
//				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
//				+ "<openid><![CDATA[ox6wPuHd5Y4jlycYaSqbz842I1jU]]></openid>"
//				+ "<is_subscribe><![CDATA[Y]]></is_subscribe>" + "<trade_type><![CDATA[MICROPAY]]></trade_type>"
//				+ "<bank_type><![CDATA[CCB_DEBIT]]></bank_type>" + "<total_fee>1</total_fee>"
//				+ "<fee_type><![CDATA[CNY]]></fee_type>"
//				+ "<transaction_id><![CDATA[1002120689201508170642621488]]></transaction_id>"
//				+ "<out_trade_no><![CDATA[15099502972928]]></out_trade_no>"
//				+ "<attach><![CDATA[微信条码支付订单附加数据]]></attach>" + "<time_end><![CDATA[20150817184643]]></time_end>"
//				+ "<cash_fee>1</cash_fee></xml>";
//		// System.out.println(AESUtils.encrypt(AESUtils.key, str));
//		ScanPayResData scanPayResData = (ScanPayResData) Util.getObjectFromXML(str, ScanPayResData.class);
		System.out.println(Float.valueOf("0.02"));
		// System.out.println(Byte.valueOf("2") ==Byte.valueOf("1"));
	}
	
	


}
