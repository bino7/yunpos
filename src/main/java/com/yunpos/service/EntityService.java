package com.yunpos.service;

import java.util.List;

import com.yunpos.model.Page;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.utils.PageDate;

/**
 * 
 * 功能描述：服务层通用接口,开发过程中通用的方法可以在此添加,特殊的方法放到具体的service里面实现
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月22日
 * @author Devin_Yang 修改日期：2015年7月22日
 *
 */
public abstract class EntityService<T> extends BaseService {
	public abstract EntityMapper<T> getMapper();

	public T findById(Integer id) {
		return getMapper().selectByPrimaryKey(id);
	}

	public List<PageDate> findByPage(Page page) {
		return getMapper().findByPage(page);
	}

	public void delete(Integer id) {
		getMapper().deleteByPrimaryKey(id);
	}

	public void save(T entity) {
		insert(entity);
	}

	// public void save(T entity) {
	// if (entity.getId() != null && entity.getId() > 0) {
	// update(entity);
	// } else {
	// insert(entity);
	// }
	// }

	public void insert(T entity) {
		getMapper().insert(entity);
	}

	public void update(T entity) {
		getMapper().updateByPrimaryKeySelective(entity);
	}

	public boolean exists(Integer id) {
		if (getMapper().selectByPrimaryKey(id) != null) {
			return true;
		}
		return false;
	}

	// 批量删除
	public void batchDeleteByIds(Object[] ids) {
		getMapper().batchDeleteByIds(ids);
	}
}
