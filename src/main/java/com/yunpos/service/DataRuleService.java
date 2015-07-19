package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.DataRule;
import com.yunpos.persistence.dao.DataRuleMapper;

@Service
public class DataRuleService {

	@Autowired
	public DataRuleMapper dataRuleMapper;

	public DataRule findById(int id) {
		return dataRuleMapper.selectByPrimaryKey(id);
	}

	public int insertUsers(DataRule record) {
		return dataRuleMapper.insert(record);
	}

	public int delUsers(int uid) {
		return dataRuleMapper.deleteByPrimaryKey(uid);
	}

	public int updateUsers(DataRule record) {
		return dataRuleMapper.updateByPrimaryKey(record);
	}

	public DataRule findByRoleID(Integer roleid) {
		return dataRuleMapper.selectByRoleID(roleid);
	}

	//查询用户数据权限
	public DataRule findByUserID(Integer userid, String datatype) {
		return dataRuleMapper.selectByUserID(userid, datatype);
	}

	public boolean exists(int id) {
		DataRule dataRule = dataRuleMapper.selectByPrimaryKey(id);
		if (dataRule != null) {
			return true;
		}
		return false;
	}

}
