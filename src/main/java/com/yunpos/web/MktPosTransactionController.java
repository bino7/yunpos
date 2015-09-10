package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.exception.ServiceException;
import com.yunpos.model.MktPosTransaction;
import com.yunpos.service.MktPosTransactionService;
import com.yunpos.utils.Tools;

/**
 * 
 * 功能描述：订单流水控制器
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
public class MktPosTransactionController {
	@Autowired
	private MktPosTransactionService mktPosTransactionService;
	

	
	@RequestMapping(value="/ajax/mktPosTransaction/search",method = GET)
	public List<MktPosTransaction> search(MktPosTransaction mktPosTransaction)throws ServiceException{
		if(!Tools.isNullOrEmpty(mktPosTransaction)){
			if(!Tools.isNullOrEmpty(mktPosTransaction.getPageNumber())){
				
			}
		}
		return mktPosTransactionService.findByParms(mktPosTransaction);
	}
	

}
