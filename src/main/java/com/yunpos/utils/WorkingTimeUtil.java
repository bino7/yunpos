package com.yunpos.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

import com.yunpos.security.SecurityUser;

public class WorkingTimeUtil {

	public static StringBuffer doTime(long startTime,String title) {
		PrincipalCollection principals= (PrincipalCollection) SecurityUtils.getSubject().getPrincipals();
		SecurityUser user = (SecurityUser) principals.getPrimaryPrincipal();
		StringBuffer actionInfo = new StringBuffer();
		actionInfo.append("支付请求");
		actionInfo.append("|").append(title);
		actionInfo.append("|").append("用户:"+user.getUsername());
		actionInfo.append("|").append("开始时间:"+DateUtil.format(startTime));
		long endTime = System.currentTimeMillis();
		actionInfo.append("|").append("结束时间:"+DateUtil.format(endTime));
		actionInfo.append("|").append("总时长:"+DateUtil.formatTimeConsumingInfo(startTime));
		return actionInfo;
	}
}
