package com.yunpos.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> model) {
		return "login";
	}

	@RequestMapping("/user")
	public String user(Map<String, Object> model) {
		return "user";
	}
	
	@RequestMapping("/fun")
	public String fun(Map<String, Object> model) {
		return "fun";
	}
	
	@RequestMapping("/book")
	public String book(Map<String, Object> model) {
		return "book";
	}
	
	@RequestMapping("/res")
	public String res(Map<String, Object> model) {
		return "res";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}
}
