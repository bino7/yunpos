package com.yunpos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yunpos.Application;
import com.yunpos.model.SysOrg;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OrgServiceTest {

	@Autowired
	private SysOrgService sysOrgService;

	@Test
	public void testFindMaxOrgNo() {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgParentId(39);
		sysOrg.setOrgParentNo("0004");
		String orgNo = sysOrgService.getOrgNo(sysOrg);
		System.out.println("orgNo===" + orgNo );
	}

}
 