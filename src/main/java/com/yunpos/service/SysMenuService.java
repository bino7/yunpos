package com.yunpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMenu;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMenuMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
@Service
public class SysMenuService extends EntityService<SysMenu>{
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public EntityMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}
	
	public List<SysMenu> findAll(){
		return sysMenuMapper.findAll();
	}

	public List<SysMenu> findListByIds(Object[] array) {
		return sysMenuMapper.findListByIds(array);
	}

	public GridResponse<SysMenu> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysMenu> response = new GridResponse<SysMenu>();
		List<SysMenu> sysMenus =  sysMenuMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysMenus);
		response.setTotalRowCount(sysMenus.size());
		return response;
	}
	
	//判断菜单是否有子菜单
	public boolean hasChild(int id) {
		List<SysMenu> sysMenus  = sysMenuMapper.findBymenuParentNo(id);
		if(sysMenus!=null && sysMenus.size()>0){
			return true;
		}
		return false;
	}



  

}
