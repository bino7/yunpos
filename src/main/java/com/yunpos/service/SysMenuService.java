package com.yunpos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpos.model.SysMenu;
import com.yunpos.persistence.dao.EntityMapper;
import com.yunpos.persistence.dao.SysMenuMapper;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;

@Service
public class SysMenuService extends EntityService<SysMenu> {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public EntityMapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}

	public List<SysMenu> findAll() {
		return sysMenuMapper.findAll();
	}

	public List<SysMenu> findListByIds(Object[] array) {
		return sysMenuMapper.findListByIds(array);
	}

	public GridResponse<SysMenu> findPageUsers(GridRequest gridRequest) {
		GridResponse<SysMenu> response = new GridResponse<SysMenu>();
		List<SysMenu> sysMenus = sysMenuMapper.findAll();
		response.setPageNumber(1);
		response.setPageSize(10);
		response.setRows(sysMenus);
		response.setTotalRowCount(sysMenus.size());
		return response;
	}

	// 判断菜单是否有子菜单
	public boolean hasChild(int id) {
		List<SysMenu> sysMenus = sysMenuMapper.findBymenuParentNo(id);
		if (sysMenus != null && sysMenus.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean existMenuName(String menuName) {
		List<SysMenu> list = sysMenuMapper.findByMenuName(menuName);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public List<SysMenu> findChildByParentId(int menuNo) {
		return sysMenuMapper.findChildByParentId(menuNo);
	}

	// public List<MenuDTO> jsonMenu() {
	// List<MenuDTO> returnList = new ArrayList<>();
	// try {
	// List<SysMenu> list = sysMenuMapper.findAll();
	// for(SysMenu menu:list){
	// MenuDTO menuDto = new MenuDTO(menu);
	// List<SysMenu> childs = sysMenuMapper.findBymenuParentNo(menu.getId());
	// if(childs!=null && childs.size()>0){
	// menuDto.setChilds(childs);
	// list.re
	// }
	// returnList.add(menuDto);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return returnList;
	// }
	
	/**
	 * 获取菜单json
	 * @return
	 */
	public List<SysMenu> getJsonMenu() {
		List<SysMenu> level1 = sysMenuMapper.findLevelOne();
		List<SysMenu> resultList = new ArrayList<SysMenu>();
		for(SysMenu menu:level1){
			SysMenu node = jsonMenu(menu.getId());
			resultList.add(node);
		}
		return resultList;
	}

	private SysMenu jsonMenu(int cid) {
		List<SysMenu> resultList = new ArrayList<SysMenu>();
			SysMenu node = sysMenuMapper.selectByPrimaryKey(cid);
			// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
			List<SysMenu> childTreeNodes = sysMenuMapper.findChildByParentId(cid);
			// 遍历子节点
			for (SysMenu child : childTreeNodes) {
				SysMenu n = jsonMenu(child.getId()); // 递归
				node.getNodes().add(n);
			}
		return node;
	}

}
