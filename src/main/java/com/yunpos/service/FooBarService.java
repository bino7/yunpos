package com.yunpos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Foo;
import com.yunpos.model.User;
import com.yunpos.persistence.dao.FooMapper;
import com.yunpos.persistence.dao.UserMapper;

@Service
public class FooBarService {

    private final FooMapper fooMapper;

    @Autowired
    public FooBarService(FooMapper fooMapper) {
        this.fooMapper = fooMapper;
    }
    @Autowired
    public UserMapper userMapper;
//    public void createTestData() {
//        for (int i = 0; i < 10; i++) {
//            int id = i + 1;
//            fooMapper.create(new Foo(id, String.format("foo-%d", id)));
//        }
//    }

    public List<Foo> findAllFoos() {
        return fooMapper.findAll();
    }
    
    public Optional<Foo> findFoo(int id) {
        return Optional.ofNullable(fooMapper.findOne(id));
    }
    
    public void printAllFooAndAllBar() {
//    	User u =userMapper.selectByPrimaryKey(1);
//    	System.out.println("user phone"+u.getPhone());
        System.out.println("foo:");
        fooMapper.findAll().stream().forEach(System.out::println);
    }
}
