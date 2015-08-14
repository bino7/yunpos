package com.yunpos.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunpos.model.SysUserSession;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysUserSessionMapper;
import com.yunpos.security.CaptchaFormAuthenticationFilter;

/**
 * 
 * 功能描述：用户session service
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月22日
 * @author Devin_Yang 修改日期：2015年7月22日
 *
 */

@Service
@Transactional
public class SysUserSessionService extends EntityService<SysUserSession> {
	@Autowired
	private SysUserSessionMapper sysUserSessionMapper;

	@Override
	public EntityMapper<SysUserSession> getMapper() {
		return sysUserSessionMapper;
	}

	public Session findBySessionId(Serializable sessionId) {
		SysUserSession sysUserSession = sysUserSessionMapper
				.findBySessionId(sessionId);
		return coverUserSessionToSession(sysUserSession);
	}

	public void createUserSession(Session session) {
		SysUserSession sysUserSession = coverSessionToUserSession(session);
		if (sysUserSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				sysUserSession.setUserName(username);
			}
			sysUserSessionMapper.insert(sysUserSession);
		}
	}

	public void deleteUserSession(Session session) {
		SysUserSession sysUserSession = sysUserSessionMapper.findBySessionId(String
				.valueOf(session.getId()));
		if (sysUserSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				sysUserSession.setUserName(username);
			}
			sysUserSession.setSessionId(sysUserSession.getSessionId());
			sysUserSessionMapper.update(sysUserSession);
		}
	}

	public void updateUserSession(Session session) {
		SysUserSession sysUserSession = sysUserSessionMapper.findBySessionId(String
				.valueOf(session.getId()));
		if (sysUserSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				sysUserSession.setUserName(username);
			}
			sysUserSession.setLastAccessTime(new Date());
			sysUserSession.setSessionId(sysUserSession.getSessionId());
			sysUserSessionMapper.update(sysUserSession);
		}
	}

	private SysUserSession coverSessionToUserSession(Session session) {
		SysUserSession sysUserSession = new SysUserSession();
		sysUserSession.setSessionId(String.valueOf(session.getId()));
		sysUserSession.setLastAccessTime(session.getLastAccessTime());
		sysUserSession.setHost(session.getHost());
		sysUserSession.setStartTime(session.getStartTimestamp());
		return sysUserSession;
	} 

	private Session coverUserSessionToSession(SysUserSession userSession) {
		SimpleSession session = new SimpleSession();
		session.setId(userSession.getId());
		session.setLastAccessTime(userSession.getLastAccessTime());
		session.setHost(userSession.getHost());
		session.setStartTimestamp(userSession.getStartTime());
		return session;
	}
}
