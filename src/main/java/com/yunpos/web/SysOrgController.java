package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysOrg;
import com.yunpos.model.SysUserRole;
import com.yunpos.service.SysOrgService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：组织实体控制类
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@RestController
public class SysOrgController extends BaseController{
	@Autowired
	private  SysOrgService sysOrgService;
	

	@RequestMapping(value="/ajax/org",method = GET)
	public JqGridResponse<SysOrg> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysOrg> dataResponse = sysOrgService.findPageUsers(gridRequest);
		return new JqGridResponse<SysOrg>(dataResponse);
	}

	
	@RequestMapping(value = "/ajax/org/{id}", method = GET)
	public SysOrg read(@PathVariable("id") int id) {
		return sysOrgService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/org", method = RequestMethod.POST)
	public GridRowResponse create(@Valid  SysOrg  org) {
		org.setCreateDate(new Date());
		//org.setCreateUserId(this.getUser().getId());
		org.setExtExpanded(true);
		org.setExtLoaded(true);
		org.setExtParent(org.getOrgParentId());
		if(org.getOrgParentId()!=null){
			SysOrg parantOrg = sysOrgService.findById(org.getOrgParentId());
			org.setExtLevel(parantOrg.getExtLevel()+1);
			org.setExtIsLeaf(true);
			org.setOrgParentName(parantOrg.getOrgName());
			org.setOrgParentNo(parantOrg.getOrgNo());
			
			parantOrg.setExtIsLeaf(false);
			sysOrgService.update(parantOrg);
		}else{
			org.setExtIsLeaf(true);
			org.setExtLevel(0);
		}
		sysOrgService.save(org);
		return new  GridRowResponse(org.getId());
	}

	@RequestMapping(value = "/ajax/org/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysOrg org, @PathVariable("id") int id) {
		org.setId(id);
		org.setModifyDate(new Date());
		//org.setModifyUserId(this.getUser().getId());
		sysOrgService.update(org);
		return new GridRowResponse(org.getId());
	}

	@RequestMapping(value = "/ajax/org/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysOrgService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/org/select", method =RequestMethod.GET )
	public List<SysOrg> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysOrg> list = sysOrgService.findAll();
		return list;
	}
	
	
	@RequestMapping(value = "/ajax/org/exist/{orgName}", method = RequestMethod.GET)
	public Object exist(HttpServletRequest request, @PathVariable("orgName") String orgName) throws Exception {
		return sysOrgService.existOrgName(orgName);
	}

}
