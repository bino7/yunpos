package com.yunpos.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.yunpos.model.Role;
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
    public Integer id;
    public List<Role> roles;
    public HashSet<String> permissions;
    

    public SecurityUser(Integer id,String username, String name) {
    	this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public HashSet<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(HashSet<String> permissions) {
		this.permissions = permissions;
	}

    
}
