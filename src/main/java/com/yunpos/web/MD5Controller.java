package com.yunpos.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.utils.MD5Utils;

@RestController
public class MD5Controller {
	
	/**
	 * MD5加密服务
	 * @param username
	 * @param key
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/ajax/md5sign/username", method = RequestMethod.GET)
	public String md5SignbyUserName(@RequestParam("username") String username,@RequestParam("key") String key) {
		Map<String,String> params = new HashedMap();
		String randomNum = MD5Utils.genRandomNum(36);//随机数
		params.put("p", randomNum);
		params.put("username", username);
		String timestamp = String.valueOf(new Date().getTime());//时间撮
		params.put("t", timestamp);
		String token = MD5Utils.sign(params, "MD5", key, "utf-8");
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("token", token);
		result.put("p", randomNum);
		result.put("t", timestamp);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
