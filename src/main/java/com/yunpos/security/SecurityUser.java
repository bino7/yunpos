package com.yunpos.security;

import java.io.Serializable;
import java.util.Objects;
/**
 * 
 * 功能描述：认证用户信息扩展实体，实体所需字段可以再次基础上扩展
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月24日
 * @author Devin_Yang 修改日期：2015年7月24日
 *
 */
public class SecurityUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
    public String name;

    public SecurityUser(String username, String name) {
        this.username = username;
        this.name = name;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SecurityUser other = (SecurityUser) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
}
