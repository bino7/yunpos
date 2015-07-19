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

import com.yunpos.model.Role;
import com.yunpos.model.ViewPage;
import com.yunpos.service.RoleService;
@Controller
@RequestMapping("rest/role")
public class RoleController {
	@Autowired
	private RoleService roleService;



	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<Role> list() {
		ViewPage<Role> viewPage = new ViewPage<Role>();
		List<Role> list = roleService.list();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/{id}", method = GET)
	public @ResponseBody Role read(@PathVariable("id") int id) {
		return roleService.findById(id);
	}


	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateRole(@PathVariable("id") int id, @RequestBody Role role) {
	    if (roleService.exists(id)) {
	    	role.setRoleid(id);
	    	roleService.update(role);
	    }
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createRole(HttpServletRequest request, @RequestBody Role role) {
		final int id = roleService.insert(role);
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRole(@PathVariable("id") int id) {
		roleService.delete(id);
	}
}
