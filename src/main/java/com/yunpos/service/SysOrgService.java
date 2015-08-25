package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysOrg;
import com.yunpos.model.SysRole;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysOrgMapper;
import com.yunpos.persistence.dao.SysRoleMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

/**
 * 
 * 功能描述：组织服务层
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@Service
public class SysOrgService extends EntityService<SysOrg> {
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public EntityMapper<SysOrg> getMapper() {
		return sysOrgMapper;
	}
	
	public List<SysOrg> findAll(){
		return sysOrgMapper.findAll();
	}

	public GridResponse<SysOrg> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysOrg> response = new GridResponse<SysOrg>();
		List<SysOrg> orgs =  sysOrgMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(orgs);
		response.setTotalRowCount(orgs.size());
		return response;
	}

	public boolean existOrgName(String orgName) {
		List<SysOrg> list = sysOrgMapper.findByOrgName(orgName);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

}
