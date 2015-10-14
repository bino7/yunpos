package com.yunpos.persistence.dao.card;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.card.SysMembercardTemplate;
import com.yunpos.persistence.dao.EntityMapper;

public interface SysMembercardTemplateMapper extends EntityMapper<SysMembercardTemplate>{
	@Select("select * from sys_membercard_template")
	List<SysMembercardTemplate> findAll();
}