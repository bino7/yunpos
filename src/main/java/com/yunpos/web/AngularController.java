package com.yunpos.web;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yunpos.model.UserDetails;

@Controller
public class AngularController {

	@RequestMapping(value = "/angular/userDetails")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody UserDetails getUser() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Praveen");
		userDetails.setEmailId("praveen@gmail.com");
		return userDetails;
	}
	
	
	@RequestMapping(value = "/angular")
	public String  angular() {
		return "angular";
	}
}
