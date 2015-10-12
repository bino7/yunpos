package com.yunpos.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.model.SysAgentMerchant;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AgentMerchantServiceTest {

	@Autowired
	private SysAgentMerchantService sysAgentMerchantService;

	@Test
	public void testFindMaxAgentMerchantNo() {
		SysAgentMerchant sysAgentMerchant = new SysAgentMerchant();
		sysAgentMerchant.setBaseUserId(1);
		sysAgentMerchant.setStatus(1);
		List<SysAgentMerchant> list = sysAgentMerchantService.findByParms(sysAgentMerchant);
		System.out.println("AgentMerchantNo===" + list.size() );
	}


}
 