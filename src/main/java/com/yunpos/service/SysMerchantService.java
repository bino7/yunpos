package com.yunpos.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.KDT.KdtApiClient;
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysUser;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysAgentMerchantMapper;
import com.yunpos.persistence.dao.SysMerchantMapper;
import com.yunpos.persistence.dao.SysUserMapper;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMerchantService extends EntityService<SysMerchant>{

	@Autowired
	private SysMerchantMapper sysMerchantMapper;
	
	@Autowired
	SysUserMapper sysUserMapper;
	
	@Autowired
	private SysAgentMerchantMapper sysAgentMerchantMapper;
	
	private final static String POSP_ADD_Merchant = "http://y.o2o520.com/index.php?&g=Api&m=Users&a=add";
	private final static String POSP_UPDATE_Merchant = "http://y.o2o520.com/index.php?&g=Api&m=Users&a=edit";
	
	@Override
	public EntityMapper<SysMerchant> getMapper() {
		return sysMerchantMapper;
	}

	public GridResponse<SysMerchant> search(SysMerchant sysMerchant) {
		GridResponse<SysMerchant> response = new GridResponse<SysMerchant>();
		List<SysMerchant> sysMerchantList =  sysMerchantMapper.selectByParm(sysMerchant);
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysMerchantList);
		response.setTotalRowCount(sysMerchantList.size());
		return response;
	}

	public List<SysMerchant> findAll() {
		return sysMerchantMapper.findAll();
	}

	public SysMerchant findBySerialNo(String serialNo) {
		return sysMerchantMapper.findBySerialNo(serialNo);
	}

	
	/**
	 * 根据商户参数查询
	 * @param sysAgentMerchant
	 * @return
	 */
	public List<SysMerchant> findByParms(SysMerchant sysMerchant) {
		List<SysMerchant> list = sysMerchantMapper.selectByParm(sysMerchant);
		return list;
	}
	
	/**
	 * 商户审核
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 */
	public boolean reviewSysMerchant(SysMerchant sysMerchant, int id) throws ClientProtocolException, IOException {
		SysMerchant entity = findById(id);
		if (entity != null) {
			entity.setAuditStatus(sysMerchant.getAuditStatus());
			entity.setAuditMemo(sysMerchant.getAuditMemo());
			update(entity);
			if (entity.getAuditStatus() == 2) {//审核通过后同步到服务窗上
				HashMap<String,String> params = new HashMap<String,String>();
				params.put("serial_no",entity.getSerialNo());//商户编号
				//获取该商户的登录用户
				SysUser sysUser = sysUserMapper.selectByPrimaryKey(entity.getBaseUserId());
				params.put("userName", sysUser.getUserName());//登录用户名
				params.put("password", sysUser.getPassword());//密码
				params.put("email", entity.getEmail());//邮箱
				Date createtime = entity.getCreatedAt();
				if (createtime != null) {
					params.put("createtime", String.valueOf(createtime.getTime()/1000));//创建时间
				}
				Date viptime = entity.getEndTime();
				if (viptime != null) {
					params.put("viptime", String.valueOf(viptime.getTime()/1000));//过期时间
				}	
				params.put("link_man", entity.getContactMan());//联系人
				params.put("mobile ", entity.getTel());//联系手机
				//获取该商户对应的代理商		
				SysAgentMerchant sysAgentMerchant = sysAgentMerchantMapper.selectBySerialNo(entity.getAgentSerialNo());
				params.put("adminuserid", String.valueOf(sysAgentMerchant.getId()));//代理商Id
				params.put("isfuwuchuang", "1");//商户存在
				params.put("user_flg", "1");//商户存在
				params.put("company_name", entity.getCompanyName());//企业名称
				params.put("register_number", entity.getRegisterNumber());//企业注册号
				params.put("status", "1");//状态为启用
				//新增商户到服务窗上
				KdtApiClient kdtApiClient = new KdtApiClient();			
				HttpResponse response = kdtApiClient.post(POSP_ADD_Merchant, params);
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
	 * 更新代理商停用、启用状态
	 * @param sysAgentMerchant
	 * @param id
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public SysMerchant updateStatus(SysMerchant sysMerchant, int id) throws ClientProtocolException, IOException{	
		SysMerchant entity = findById(id);
		if (entity != null) {
			entity.setStatus(sysMerchant.getStatus());
			update(entity);
			//同时更新到posp平台
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("serial_no",entity.getAgentSerialNo());//代理商编号
			params.put("status", String.valueOf(entity.getStatus()));//代理商状态

			KdtApiClient kdtApiClient = new KdtApiClient();
			HttpResponse response = kdtApiClient.post(POSP_UPDATE_Merchant, params);
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
