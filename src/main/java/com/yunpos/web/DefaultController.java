package com.yunpos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController extends BaseController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
    @RequestMapping("/role")
    public String role() {
        return "role";
    }
    
    @RequestMapping("/fun")
    public String fun() {
        return "fun";
    }
    
    @RequestMapping("/res")
    public String res() {
        return "res";
    }
    
    @RequestMapping("/org")
    public String org() {
        return "org";
    }
      
}
