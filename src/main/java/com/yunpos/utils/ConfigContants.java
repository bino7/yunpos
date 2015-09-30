package com.yunpos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunpos.web.LoginController;

/**
 * 
 * 功能描述：系统常量配置
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月24日
 * @author Devin_Yang 修改日期：2015年7月24日
 *
 */
public class ConfigContants {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private static Properties prop = null;

	public static String SHOWCOUNT = "15"; // 分页常量
	
	public static String IMAGEPATH = ""; // 图片路径
	
	public static String IMAGEURL = "";  // 图片路径

	static {
		InputStream is = ConfigContants.class.getClassLoader()
				.getResourceAsStream("configContants.properties");
		try {
			prop = new Properties();
			prop.load(is);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		SHOWCOUNT = prop.getProperty("page.showCount");
		IMAGEPATH = prop.getProperty("imagePath");
		IMAGEURL  = prop.getProperty("imageURL");
	}

}
