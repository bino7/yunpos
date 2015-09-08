package com.yunpos.KDT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.model.SysFans;
import com.yunpos.service.SysFansService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SysFansServiceTest {

	@Autowired
	SysFansService sysFansService;
	
	@Test
	public void getFans() {
		SysFans sysFans = sysFansService.findByOid("519261387");
		System.out.println("OpenId:" + sysFans.getOpenId());
	}
}
