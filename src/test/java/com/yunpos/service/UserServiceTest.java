package com.yunpos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.model.SysUser;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {

	@Autowired
	private SysUserService sysUserService;

	@Test
	public void testFindById() {
		SysUser sysUser = sysUserService.findById(1);		
		System.out.println(sysUser.getUserName());
	}


}
