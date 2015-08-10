package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysDataRule;
import com.yunpos.persistence.dao.SysDataRuleMapper;

@Service
public class SysDataRuleService {

	@Autowired
	public SysDataRuleMapper sysDataRuleMapper;

	public SysDataRule findById(int id) {
		return sysDataRuleMapper.selectByPrimaryKey(id);
	}

	public int insertUsers(SysDataRule record) {
		return sysDataRuleMapper.insert(record);
	}

	public int delUsers(int uid) {
		return sysDataRuleMapper.deleteByPrimaryKey(uid);
	}

	public int updateUsers(SysDataRule record) {
		return sysDataRuleMapper.updateByPrimaryKey(record);
	}

	public SysDataRule findByRoleID(Integer roleid) {
		return sysDataRuleMapper.selectByRoleID(roleid);
	}

	//查询用户数据权限
	public SysDataRule findByUserID(Integer userid, String datatype) {
		return sysDataRuleMapper.selectByUserID(userid, datatype);
	}

	public boolean exists(int id) {
		SysDataRule dataRule = sysDataRuleMapper.selectByPrimaryKey(id);
		if (dataRule != null) {
			return true;
		}
		return false;
	}

}
