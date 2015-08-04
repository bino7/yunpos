package com.yunpos.web;

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
import com.yunpos.model.User;
import com.yunpos.security.SecurityUtils;
import com.yunpos.service.UserService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;
@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/ajax/user",method = RequestMethod.GET)
	public JqGridResponse<User> list(JqGridRequest jqGridRequest) throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<User> dataResponse = userService.findPageUsers(gridRequest);
		return new JqGridResponse<User>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.GET)
	public User read(@PathVariable("id") int id) throws ServiceException{
		return userService.findById(id);
	}

	@RequestMapping(value="/ajax/user",method = RequestMethod.POST)
	public GridRowResponse create(@Valid User user)throws ServiceException {
		user.setCreatedAt(new Date());
		user.setCreatedBy(getUser().getId());
		user.setStatus("1");
		userService.save(user);
		return new  GridRowResponse(user.getId());
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid User user, @PathVariable("id") int id) {
		user.setId(id);
		user.setUpdatedAt(new Date());
		user.setUpdatedBy(getUser().getId());
		user.setSalt(SecurityUtils.generateSalt());
		userService.update(user);
		return new GridRowResponse(user.getId());
	}

	@RequestMapping(value = "/ajax/user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		userService.delete(id);
	}
	
	
	@RequestMapping(value = "/ajax/user/select", method =RequestMethod.GET )
	public List<User> getUserSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> list = userService.findAll();
		return list;
	}


}
