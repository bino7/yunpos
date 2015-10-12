package com.yunpos.web.card;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.card.SysCardCoupon;
import com.yunpos.service.SysOrgService;
import com.yunpos.service.SysUserService;
import com.yunpos.service.card.SysCardCouponService;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;
import com.yunpos.web.BaseController;

/**
 * 
 * 功能描述：卡券信息控制器 
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月28日
 * @author Devin_Yang 修改日期：2015年8月28日
 *
 */
@RestController
public class SysCardCouponController extends BaseController {
	
	@Autowired
	private  SysCardCouponService sysCardCouponService;
	
	/**
	 * 卡券列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/cardCoupon",method = GET)
	public List<SysCardCoupon> list()throws ServiceException{
		return sysCardCouponService.findAll();
		
	}
	
	/**
	 * 卡券分页查询查询
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/sysCardCoupon/search",method = GET)
	public JqGridResponse<SysCardCoupon> search(SysCardCoupon sysCardCoupon)throws ServiceException{
		GridResponse<SysCardCoupon> dataResponse = sysCardCouponService.search(sysCardCoupon);
		return new JqGridResponse<SysCardCoupon>(dataResponse);
	}
	
	/**
	 * 卡券详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardCoupon/{id}", method = GET)
	public SysCardCoupon read(@PathVariable("id") int id) {
		return sysCardCouponService.findById(id);
	}
	
	/**
	 * 新增卡券信息，现在卡券用户信息
	 * @param sysCardCoupon
	 * @return
	 */
	@RequestMapping(value = "/ajax/cardCoupon", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysCardCoupon sysCardCoupon) {
		sysCardCouponService.save(sysCardCoupon);

		return new GridRowResponse(-2);
	}

	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardCoupon
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardCoupon/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysCardCoupon sysCardCoupon, @PathVariable("id") int id) {
		if(!Tools.isNullOrEmpty(sysCardCoupon)){
			sysCardCoupon.setId(id);
			sysCardCouponService.update(sysCardCoupon);
		}
		return new GridRowResponse(sysCardCoupon.getId());
	}

	
	
	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardCoupon
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardCoupon/updateStatus/{id}", method = RequestMethod.PUT)
	public GridRowResponse updateStatus(@Valid SysCardCoupon sysCardCoupon, @PathVariable("id") int id) {
		SysCardCoupon sCardCoupon = sysCardCouponService.findById(id);
		sCardCoupon.setStatus(sysCardCoupon.getStatus());
		 sysCardCouponService.update(sCardCoupon);
		return new GridRowResponse(sCardCoupon.getId());
	}
	
	/**
	 * 卡券删除
	 * @param id
	 */
	@RequestMapping(value = "/ajax/sysCardCoupon/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysCardCouponService.delete(id);
	}
	
	
}
