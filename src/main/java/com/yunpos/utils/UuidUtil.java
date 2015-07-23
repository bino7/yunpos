package com.yunpos.utils;

import java.util.UUID;
/**
 * 
 * 功能描述：UUID工具
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月24日
 * @author Devin_Yang 修改日期：2015年7月24日
 *
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}
