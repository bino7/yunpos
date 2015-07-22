package com.yunpos.model;

import java.util.Date;

/**
 * 
 * 功能描述：用户session实体
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月22日
 * @author Devin_Yang 修改日期：2015年7月22日
 *
 */
public class UserSession {
	 private Integer id;

	    private String sessionId;

	    private String username;

	    private String host;

	    private Date lastAccessTime;

	    private Date startTime;

	    private Date endTime;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getSessionId() {
	        return sessionId;
	    }

	    public void setSessionId(String sessionId) {
	        this.sessionId = sessionId == null ? null : sessionId.trim();
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }

	    public String getHost() {
	        return host;
	    }

	    public void setHost(String host) {
	        this.host = host == null ? null : host.trim();
	    }

	    public Date getLastAccessTime() {
	        return lastAccessTime;
	    }

	    public void setLastAccessTime(Date lastAccessTime) {
	        this.lastAccessTime = lastAccessTime;
	    }

	    public Date getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(Date startTime) {
	        this.startTime = startTime;
	    }

	    public Date getEndTime() {
	        return endTime;
	    }

	    public void setEndTime(Date endTime) {
	        this.endTime = endTime;
	    }

}
