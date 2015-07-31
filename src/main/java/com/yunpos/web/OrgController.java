package com.yunpos.web;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.google.common.base.Strings;
import com.yunpos.model.Org;
import com.yunpos.model.ViewPage;
import com.yunpos.security.SecurityUser;
import com.yunpos.service.OrgService;
import com.yunpos.utils.PageDate;

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
@RequestMapping("sys/org")
public class OrgController extends BaseController{
	
	@Autowired
	private  OrgService orgService;
	

	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<Org> list() {
		ViewPage<Org> viewPage = new ViewPage<Org>();
		List<Org> list = orgService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		//viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	
	// 获取组织的下拉框
	@RequestMapping(value = "/getOrgSelectList", method = { RequestMethod.POST, RequestMethod.GET })
	public  @ResponseBody List<Org> getOrgSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Org> orgList = orgService.findAll();
//		StringBuilder builder = new StringBuilder();
//		builder.append("<select>");
//		for (int i = 0; i < orgList.size(); i++) {
//			builder.append("<option value=\"" + orgList.get(i).getOrgId() + "\">" + orgList.get(i).getOrgName() + "</option>");
//		}
//		builder.append("</select>");
//		
//		return builder.toString();
		
		return orgList;
	}
	
	
	@RequestMapping(value = "/operate", method = { RequestMethod.POST, RequestMethod.GET })
	public void operate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDate pageParam = this.getPageParam();
		String oper = pageParam.getString("oper");
		String id = pageParam.getString("id");
		String level = request.getParameter("level");
		
		if (oper.equals("del") && !Strings.isNullOrEmpty(id)) {
			String[] ids = id.split(",");
			orgService.batchDeleteByIds( (Integer[])ConvertUtils.convert(ids, Integer.class));
		}else {
			Org org = null;
			if (oper.equals("edit")) {
				org = orgService.findById(Integer.valueOf(id));
			}
			//获取当前登录用户
			//SecurityUser currentUser = com.yunpos.security.SecurityUtils.getCurrentUser();
			Org entity = new Org();
			entity.setExtIsLeaf(Boolean.valueOf(pageParam.getString("extIsLeaf")));
			entity.setExtLevel(Integer.valueOf(pageParam.getString("extLevel")));
			entity.setExtExpanded(Boolean.valueOf(pageParam.getString("extExpanded")));
			entity.setExtLoaded(Boolean.valueOf(pageParam.getString("extLoaded")));
			entity.setExtParent(Integer.valueOf(pageParam.getString("extParent")));
			
			if(!Strings.isNullOrEmpty(pageParam.getString("orgParentName"))){
				Org temp = orgService.findById(Integer.valueOf(pageParam.getString("orgParentName")));
				entity.setOrgParentNo(temp.getOrgParentNo());
				entity.setOrgParentName(temp.getOrgParentName());
				entity.setOrgParentId(temp.getOrgParentId());
			}
			entity.setOrgName(pageParam.getString("orgName"));
			entity.setOrgNo(pageParam.getString("orgNo"));
			
			if (oper.equals("edit")) {
				entity.setModifyDate(new Date());
				//entity.setModifyUserId(currentUser.getId());
				entity.setOrgId(org.getOrgId());
				orgService.update(entity);
			} else if (oper.equals("add")) {
				entity.setCreateDate(new Date());
				//entity.setCreateUserId(currentUser.getId());
				orgService.save(entity);
			}
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody Org read(@PathVariable("id") int id) {
		return orgService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOrg(@PathVariable("id") int id, @RequestBody Org org) {
	    if (orgService.exists(id)) {
	    	org.setOrgId(id);
	    	orgService.update(org);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createOrg(HttpServletRequest request, @RequestBody Org org) {
		orgService.insert(org);
		int id = org.getOrgId();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	

	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrg(@PathVariable("id") int id) {
		orgService.delete(id);
	}

}
