package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yunpos.model.Org;
import com.yunpos.model.ViewPage;
import com.yunpos.service.OrgService;

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
@Controller
public class OrgController extends BaseController{
	@Autowired
	private  OrgService orgService;
	

	@RequestMapping(value="/ajax/org",method = GET)
	public @ResponseBody ViewPage<Org> list() {
		ViewPage<Org> viewPage = new ViewPage<Org>();
		List<Org> list = orgService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		//viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}

	
	@RequestMapping(value = "/ajax/org/{id}", method = GET)
	public @ResponseBody  Org read(@PathVariable("id") int id) {
		return orgService.findById(id);
	}
	
	
	@RequestMapping(value = "/ajax/org", method = RequestMethod.POST)
	public void create(@Valid  Org  org) {
		org.setCreateDate(new Date());
		org.setCreateUserId(this.getUser().getId());
		org.setExtExpanded(true);
		org.setExtLoaded(true);
		org.setExtParent(org.getOrgParentId());
		if(org.getOrgParentId()!=null){
			Org parantOrg = orgService.findById(org.getOrgParentId());
			org.setExtLevel(parantOrg.getExtLevel()+1);
			org.setExtIsLeaf(true);
			org.setOrgParentName(parantOrg.getOrgName());
			org.setOrgParentNo(parantOrg.getOrgNo());
			
			parantOrg.setExtIsLeaf(false);
			orgService.update(parantOrg);
		}else{
			org.setExtIsLeaf(true);
			org.setExtLevel(0);
		}
		orgService.save(org);
	}

	@RequestMapping(value = "/ajax/org/{id}", method = RequestMethod.PUT)
	public void update(@Valid Org org, @PathVariable("id") int id) {
		org.setId(id);
		org.setModifyDate(new Date());
		org.setModifyUserId(this.getUser().getId());
		orgService.update(org);
	}

	@RequestMapping(value = "/ajax/org/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		orgService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/org/select", method =RequestMethod.GET )
	public  @ResponseBody List<Org> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Org> list = orgService.findAll();
		return list;
	}

}
