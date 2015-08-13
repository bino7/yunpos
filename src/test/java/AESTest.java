import org.junit.Test;

import com.yunpos.utils.AESUtils;


public class AESTest {

	@Test
	public void decrypt(){
		String str = "917w3SrnwK0GP0+PbPw5RTqVhQDaGJbBZcX74PSUtS8=";
		System.out.println(AESUtils.decrypt(AESUtils.key, str));
	}
	
	@Test
	public void encrypt(){
		String str ="MD5";
//		System.out.println(AESUtils.encrypt(AESUtils.key, str));
		System.out.println(Double.valueOf("0.01").longValue());
	}
}
