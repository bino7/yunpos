package com.yunpos.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.yunpos.model.Page;
import com.yunpos.utils.PageDate;

/**
 * 
 * 功能描述：通用接口
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月22日
 * @author Devin_Yang 修改日期：2015年7月22日
 *
 */
public interface EntityMapper<T> extends BaseMapper {
	int deleteByPrimaryKey(Serializable id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Serializable id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
	
	List<PageDate> findByPage(Page page);
	
	List<T> findAll();

}
