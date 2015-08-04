package com.yunpos.web;

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

import com.yunpos.model.Org;
import com.yunpos.model.User;
import com.yunpos.model.ViewPage;
import com.yunpos.security.SecurityUtils;
import com.yunpos.service.UserService;
@Controller
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/ajax/user",method = RequestMethod.GET)
	public @ResponseBody ViewPage<User> list() {
		ViewPage<User> viewPage = new ViewPage<User>();
		List<User> list = userService.findAll();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setTotal(list.size());
		viewPage.setRecords(list.size());
		return viewPage;
	}
	
	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.GET)
	public @ResponseBody  User read(@PathVariable("id") int id) {
		return userService.findById(id);
	}

	@RequestMapping(value="/ajax/user",method = RequestMethod.POST)
	public void create(@Valid User user) {
		user.setCreatedAt(new Date());
		user.setCreatedBy(getUser().getId());
		user.setStatus("1");
		userService.save(user);
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.PUT)
	public void update(@Valid User user, @PathVariable("id") int id) {
		user.setId(id);
		user.setUpdatedAt(new Date());
		user.setUpdatedBy(getUser().getId());
		user.setSalt(SecurityUtils.generateSalt());
		userService.update(user);
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		userService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/user/select", method =RequestMethod.GET )
	public  @ResponseBody List<User> getUserSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> list = userService.findAll();
		return list;
	}


}
