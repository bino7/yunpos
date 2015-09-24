import java.net.UnknownHostException;

import org.junit.Test;

import com.yunpos.payment.alipay.sign.MD5;
import com.yunpos.utils.MD5Utils;

public class MD5Test {
	private String text = "pay_channel=alipay&total_fee=0.02&merchant_num=1&terminal_unique_no=1&cashier_num=1&client_type=PC";
	private String key = "slqdu3mcpt194w77j9z1k5d2w6gerzo7";
	private String input_charset = "utf-8";
	@Test
	public void sign() throws UnknownHostException {
		System.out.println(MD5.sign(text, key, input_charset));//da3b955862b554757ddba123b85de471
	}
	
	@Test
	public void verify() {
		MD5.verify(text, "MD5", key, input_charset);//da3b955862b554757ddba123b85de471
	}
	
	@Test
	public void password() {
		System.out.println(MD5Utils.genRandomNum(36));
	}
	
	

	


}
