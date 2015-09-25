package com.yunpos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMemberpay;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMemberpayMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMemberpayService extends EntityService<SysMemberpay> {
	@Autowired
	private SysMemberpayMapper sysMemberpayMapper;

	@Override
	public EntityMapper<SysMemberpay> getMapper() {
		return sysMemberpayMapper;
	}
	

	public List<SysMemberpay> findAll() {
		return sysMemberpayMapper.findAll();
	}

	public GridResponse<SysMemberpay> search(GridRequest gridRequest) {
		GridResponse<SysMemberpay> response = new GridResponse<SysMemberpay>();
		List<SysMemberpay> sysApps =  sysMemberpayMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}
	
	/**
	 * 获取一个粉丝OpenId的充值记录列表
	 * @param openId
	 * @return
	 */
	public GridResponse<SysMemberpay> findByUserId(String openId) {
		GridResponse<SysMemberpay> response = new GridResponse<SysMemberpay>();
		List<SysMemberpay> sysApps =  sysMemberpayMapper.findByuserId(openId);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}
	

	/**
	 * 根据会员卡参数查询
	 * @param SysMemberpay
	 * @return
	 */
	public List<SysMemberpay> findByParms(SysMemberpay sysMemberpay) {
		List<SysMemberpay> list = sysMemberpayMapper.selectByParm(sysMemberpay);
		return list;
	}
	
	public SysMemberpay getMemberpayByorderId(String source, String payorderId) {
		return sysMemberpayMapper.findByorderId(source, payorderId);
	}
	
	public List<SysMemberpay> getMemberpayByuserIdAndTime(String appid_userId, Date startTime, Date endTime) {
		return sysMemberpayMapper.findByuserIdAndTime(appid_userId, startTime, endTime);
	}
	
}