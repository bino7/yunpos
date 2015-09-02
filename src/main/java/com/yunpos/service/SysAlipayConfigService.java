package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysAlipayConfig;
import com.yunpos.model.SysAlipayConfigWithBLOBs;
import com.yunpos.persistence.dao.SysAlipayConfigMapper;

@Service
public class SysAlipayConfigService{
	@Autowired
	private SysAlipayConfigMapper sysAlipayConfigMapper;

	public SysAlipayConfigWithBLOBs findById(int id) {
		return sysAlipayConfigMapper.selectByPrimaryKey(id);
	}

	public void save(SysAlipayConfigWithBLOBs sysalipayconfig) {
		sysAlipayConfigMapper.insertSelective(sysalipayconfig);
		
	}

	public void update(SysAlipayConfigWithBLOBs sysalipayconfig) {
		sysAlipayConfigMapper.updateByPrimaryKey(sysalipayconfig);
		
	}

	public void delete(int id) {
		sysAlipayConfigMapper.deleteByPrimaryKey(id);
	}

	public SysAlipayConfigWithBLOBs findByPid(String pid) {
		return sysAlipayConfigMapper.findByPid(pid);
	}

	public SysAlipayConfigWithBLOBs findByMerchantNo(String merchantNo) {
		return sysAlipayConfigMapper.findByMerchantNo(merchantNo);
	}



	


}
