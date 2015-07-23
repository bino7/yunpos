package com.yunpos.security;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunpos.service.UserSessionService;

public class SecuritySessionDao extends CachingSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(SecuritySessionDao.class);

    @Autowired
    UserSessionService userSessionService;

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

        userSessionService.createUserSession(session);
        return sessionId;
    }

    @Override
    protected void doDelete(Session session) {
        userSessionService.deleteUserSession(session);
    }

    @Override
    protected void doUpdate(Session session) {
        userSessionService.updateUserSession(session);
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}
