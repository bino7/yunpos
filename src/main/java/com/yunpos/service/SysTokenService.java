package com.yunpos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysToken;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysTokenMapper;

@Service
public class SysTokenService extends EntityService<SysToken>{
	@Autowired
	private SysTokenMapper sysTokenMapper;
	
	@Override
	public EntityMapper<SysToken> getMapper() {
		return sysTokenMapper;
	}
	
	/**
	 * 产生Access_Token
	 * @param appid
	 * @param appSecret
	 * @return
	 */
	public SysToken createToken(String appid, String appSecret) {
		SysToken token = sysTokenMapper.findByappidAndappSecret(appid, appSecret);
		if (token != null) {
			// 设置accessToken,目前设为当前时间，后续修改为统一的产生规则
			long accessToken = System.currentTimeMillis() - 3600 * 1000L;
			token.setAccess_token(String.valueOf(accessToken));
			token.setExpires_time(12 * 60 * 60);// 有效期设置为12小时
			token.setUpdatedAt(new Date());
			update(token);
			//查询出最新数据
			token = sysTokenMapper.findByappidAndappSecret(appid, appSecret);
		}
		return token;
		
	}
	
	/**
	 * 通过Access_Token获取对应的SysToken
	 * @param accessToken
	 * @return
	 */
	public SysToken getTokenByAccessToken(String accessToken) {
		SysToken token = sysTokenMapper.findByAccessToken(accessToken);
		return token;
	}

}
