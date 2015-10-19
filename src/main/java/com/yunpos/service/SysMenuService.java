package com.yunpos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
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
	public boolean hasChild(String  menuNo) {
		List<SysMenu> sysMenus = sysMenuMapper.findBymenuParentNo(menuNo);
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
	
	
	public List<SysMenu> findLevelOne(){
		return sysMenuMapper.findLevelOne();
	}


	
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
	
	
	@Override
	public void save(SysMenu sysMenu) {
		String menuNo = getMaxNo(sysMenu);
		sysMenu.setMenuNo(menuNo);
		sysMenu.setIsVisible(1);
		sysMenu.setIsLeaf(1);
		if(!Strings.isNullOrEmpty(sysMenu.getMenuParentNo())){
			SysMenu menu = sysMenuMapper.findByMenuNo(sysMenu.getMenuParentNo());
			if(menu.getIsLeaf()==1){
				menu.setIsLeaf(0);
				sysMenuMapper.updateByPrimaryKey(menu);
			}
		}
		sysMenuMapper.insert(sysMenu);
	}
	
	
	private String getMaxNo(SysMenu sysMenu){
		String nextMaxMenuNo = "";
		String maxMenuNo = "";
		String childMaxMenuNo = "";
		if(Strings.isNullOrEmpty(sysMenu.getMenuParentNo())){
			 maxMenuNo = sysMenuMapper.findMaxMenuNo();
			if(Strings.isNullOrEmpty(maxMenuNo)){
				maxMenuNo = "0000";
			}
			nextMaxMenuNo  = (Integer.parseInt(maxMenuNo)+1)+"";
			if(nextMaxMenuNo.length() % 4 == 1 ){
				nextMaxMenuNo = "000" + nextMaxMenuNo;
			}else if(nextMaxMenuNo.length() % 4 == 2 ){
				nextMaxMenuNo = "00" + nextMaxMenuNo;
			}else if(nextMaxMenuNo.length() % 4 == 3 ){
				nextMaxMenuNo = "0" + nextMaxMenuNo;
			} 
		}else{
			childMaxMenuNo = sysMenuMapper.findMaxChildMenuNo(sysMenu.getMenuParentNo());
			if(Strings.isNullOrEmpty(childMaxMenuNo)){
				childMaxMenuNo = "00000000";
			}
			nextMaxMenuNo =Integer.parseInt(childMaxMenuNo.substring(4))+1+"";
			if(nextMaxMenuNo.length() % 4 == 1 ){
				nextMaxMenuNo = "000" + nextMaxMenuNo;
			}else if(nextMaxMenuNo.length() % 4 == 2 ){
				nextMaxMenuNo = "00" + nextMaxMenuNo;
			}else if(nextMaxMenuNo.length() % 4 == 3 ){
				nextMaxMenuNo = "0" + nextMaxMenuNo;
			} 
			nextMaxMenuNo = sysMenu.getMenuParentNo()+nextMaxMenuNo;
		}
		return nextMaxMenuNo;
	}
	
	
	@Override
	public void delete(Integer id) {
		SysMenu sysMenu =  sysMenuMapper.selectByPrimaryKey(id);
		if(!Strings.isNullOrEmpty(sysMenu.getMenuParentNo())){
			List<SysMenu > list = sysMenuMapper.findBymenuParentNo(sysMenu.getMenuParentNo());
			if(list!=null && list.size()>1){
				sysMenuMapper.deleteByPrimaryKey(id);
			}else{
				SysMenu temp =  sysMenuMapper.findByMenuNo(sysMenu.getMenuParentNo());
				temp.setIsLeaf(1);
				sysMenuMapper.updateByPrimaryKey(temp);
				sysMenuMapper.deleteByPrimaryKey(id);
			}
		}
		if(hasChild(sysMenu.getMenuNo())){
			
		}
		super.delete(id);
	}
	

}
