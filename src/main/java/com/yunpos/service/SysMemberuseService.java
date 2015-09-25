package com.yunpos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMemberuse;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMemberuseMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMemberuseService extends EntityService<SysMemberuse>  {
	@Autowired
	private SysMemberuseMapper sysMemberuseMapper;

	@Override
	public EntityMapper<SysMemberuse> getMapper() {
		return sysMemberuseMapper;
	}
   

	public List<SysMemberuse> findAll() {
		return sysMemberuseMapper.findAll();
	}

	public GridResponse<SysMemberuse> search(GridRequest gridRequest) {
		GridResponse<SysMemberuse> response = new GridResponse<SysMemberuse>();
		List<SysMemberuse> sysApps =  sysMemberuseMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}
	
	/**
	 * 获取一个粉丝OpenId的消费记录列表
	 * @param openId
	 * @return
	 */
	public GridResponse<SysMemberuse> findByUserId(String openId) {
		GridResponse<SysMemberuse> response = new GridResponse<SysMemberuse>();
		List<SysMemberuse> sysApps =  sysMemberuseMapper.findByuserId(openId);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}

	/**
	 * 根据会员卡参数查询
	 * @param SysMemberuse
	 * @return
	 */
	public List<SysMemberuse> findByParms(SysMemberuse sysMemberuse) {
		List<SysMemberuse> list = sysMemberuseMapper.selectByParm(sysMemberuse);
		return list;
	}
	
	public SysMemberuse getMemberUseByuseId(String source, String appid_useId) {
		return sysMemberuseMapper.findMemberUseByuseId(source, appid_useId);	
	}
	
	/**
	 * 获取会员一段时间内的消费记录
	 * @param appid_userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SysMemberuse> getMemberUseByuserIdAndTime(String appid_userId, Date startTime, Date endTime) {
		return sysMemberuseMapper.findByuserIdAndTime(appid_userId, startTime, endTime);
	}
	 
}