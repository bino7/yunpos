package com.yunpos.security;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunpos.service.SysUserSessionService;

public class SecuritySessionDao extends CachingSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(SecuritySessionDao.class);

    @Autowired
    SysUserSessionService sysUserSessionService;

    @Override
    protected Serializable doCreate(Session session) {
        logger.debug("......");
        logger.debug("......");
        logger.debug("......");
        logger.debug("......");
        logger.debug("......");
        logger.debug("......");
        logger.debug("......");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        sysUserSessionService.createUserSession(session);
        return sessionId;
    }

    @Override
    protected void doDelete(Session session) {
    	sysUserSessionService.deleteUserSession(session);
    }

    @Override
    protected void doUpdate(Session session) {
    	sysUserSessionService.updateUserSession(session);
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}
