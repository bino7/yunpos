package com.yunpos.persistence.dao.card;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunpos.model.card.SysCardBaseinfo;
import com.yunpos.persistence.dao.EntityMapper;

public interface SysCardBaseinfoMapper extends EntityMapper<SysCardBaseinfo>{
	@Select("select * from sys_card_baseinfo")
	List<SysCardBaseinfo> findAll();
}