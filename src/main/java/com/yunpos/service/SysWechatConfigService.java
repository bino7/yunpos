package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.persistence.dao.SysWechatConfigMapper;

@Service
public class SysWechatConfigService{
	@Autowired
	private SysWechatConfigMapper sysWechatConfigMapper;

	public SysWechatConfigWithBLOBs findById(int id) {
		return sysWechatConfigMapper.selectByPrimaryKey(id);
	}

	public void save(SysWechatConfigWithBLOBs sysWechatConfig) {
		sysWechatConfigMapper.insert(sysWechatConfig);
		
	}

	public void update(SysWechatConfigWithBLOBs sysWechatConfig) {
		sysWechatConfigMapper.updateByPrimaryKeySelective(sysWechatConfig);
		
	}

	public void delete(int id) {
		sysWechatConfigMapper.deleteByPrimaryKey(id);
		
	}

	public SysWechatConfigWithBLOBs findByMchId(String mchId) {
		return sysWechatConfigMapper.findByMchId(mchId);
	}


	public SysWechatConfigWithBLOBs findByMerchantNo(String merchantNo) {
		return sysWechatConfigMapper.findByMerchantNo(merchantNo);
	}

}
