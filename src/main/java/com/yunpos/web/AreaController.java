package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.model.Area;
import com.yunpos.model.SysAgentMerchant;
import com.yunpos.service.AreaService;
import com.yunpos.utils.Tools;

/**
 * 
 * 功能描述：地址控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月2日
 * @author tiger_lin 修改日期：2015年9月2日
 *
 */
@RestController
public class AreaController {
	
	@Autowired
	private  AreaService areaService;
	
	
	/**
	 * 地址详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/area/{id}", method = GET)
	public Area findArea(@PathVariable("id") int id) {
		return areaService.findById(Long.parseLong(id + ""));
	}
	
	/**
	 * 下级区域
	 * @param id
	 * @return
	 */	
	@RequestMapping(value = "/ajax/findChildArea/{id}", method = GET)
	public List<Area> findChildArea(@Valid Area area, @PathVariable("id") int id) {
		area.setParent_id(Long.parseLong(id + ""));
		return areaService.findByParms(area);
	}
}
