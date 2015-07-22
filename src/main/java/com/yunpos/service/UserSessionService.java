package com.yunpos.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunpos.model.UserSession;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.UserSessionMapper;
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
public class UserSessionService extends EntityService<UserSession> {
	@Autowired
	private UserSessionMapper userSessionMapper;

	@Override
	public EntityMapper<UserSession> getMapper() {
		return userSessionMapper;
	}

	public Session findBySessionId(Serializable sessionId) {
		UserSession userSession = userSessionMapper
				.findBySessionId(sessionId);
		return coverUserSessionToSession(userSession);
	}

	public void createUserSession(Session session) {
		UserSession userSession = coverSessionToUserSession(session);
		if (userSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				userSession.setUsername(username);
			}
			userSessionMapper.insert(userSession);
		}
	}

	public void deleteUserSession(Session session) {
		UserSession userSession = userSessionMapper.findBySessionId(String
				.valueOf(session.getId()));
		if (userSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				userSession.setUsername(username);
			}
			userSession.setEndTime(new Date());
			userSession.setSessionId(userSession.getSessionId());
			userSessionMapper.update(userSession);
		}
	}

	public void updateUserSession(Session session) {
		UserSession userSession = userSessionMapper.findBySessionId(String
				.valueOf(session.getId()));
		if (userSession != null) {
			if (session
					.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key) != null) {
				String username = (String) session
						.getAttribute(CaptchaFormAuthenticationFilter.Shiro_Session_Username_Key);
				userSession.setUsername(username);
			}
			userSession.setLastAccessTime(new Date());
			userSession.setSessionId(userSession.getSessionId());
			userSessionMapper.update(userSession);
		}
	}

	private UserSession coverSessionToUserSession(Session session) {
		UserSession userSession = new UserSession();
		userSession.setSessionId(String.valueOf(session.getId()));
		userSession.setLastAccessTime(session.getLastAccessTime());
		userSession.setHost(session.getHost());
		userSession.setStartTime(session.getStartTimestamp());
		return userSession;
	}

	private Session coverUserSessionToSession(UserSession userSession) {
		SimpleSession session = new SimpleSession();
		session.setId(userSession.getId());
		session.setLastAccessTime(userSession.getLastAccessTime());
		session.setHost(userSession.getHost());
		session.setStartTimestamp(userSession.getStartTime());
		return session;
	}
}
