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
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysOrg;
import com.yunpos.model.SysStore;
import com.yunpos.model.SysUser;
import com.yunpos.service.IndustryService;
import com.yunpos.service.SysOrgService;
import com.yunpos.service.SysStoreService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：门店信息控制器
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
public class SysStoreController extends BaseController{
	@Autowired
	private  SysStoreService sysStoreService;
	
	@Autowired
	private  IndustryService industryService;
	
	@Autowired
	private SysOrgService sysOrgService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**所以门店信息
	 * 门店
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/Store",method = GET)
	public List<SysStore> list()throws ServiceException{
		return sysStoreService.findAll();
		
	}
	
	/**
	 * 查询门店列表
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/store/search",method = GET)
	public JqGridResponse<SysStore> search(SysStore sysStore)throws ServiceException{
		GridResponse<SysStore> dataResponse = sysStoreService.search(sysStore);
		return new JqGridResponse<SysStore>(dataResponse);
	}
	
	/**
	 * 门店详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/store/{id}", method = GET)
	public SysStore read(@PathVariable("id") int id) {
		return sysStoreService.findById(id);
	}
	
	/**
	 * 现在门店信息,，门店用户信息
	 * @param sysStore
	 * @return
	 */
	@RequestMapping(value = "/ajax/Store", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysStore sysStore) {
		SysUser user = new SysUser();
		user.setUserName(sysStore.getUserName());
		user.setNickname(sysStore.getNickname());
		user.setPassword(sysStore.getPassword());
		user.setCreatedBy(getUser().getId());
		sysUserService.creatSysUser(user);
		
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgName(sysStore.getStoreName());
		sysOrg.setCreateUserId(user.getCreatedBy());
		sysOrg.setCreateDate(new Date());
		sysOrg.setLevel(1);
		sysOrg.setOrgNo("111111");
		sysOrgService.save(sysOrg);
		
		sysStoreService.save(sysStore);
		return new GridRowResponse(sysStore.getId());
	}

	/**
	 * 更新门店信息 ，更新门店用户信息
	 * @param sysStore
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/store/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysStore sysStore, @PathVariable("id") int id) {
		
		SysUser user = sysUserService.findById(Integer.parseInt(sysStore.getUserId()));
		user.setUserName(sysStore.getUserName());
		user.setNickname(sysStore.getNickname());
		user.setPassword(sysStore.getNewPassword());
		user.setUpdatedBy(getUser().getId());
		sysUserService.updateSysUser(user);
		
//		SysOrg sysOrg = sysOrgService.findById(user.getOrgId());
//		sysOrg.setOrgName(sysStore.getStoreName());
//		sysOrg.setModifyUserId(user.getUpdatedBy());
//		sysOrg.setModifyDate(new Date());
//		sysOrgService.update(sysOrg);
		
		
		sysStore.setId(id);
		sysStoreService.update(sysStore);
		return new GridRowResponse(sysStore.getId());
	}

	/**
	 * 删除门店
	 * @param id
	 */
	@RequestMapping(value = "/ajax/store/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysStoreService.delete(id);
	}
	
	/**
	 * 用户名是否重复
	 * @param request
	 * @param type  userName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax/user/sysStore/{type}/{value}", method =RequestMethod.GET )
	public Object exist(HttpServletRequest request, @PathVariable("type") String type,@PathVariable("value") String value) throws Exception {
		boolean flag = false;
		if (type.equals("userName")){
			flag = sysUserService.userNameExist(value);
		}
		return flag;
	}
	
	

}
