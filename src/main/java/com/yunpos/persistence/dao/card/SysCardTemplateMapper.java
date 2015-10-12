package com.yunpos.persistence.dao.card;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.utils.jqgrid.GridRequest;

public interface SysCardTemplateMapper  extends EntityMapper<SysCardTemplate>{
    int deleteByPrimaryKey(Integer id);

    int insert(SysCardTemplate record);

    int insertSelective(SysCardTemplate record);

    SysCardTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCardTemplate record);

    int updateByPrimaryKey(SysCardTemplate record);
    
	@Select("select * from sys_card_template order by id")
	List<SysCardTemplate> findAll();

	List<SysCardTemplate> search(GridRequest gridRequest);
	
	List<SysCardTemplate> selectByParm(SysCardTemplate sysCardTemplate);
    
    
}