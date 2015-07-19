package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.Org;
import com.yunpos.persistence.dao.OrgMapper;
/**
 * 
 * 功能描述：组织服务层
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@Service
public class OrgService {
	
	@Autowired
	private OrgMapper orgMapper;
	
	 

    public List<Org> list() {
        return orgMapper.findAll();
    }
    
    public int insert(Org record) {
    	return orgMapper.insert(record);
    }
    
    public int delete(int uid) {
    	return orgMapper.deleteByPrimaryKey(uid);
    }
    
    public int update(Org record) {
    	return orgMapper.updateByPrimaryKey(record);
    }

	public Org findById(int id) {
		return orgMapper.selectByPrimaryKey(id);
	}

	public boolean exists(int id) {
		Org org = orgMapper.selectByPrimaryKey(id);
		if(org!=null) {
			return true;
		}
		return false;
	}
	
	
	

}
