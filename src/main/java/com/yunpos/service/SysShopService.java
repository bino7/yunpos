package com.yunpos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysShop;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysShopMapper;

@Service
public class SysShopService extends EntityService<SysShop> {
	@Autowired
	private SysShopMapper sysShopMapper;

	@Override
	public EntityMapper<SysShop> getMapper() {
		return sysShopMapper;
	}
	
	public void updateByOid(SysShop entity) {
		SysShop result = sysShopMapper.findByOid(entity.getSerialNo(), entity.getOid());
		if (result == null) {
			save(entity);
		}else {
			result.setAccessToken(entity.getAccessToken());//更新新的AccessToken
			result.setRefreshToken(entity.getRefreshToken());//更新新的RefreshToken
			result.setUpdatedAt(new Date());
			update(result);
		}	
	}

	public List<SysShop> findAll(){
		return sysShopMapper.findAll();
	}
	
	public List<SysShop> findAllByToken(){
		return sysShopMapper.findAll();
	}
	
	public SysShop findByRefreshtoken(String refresh_token) {
		return sysShopMapper.findByRefreshtoken(refresh_token);	
	}

}
