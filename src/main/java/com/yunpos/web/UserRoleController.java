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

import com.yunpos.model.UserRole;
import com.yunpos.model.ViewPage;
import com.yunpos.service.UserRoleService;

@Controller
@RequestMapping("rest/userRole")
public class UserRoleController extends BaseController{
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<UserRole> list() {
		ViewPage<UserRole> viewPage = new ViewPage<UserRole>();
		List<UserRole> list = userRoleService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		//viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody UserRole read(@PathVariable("id") int id) {
		return userRoleService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUserRole(@PathVariable("id") int id, @RequestBody UserRole userRole) {
	    if (userRoleService.exists(id)) {
	    	userRole.setUserid(id);
	    	userRoleService.update(userRole);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createUserRole(HttpServletRequest request, @RequestBody UserRole userRole) {
		userRoleService.insert(userRole);
		final int id = userRole.getRoleid();
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserRole(@PathVariable("id") int id) {
		userRoleService.delete(id);
	}
}
