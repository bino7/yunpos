package com.yunpos.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 继承UsernamePasswordToken，增加验证码
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    // 验证码
    private String captcha;

    public CaptchaUsernamePasswordToken() {
        super();
    }

    public CaptchaUsernamePasswordToken(String username, String password) {
        super(username, password);
    }

    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);

        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


}
