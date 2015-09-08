package com.yunpos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysFans;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysFansMapper;

@Service
public class SysFansService extends EntityService<SysFans> {
	@Autowired
	private SysFansMapper sysFansMapper;

	@Override
	public EntityMapper<SysFans> getMapper() {
		return sysFansMapper;
	}
	
	public void updateByOpenIdorUserId(SysFans entity) {
		SysFans result = sysFansMapper.findByOpenIdorUserId(entity.getOpenId(),entity.getAppid_userId());
		if (result == null) {
			save(entity);
		}else {
			result.setSubscribeFlag(Byte.valueOf("1"));//关注标识设置为已关注
			result.setSubscribeTime(entity.getSubscribeTime());//关注时间
			update(result);
		}
	}
	
	/**
	 * 通过外部系统ID获取粉丝
	 * @param oid
	 * @return
	 */
	public SysFans findByOid(String oid) {
		return sysFansMapper.findByOid(oid);		
	}

}
