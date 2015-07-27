package com.yunpos.web;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import com.yunpos.model.Privilege;
import com.yunpos.model.ViewPage;
import com.yunpos.service.PrivilegeService;

/**
 * 
 * 功能描述：权限资源控制器
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
@RequestMapping("rest/privilege")
public class PrivilegeController extends BaseController{
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<Privilege> list() {
		ViewPage<Privilege> viewPage = new ViewPage<Privilege>();
		List<Privilege> list = privilegeService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		//viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody  Privilege read(@PathVariable("id") int id) {
		return privilegeService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePrivilege(@PathVariable("id") int id, @RequestBody Privilege privilege) {
	    if (privilegeService.exists(id)) {
	    	privilege.setPrivilegeid(id);
	    	privilegeService.update(privilege);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createPrivilege(HttpServletRequest request, @RequestBody Privilege privilege) {
		privilegeService.insert(privilege);
		final int id  = privilege.getPrivilegeid();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrivilege(@PathVariable("id") int id) {
		privilegeService.delete(id);
	}
}
