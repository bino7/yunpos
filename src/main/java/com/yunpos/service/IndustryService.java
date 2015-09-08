package com.yunpos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yunpos.model.Industry;
import com.yunpos.persistence.dao.EntityMapper;

@Service
public class IndustryService extends EntityService<Industry>{

	@Override
	public EntityMapper<Industry> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * 默认行业数据
	 * @return
	 */
	public List<Industry> findAll() {
		List<Industry> list = new ArrayList<Industry>();
		Industry industry1 =  new Industry();
		industry1.setId(1);
		industry1.setIndustryType("餐饮");

		Industry industry2 =  new Industry();
		industry2.setId(2);
		industry2.setIndustryType("KTV");
		
		Industry industry3 =  new Industry();
		industry3.setId(3);
		industry3.setIndustryType("美容美发");
		
		Industry industry4 =  new Industry();
		industry4.setId(4);
		industry4.setIndustryType("酒店");
		
		list.add(industry1);
		list.add(industry2);
		list.add(industry3);
		list.add(industry4);
		
		return list;
	}
	
	/**
	 * 查询行业数据
	 */
	public Industry findById(Integer id) {
		List<Industry> list = this.findAll();
		Industry industryReturn = null;
		for(Industry industry : list){
			if(industry.getId() == id){
				industryReturn = industry;
			}
		}
		return industryReturn;
	}
}
