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
		String str ="slqdu3mcpt194w77j9z1k5d2w6gerzo7";
		System.out.println(AESUtils.encrypt(AESUtils.key, str));
	}
}
