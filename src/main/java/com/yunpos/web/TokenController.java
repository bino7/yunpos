package com.yunpos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.service.KDT.KdtTokenService;

@RestController
public class TokenController {
	@Autowired
	private KdtTokenService kdtTokenService;
		
	/**
	 * 通过Code获取Token
	 * @param grant_type
	 * @param code
	 * @param redirect_uri
	 * @param serialNo  商户编号
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/kdtToken", method = RequestMethod.GET)
	public void getToken(String grant_type, String code, String redirect_uri,String serialNo) throws Exception {
		kdtTokenService.getToken(grant_type, code, redirect_uri, serialNo);
	}
	

}
