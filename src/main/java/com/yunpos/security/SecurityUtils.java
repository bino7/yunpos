package com.yunpos.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yunpos.utils.Digests;
import com.yunpos.utils.Encodes;

/**
 * 
 * 功能描述：Shiro操作工具类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月21日
 * @author Devin_Yang 修改日期：2015年7月21日
 *
 */
public class SecurityUtils {
    public final static String HASH_ALGORITHM = "SHA-1";
    public final static int HASH_INTERATIONS = 1024;
    private final static int SALT_SIZE = 8;

    @SuppressWarnings("unused")
	public static String getUsername() {
        Subject subject = getSubject();
        return null;
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static String getSessionId() {
        Session session = getSession();
        if (null == session) {
            return null;
        }
        return getSession().getId().toString();
    }

    public static boolean isAuthenticated() {
        return getSubject().isAuthenticated();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 对传入的明文密码进行加密
     */
    public static String entryptPassword(String plainPassword, String salt) {
        byte[] saltByte = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), saltByte, HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    /**
     * 生成一个随机的Salt
     */
    public static String generateSalt() {
        return Encodes.encodeHex(Digests.generateSalt(SALT_SIZE));
    }
}
