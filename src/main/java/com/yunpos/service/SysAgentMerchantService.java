package com.yunpos.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.KDT.KdtApiClient;
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysUser;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAgentMerchantMapper;
import com.yunpos.persistence.dao.SysUserMapper;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysAgentMerchantService extends EntityService<SysAgentMerchant>{
	@Autowired
	private SysAgentMerchantMapper sysAgentMerchantMapper;
	
	@Autowired
	SysUserMapper sysUserMapper;
	
	private final static String POSP_ADD_AgentMerchant = "http://y.o2o520.com/index.php?&g=Api&m=User&a=add";
	private final static String POSP_UPDATE_AgentMerchant = "http://y.o2o520.com/index.php?&g=Api&m=User&a=edit";
	
	@Override
	public EntityMapper<SysAgentMerchant> getMapper() {
		return sysAgentMerchantMapper;
	}

	public List<SysAgentMerchant> findAll() {
		return sysAgentMerchantMapper.findAll();
	}

	public GridResponse<SysAgentMerchant> search(GridRequest gridRequest) {
		GridResponse<SysAgentMerchant> response = new GridResponse<SysAgentMerchant>();
		List<SysAgentMerchant> sysApps =  sysAgentMerchantMapper.search();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysApps);
		response.setTotalRowCount(sysApps.size());
		return response;
	}

	/**
	 * 根据代理商参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<SysAgentMerchant> findByParms(SysAgentMerchant sysAgentMerchant) {
		List<SysAgentMerchant> list = sysAgentMerchantMapper.selectByParm(sysAgentMerchant);
		return list;
	}
	
	
	/**
	 * 根据代理商用户信息查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public SysAgentMerchant getDataByBaseUserId(Integer baseUserId) {
		SysAgentMerchant sysAgentMerchant = new SysAgentMerchant();
		sysAgentMerchant.setBaseUserId(baseUserId);
		sysAgentMerchant.setStatus(1);
		List<SysAgentMerchant> list = findByParms(sysAgentMerchant); 
		if(!Tools.isNullOrEmpty(list)){
			sysAgentMerchant = list.get(0);
		}
		return sysAgentMerchant;
	}
	
	
	
	/**
	 * 新增代理商
	 * @param session
	 */
	public void createSysAgentMerchant(SysAgentMerchant sysAgentMerchant) {
		save(sysAgentMerchant);
	}
	
	/**
	 * 代理商审核
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 * @throws JsonProcessingException 
	 */
	public boolean reviewSysAgentMerchant(SysAgentMerchant sysAgentMerchant, int id) throws ClientProtocolException, IOException {
		SysAgentMerchant entity = findById(id);
		if (entity != null) {
			entity.setAuditStatus(sysAgentMerchant.getAuditStatus());
			entity.setAuditOpinion(sysAgentMerchant.getAuditOpinion());
			update(entity);
			if (entity.getAuditStatus() == 2) {//审核通过后同步到POSP上
				HashMap<String,String> params = new HashMap<String,String>();
				params.put("serial_no",entity.getAgentSerialNo());//代理商编号
				//获取该代理商的登录用户
				SysUser sysUser = sysUserMapper.selectByPrimaryKey(entity.getBaseUserId());
				params.put("userName", sysUser.getUserName());//登录用户名
				params.put("password", sysUser.getPassword());//密码
				params.put("linkman", entity.getContactMan());//联系人
				params.put("phone", entity.getPhone());//联系电话
				params.put("expire_time", String.valueOf(entity.getEndTime().getTime()/1000));//过期时间
				params.put("company_name", entity.getCompanyName());//公司名称
				params.put("status", "1");//状态为启用
				//新增商户到POSP上
				KdtApiClient kdtApiClient = new KdtApiClient();
				HttpResponse response = kdtApiClient.post(POSP_ADD_AgentMerchant, params);
				HttpEntity responseEntity = response.getEntity();
				if (responseEntity != null) {//调用成功
					String content = EntityUtils.toString(responseEntity, "UTF-8");
					ObjectMapper objectMapper = new ObjectMapper();
					JsonNode root = objectMapper.readTree(content);	
					int errcode = root.path("errcode").asInt();
					if (errcode == 0) {//更新成功
						return true;
					}else {//更新失败
						return false;
					}					
				}else {//调用失败
					return false;
				}			
			}else {//非审核状态不用同步
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 更新商户停用、启用状态
	 * @param sysAgentMerchant
	 * @param id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public SysAgentMerchant updateStatus(SysAgentMerchant sysAgentMerchant, int id) throws ClientProtocolException, IOException{	
		SysAgentMerchant entity = findById(id);
		if (entity != null) {
			entity.setStatus(sysAgentMerchant.getStatus());
			update(entity);
			//同时更新到posp平台
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("serial_no",entity.getAgentSerialNo());//代理商编号
			params.put("status", String.valueOf(entity.getStatus()));//代理商状态

			KdtApiClient kdtApiClient = new KdtApiClient();
			HttpResponse response = kdtApiClient.post(POSP_UPDATE_AgentMerchant, params);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {//调用成功
				String content = EntityUtils.toString(responseEntity, "UTF-8");
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode root = objectMapper.readTree(content);	
				int errcode = root.path("errcode").asInt();
				if (errcode == 0) {//更新成功
					return entity;
				}else {//更新失败
					return null;
				}					
			}else {//调用失败
				return null;
			}
		}else {
			return null;
		}
	}
}
