package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysMenu;
import com.yunpos.service.SysMenuService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：按钮资源控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月17日
 * @author Devin_Yang 修改日期：2015年7月17日
 *
 */
@RestController
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping(value = "/ajax/menu/{id}", method = GET)
	public SysMenu read(@PathVariable("id") int id) {
		return sysMenuService.findById(id);
	}
	

	
	@RequestMapping(value="/ajax/menu",method = GET)
	public JqGridResponse<SysMenu> list(JqGridRequest jqGridRequest)  throws ServiceException {
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysMenu> dataResponse = sysMenuService.findPageUsers(gridRequest);
		return new JqGridResponse<SysMenu>(dataResponse);
	}
	
	/**
	 * 获取菜单嵌套json结构
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/ajax/menu/json", method = GET)
	public Object json()   throws ServiceException {
		return sysMenuService.getJsonMenu();
	}

	
	@RequestMapping(value = "/ajax/menu/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMenu sysMenu, @PathVariable("id") int id) {
		sysMenu.setId(id);
		sysMenuService.update(sysMenu);
		return new GridRowResponse(sysMenu.getId());
	}
	
	@RequestMapping(value = "/ajax/menu", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
		return new GridRowResponse(sysMenu.getId());
	}

	@RequestMapping(value = "/ajax/menu/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMenuService.delete(id);
	}
	
	@RequestMapping(value = "/ajax/menu/select", method =RequestMethod.GET )
	public List<SysMenu> getRoleSelectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysMenu> roleList = sysMenuService.findAll();
		return roleList;
	}
	
	/**
	 * 判断是否有子节点
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ajax/menu/{id}/haschild",method = RequestMethod.GET)
	@ResponseBody
	public Object hasChild(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id)throws Exception{
		return sysMenuService.hasChild(id);
	}
	
	//判断菜单名是否存在
	@RequestMapping(value = "/ajax/menu/exist/{menuName}", method = RequestMethod.GET)
	public Object exist(HttpServletRequest request, @PathVariable("menuName") String menuName) throws Exception {
		return sysMenuService.existMenuName(menuName);
	}
	
	//获取指定菜单的直接下级菜单列表
	@RequestMapping(value = "/ajax/menu/getchild/{menuNo}", method = RequestMethod.GET)
	public Object getChild(HttpServletRequest request, @PathVariable("menuNo") String menuNo) throws Exception {
		return sysMenuService.findChildByParentId(Integer.valueOf(menuNo));
	}
	
	
}
