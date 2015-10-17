package com.yunpos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yunpos.model.SysOrg;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysOrgMapper;
import com.yunpos.persistence.dao.SysRoleMapper;
import com.yunpos.utils.Tools;
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
		List<SysOrg> sysOrgList = sysOrgMapper.findAll();
	
		List<String> orgParentNos = new ArrayList<>();
		
		for(SysOrg org :sysOrgList){
			String orgParentNo = org.getOrgParentNo();
			if(!Strings.isNullOrEmpty(orgParentNo)){
				orgParentNos.add(orgParentNo);
			}
		}
		
		for(SysOrg org :sysOrgList){
			if(orgParentNos.contains(org.getOrgNo())){
				org.setIsLeaf(0);
			}
		}
		return sysOrgList;
	
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

	
	public GridResponse<SysOrg> search(GridRequest gridRequest) {
		GridResponse<SysOrg> response = new GridResponse<SysOrg>();
		List<SysOrg> orgs =  sysOrgMapper.findByCondition(gridRequest);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(orgs);
		response.setTotalRowCount(orgs.size());
		return response;
	}

	
	/**
	 * 获取当前等级组织结构最大编号
	 * @param sysOrg
	 * @return
	 */
	public String getOrgNo(SysOrg sysOrg){
		String  maxOrgNo = sysOrgMapper.findMaxOrgNo(sysOrg);
		if(Tools.isNullOrEmpty(maxOrgNo)){
			maxOrgNo = "";
			if(!Tools.isNullOrEmpty(sysOrg.getOrgParentNo())){
				maxOrgNo = sysOrg.getOrgParentNo() ;
			}
			maxOrgNo +=  "0000";
		}
		String orgNo = Integer.parseInt(maxOrgNo) + 1 +"";
		if(orgNo.length() % 4 == 1 ){
			orgNo = "000" + orgNo;
		}else if(orgNo.length() % 4 == 2 ){
			orgNo = "00" + orgNo;
		}else if(orgNo.length() % 4 == 3 ){
			orgNo = "0" + orgNo;
		} 
		return orgNo;
	}
	
	
	/**
	 * 获取当前等级组织结构最大编号
	 * @param sysOrg
	 * @return
	 */
	public String getOrgNo(String orgParentNo ,int  orgParentId){
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgParentId(orgParentId);
		sysOrg.setOrgParentNo(orgParentNo);
		return getOrgNo(sysOrg);
	}
	
	public List<SysOrg> getJsonOrg() {
		List<SysOrg> level1 = sysOrgMapper.findLevelOne();
		List<SysOrg> resultList = new ArrayList<SysOrg>();
		for(SysOrg org:level1){
			SysOrg node = jsonMenu(org.getOrgNo());
			resultList.add(node);
		}
		return resultList;
	}

	private SysOrg jsonMenu(String orgNo) {
		List<SysOrg> resultList = new ArrayList<SysOrg>();
			SysOrg node = sysOrgMapper.selectByOrgNo(orgNo);
			// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
			List<SysOrg> childTreeNodes = sysOrgMapper.findChildByParenOrgNo(orgNo);
			// 遍历子节点
			for (SysOrg child : childTreeNodes) {
				SysOrg n = jsonMenu(child.getOrgNo()); // 递归
				node.getChildren().add(n);
			}
		return node;
	}
	
	public List<SysOrg> getTree() {
		 List<SysOrg>  orgs = sysOrgMapper.findAll();
		 Map<String,Object> map1 =  null;
		 Map<String,Object> map2 =  null;
		 Map<String,Object> map3 =  null;
		 List<Map<String,Object>> list = null;
		 for(SysOrg org: orgs){
			  map1 = new HashMap<>();
			  map2 = new HashMap<>();
			  map3 = new HashMap<>();
			  list = new ArrayList<>();
			  map1.put("cid", "2");
			  map1.put("text", org.getOrgNo());
			  list.add(map1);
			  map2.put("cid", "3");
			  map2.put("text", org.getOrgName());
			  list.add(map2);
			  map3.put("cid", "4");
			  map3.put("text", org.getCreateDateStr());
			  list.add(map3);
			  org.setCells(list);
			  
		 }
		return orgs;
	}
}
