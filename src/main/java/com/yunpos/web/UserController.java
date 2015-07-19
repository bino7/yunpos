package com.yunpos.web;

import static java.util.Collections.singletonList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriTemplate;

import com.yunpos.model.User;
import com.yunpos.model.ViewPage;
import com.yunpos.service.UserService;
/**
 * 
 * 功能描述：rest风格用户控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月18日
 * @author Devin_Yang 修改日期：2015年7月18日
 *
 */
@Controller
//@RestController
@RequestMapping(value = "rest/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView listUser() {
		ModelAndView mav = new ModelAndView("security/user/listUser");
		return mav;
	}
	
	
	
	@RequestMapping(method = GET)
	public @ResponseBody ViewPage<User> list() {
		ViewPage<User> viewPage = new ViewPage<User>();
		List<User> list = userService.findAllUsers();
		viewPage.setPage(0);
		viewPage.setRows(list);
		viewPage.setMax(10);
		viewPage.setTotal(list.size());
		return viewPage;
	}
	
	
	@RequestMapping(value = "/get/{id}")
	public @ResponseBody User read(@PathVariable("id") int id) {
		return userService.findById(id);
	}


	@RequestMapping(value = "/update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
	    if (userService.exists(id)) {
	    	user.setUserid(id);
	    	userService.updateUsers(user);
	    }
	}

	@RequestMapping(value="/add")
	public ResponseEntity<String> createUser(HttpServletRequest request, @RequestBody User user) {
		final int id = userService.insertUsers(user);
		URI uri = new UriTemplate("{requestUrl}/{id}").expand(request.getRequestURL().toString(), id);
		final HttpHeaders headers = new HttpHeaders();
		headers.put("Location", singletonList(uri.toASCIIString()));
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	

	@RequestMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") int id) {
		userService.delUsers(id);
	}
	
}
