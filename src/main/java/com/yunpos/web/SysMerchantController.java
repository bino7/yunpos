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
import com.yunpos.model.Industry;
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysOrg;
import com.yunpos.model.SysUser;
import com.yunpos.service.IndustryService;
import com.yunpos.service.SysAgentMerchantService;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysOrgService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.MD5Utils;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：商户信息控制器
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
public class SysMerchantController extends BaseController{
	@Autowired
	private  SysMerchantService sysMerchantService;
	
	@Autowired
	private  SysAgentMerchantService sysAgentMerchantService;
	
	@Autowired
	private  IndustryService industryService;
	
	@Autowired
	private SysOrgService sysOrgService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**所以商户信息
	 * 商户
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/merchant",method = GET)
	public List<SysMerchant> list()throws ServiceException{
		return sysMerchantService.findAll();
		
	}
	
	/**
	 * 查询商户列表
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/merchant/search",method = GET)
	public JqGridResponse<SysMerchant> search(SysMerchant sysMerchant)throws ServiceException{
		GridResponse<SysMerchant> dataResponse = sysMerchantService.search(sysMerchant);
		return new JqGridResponse<SysMerchant>(dataResponse);
	}
	
	/**
	 * 商户详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/merchant/{id}", method = GET)
	public SysMerchant read(@PathVariable("id") int id) {
		return sysMerchantService.findById(id);
	}
	
	/**
	 * 现在商户信息,，商户用户信息
	 * @param sysMerchant
	 * @return
	 */
	@RequestMapping(value = "/ajax/merchant", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMerchant sysMerchant) {
		SysAgentMerchant sysAgentMerchant  =  sysAgentMerchantService.getDataByBaseUserId(getUser().getId()); 
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgName(sysMerchant.getCompanyName());
		sysOrg.setCreateUserId(getUser().getId());
		sysOrg.setCreateDate(new Date());
		sysOrg.setLevel(1);
		sysOrg.setOrgParentId(sysAgentMerchant.getOrgId());
		sysOrg.setOrgNo(sysOrgService.getOrgNo(sysOrg));
		sysOrgService.save(sysOrg);
		
		SysUser user = new SysUser();
		user.setUserName(sysMerchant.getUserName());
		user.setNickname(sysMerchant.getNickname());
		user.setPassword(sysMerchant.getPassword());
		user.setCreatedBy(getUser().getId());
		user.setCreatedAt(new Date());
		user.setOrgId(sysOrg.getId());
		user.setOrgName(sysOrg.getOrgName());
		user.setDescription(sysMerchant.getDescription());
		sysUserService.creatSysUser(user);

		sysMerchant.setMd5Key(MD5Utils.genRandomNum(36));
		sysMerchant.setSerialNo(sysOrg.getOrgNo());
		sysMerchant.setBaseUserId(user.getId());
		sysMerchant.setAgentSerialNo(sysAgentMerchant.getAgentSerialNo());
		sysMerchant.setAuditStatus(new Byte("0"));
		sysMerchantService.save(sysMerchant);
		return null;
	}

	/**
	 * 更新商户信息 ，更新商户用户信息
	 * @param sysMerchant
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/merchant/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMerchant sysMerchant, @PathVariable("id") int id) {
		/*
		SysUser user = sysUserService.findById(sysMerchant.getBaseUserId());
		user.setUserName(sysMerchant.getUserName());
		user.setNickname(sysMerchant.getNickname());
		user.setPassword(sysMerchant.getNewPassword());
		user.setUpdatedBy(getUser().getId());
		user.setDescription(sysMerchant.getDescription());
		sysUserService.updateSysUser(user);
		
		SysOrg sysOrg = sysOrgService.findById(user.getOrgId());
		sysOrg.setOrgName(sysMerchant.getCompanyName());
		sysOrg.setModifyUserId(user.getUpdatedBy());
		sysOrg.setModifyDate(new Date());
		sysOrgService.update(sysOrg);
		*/
		
		sysMerchant.setId(id);
		sysMerchantService.update(sysMerchant);
		return new GridRowResponse(sysMerchant.getId());
	}

	/**
	 * 删除商户
	 * @param id
	 */
	@RequestMapping(value = "/ajax/merchant/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMerchantService.delete(id);
	}
	
	/**
	 * 用户名是否重复
	 * @param request
	 * @param type  userName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/user/agentmerchant/{type}/{value}", method =RequestMethod.GET )
	public Object exist(HttpServletRequest request, @PathVariable("type") String type,@PathVariable("value") String value) throws Exception {
		boolean flag = false;
		if (type.equals("userName")){
			flag = sysUserService.userNameExist(value);
		}
		return flag;
	}
	
	
	/**行业数据
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/industry",method = GET)
	public List<Industry> industrList() throws ServiceException{
		return industryService.findAll();
		
	}
}
