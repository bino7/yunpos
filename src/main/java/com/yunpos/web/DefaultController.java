package com.yunpos.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController extends BaseController {

    @RequestMapping({"/", "/page/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/page/home")
    public String home() {
        return "home";
    }
    
    @RequestMapping("/page/user")
    public String user() {
        return "user";
    }
    
    @RequestMapping("/page/role")
    public String role() {
        return "role";
    }
    
    @RequestMapping("/page/fun")
    public String fun() {
        return "fun";
    }
    
    @RequestMapping("/page/res")
    public String res() {
        return "res";
    }
    
    @RequestMapping("/page/org")
    public String org() {
        return "org";
    }
    
    @RequestMapping("/page/menu_res")
    public String res_menu() {
        return "resource/menu_res";
    }
    
    @RequestMapping("/page/app_res")
    public String res_app() {
        return "resource/app_res";
    }
    
    @RequestMapping("/page/button_res")
    public String res_button() {
        return "resource/button_res";
    }
    
    
      
}
