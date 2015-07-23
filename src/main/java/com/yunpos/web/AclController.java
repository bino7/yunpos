package com.yunpos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/acl")
public class AclController {
    @RequestMapping
    public String index() {
        return "acl/index";
    }
}
