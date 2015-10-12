package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysOrg;
import com.yunpos.model.SysUser;
import com.yunpos.service.SysAgentMerchantService;
import com.yunpos.service.SysOrgService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：代理商信息控制器 
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月28日
 * @author Devin_Yang 修改日期：2015年8月28日
 *
 */
@RestController
public class SysAgentMerchantController extends BaseController {
	
	@Autowired
	private  SysAgentMerchantService sysAgentMerchantService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysOrgService sysOrgService;
	
	/**
	 * 代理商列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/agentmerchant",method = GET)
	public List<SysAgentMerchant> list()throws ServiceException{
		return sysAgentMerchantService.findAll();
		
	}
	
	/**
	 * 代理商分页查询查询
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/agentmerchant/search",method = GET)
	public JqGridResponse<SysAgentMerchant> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysAgentMerchant> dataResponse = sysAgentMerchantService.search(gridRequest);
		return new JqGridResponse<SysAgentMerchant>(dataResponse);
	}
	
	/**
	 * 代理商详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = GET)
	public SysAgentMerchant read(@PathVariable("id") int id) {
		return sysAgentMerchantService.findById(id);
	}
	
	/**
	 * 新增代理商信息，现在代理商用户信息
	 * @param sysAgentMerchant
	 * @return
	 */
	@RequestMapping(value = "/ajax/agentmerchant", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysAgentMerchant sysAgentMerchant) {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgName(sysAgentMerchant.getCompanyName());
		sysOrg.setCreateUserId(getUser().getId());
		sysOrg.setCreateDate(new Date());
		sysOrg.setLevel(1);
		sysOrg.setOrgNo(sysOrgService.getOrgNo(sysOrg));
		sysOrgService.save(sysOrg);
		
		SysUser user = new SysUser();
		user.setUserName(sysAgentMerchant.getUserName());
		user.setNickname(sysAgentMerchant.getNickname());
		user.setPassword(sysAgentMerchant.getPassword());
		user.setCreatedBy(getUser().getId());
		user.setCreatedAt(new Date());
		user.setOrgId(sysOrg.getId());
		user.setOrgName(sysOrg.getOrgName());
		user.setDescription(sysAgentMerchant.getDescription());
		sysUserService.creatSysUser(user);
	

		sysAgentMerchant.setBaseUserId(user.getId());
		sysAgentMerchant.setAgentSerialNo(sysOrg.getOrgNo());
		sysAgentMerchant.setOrgId(sysOrg.getId());
		sysAgentMerchantService.save(sysAgentMerchant);

		return new GridRowResponse(-2);
	}

	/**
	 * 代理商更新 更新代理商用户信息
	 * @param sysAgentMerchant
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysAgentMerchant sysAgentMerchant, @PathVariable("id") int id) {
		SysUser user = sysUserService.findById(sysAgentMerchant.getBaseUserId());
		user.setUserName(sysAgentMerchant.getUserName());
		user.setNickname(sysAgentMerchant.getNickname());
		user.setPassword(sysAgentMerchant.getNewPassword());
		user.setUpdatedBy(getUser().getId());
		user.setUpdatedAt(new Date());
		user.setDescription(sysAgentMerchant.getDescription());
		sysUserService.updateSysUser(user);
		
		
		SysOrg sysOrg = sysOrgService.findById(user.getOrgId());
		sysOrg.setOrgName(sysAgentMerchant.getCompanyName());
		sysOrg.setModifyUserId(getUser().getId());
		sysOrg.setModifyDate(new Date());
		sysOrgService.update(sysOrg);
		
		if(!Tools.isNullOrEmpty(sysAgentMerchant)){
			sysAgentMerchant.setId(id);
			sysAgentMerchantService.update(sysAgentMerchant);
		}
		return new GridRowResponse(sysAgentMerchant.getId());
	}

	
	
	/**
	 * 代理商更新 更新代理商用户信息
	 * @param sysAgentMerchant
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/agentmerchant/updateStatus/{id}", method = RequestMethod.PUT)
	public GridRowResponse updateStatus(@Valid SysAgentMerchant sysAgentMerchant, @PathVariable("id") int id) {
		SysAgentMerchant sagentMerchant = sysAgentMerchantService.findById(id);
		sagentMerchant.setStatus(sysAgentMerchant.getStatus());
		 sysAgentMerchantService.update(sagentMerchant);
		return new GridRowResponse(sagentMerchant.getId());
	}
	
	/**
	 * 代理商删除
	 * @param id
	 */
	@RequestMapping(value = "/ajax/agentmerchant/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysAgentMerchantService.delete(id);
	}
	
	/**
	 * 代理商信息是否存在
	 * @param request
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/agentmerchant/exist/{companyName}", method = RequestMethod.GET)
	public Object exist(HttpServletRequest request, @PathVariable("companyName") String companyName) throws Exception {
		SysAgentMerchant sysAgentMerchant = new SysAgentMerchant();
		sysAgentMerchant.setCompanyName(companyName);
		return sysAgentMerchantService.findByParms(sysAgentMerchant);
	}

	
	/**
	 * 
	 * @param request
	 * @param type  companyName/userName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/agentmerchant/{type}/{value}", method =RequestMethod.GET )
	public Object exist(HttpServletRequest request, @PathVariable("type") String type,@PathVariable("value") String value) throws Exception {
		boolean flag = false;
		SysAgentMerchant sysAgentMerchant = new SysAgentMerchant();

		if(type.equals("companyName")){
			sysAgentMerchant.setCompanyName(value);
			List sysAgentMerchants = sysAgentMerchantService.findByParms(sysAgentMerchant);
			if(sysAgentMerchants!=null && sysAgentMerchants.size()>0){
				return true;
			}
		}else if (type.equals("userName")){
			flag = sysUserService.userNameExist(value);
		}
		return flag;
	}
	
	
	/**
	 * 代理商信息审核
	 * @param request
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/agentmerchant/audit", method = RequestMethod.GET)
	public Object audit(HttpServletRequest request,@Valid SysAgentMerchant sysAgentMerchant) throws Exception {
		if(!Tools.isNullOrEmpty(sysAgentMerchant)){
			sysAgentMerchant.setAuditStatus(1);
			sysAgentMerchantService.update(sysAgentMerchant);
		}
		return sysAgentMerchant;
	}

	

	/**
	 * 代理商停用启用
	 * @param request
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/agentmerchant/updateStatus", method = RequestMethod.GET)
	public Object updateStatus(HttpServletRequest request,@Valid SysAgentMerchant sysAgentMerchant) throws Exception {
		if(!Tools.isNullOrEmpty(sysAgentMerchant)){
			if(!Tools.isNullOrEmpty(sysAgentMerchant.getStatus())){
				if(sysAgentMerchant.getStatus() == 1){
					sysAgentMerchant.setStatus(0);
				}else {
					sysAgentMerchant.setStatus(1);
				}
			}
			sysAgentMerchantService.update(sysAgentMerchant);
		}
		
		return sysAgentMerchant;
	}
}
