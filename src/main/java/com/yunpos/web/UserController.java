package com.yunpos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunpos.model.User;
import com.yunpos.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method= RequestMethod.GET)
	public void findById(){
		User user = userService.findById(1);
		User user2 = userService.findByUserName("yang");
		System.out.println(user.getLoginname());
	}

}
