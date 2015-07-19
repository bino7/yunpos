package com.yunpos.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.FooBarProperties;
import com.yunpos.model.Foo;
import com.yunpos.service.FooBarService;

@RestController
@RequestMapping("/foobar")
public class FooBarController {
    
    private final FooBarService fooBarService;
    private final FooBarProperties fooBarProperties;

    
    @Autowired
    public FooBarController(FooBarService fooBarService, FooBarProperties fooBarProperties) {
        this.fooBarService = fooBarService;
        this.fooBarProperties = fooBarProperties;
    }
    
    @RequestMapping("/foos")
    public List<Foo> findFoos() {
        return fooBarService.findAllFoos();
    }
    
    @RequestMapping("/foos/{id}")
    public ResponseEntity<Foo> findFoo(@PathVariable int id) {
        return fooBarService.findFoo(id)
                .map(foo -> new ResponseEntity<>(foo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    @RequestMapping("/props")
    public FooBarProperties fooBarProps() {
        return fooBarProperties;
    }
    
}
