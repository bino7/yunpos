package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.yunpos.model.SysFans;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysFansMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

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
	
	
	public List<SysFans> findAll() {
		return sysFansMapper.findAll();
	}
	

	public GridResponse<SysFans> search(GridRequest gridRequest) {
		GridResponse<SysFans> response = new GridResponse<SysFans>();
		List<SysFans> sysFans =  sysFansMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysFans);
		response.setTotalRowCount(sysFans.size());
		return response;
	}
	
	public GridResponse<SysFans> searchByMerchant(String serialNo) {
		GridResponse<SysFans> response = new GridResponse<SysFans>();
		List<SysFans> sysFans =  sysFansMapper.findByMerchant(serialNo);//getSearchValue()值为商户编号
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysFans);
		response.setTotalRowCount(sysFans.size());
		return response;
	}
	
	/**
	 * 增加粉丝的可用余额
	 * @param id
	 * @param balance
	 */
	public boolean updateBalance(@PathVariable("id") int id, float balance){ 
		SysFans sysFan = findById(id);
		if (sysFan != null) {
			float soucebalance = sysFan.getBalance() != null ? sysFan.getBalance() : 0;
			sysFan.setBalance(soucebalance + balance);
			update(sysFan);
			return true;
		} else {
			return false;
		}	
	}
}
