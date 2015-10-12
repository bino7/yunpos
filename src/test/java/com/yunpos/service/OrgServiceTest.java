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
		int maxOrgNo = sysOrgService.findMaxOrgNo(sysOrg);
		String orgNo = maxOrgNo+1 +"";
		if(orgNo.length() % 4 == 1 ){
			orgNo = "000" + orgNo;
		}else if(orgNo.length() % 4 == 2 ){
			orgNo = "00" + orgNo;
		}else if(orgNo.length() % 4 == 3 ){
			orgNo = "0" + orgNo;
		} 
		
		System.out.println("orgNo===" + orgNo );
		System.out.println(maxOrgNo);
	}


}
 