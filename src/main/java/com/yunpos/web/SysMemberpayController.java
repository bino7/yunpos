package com.yunpos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysMemberpay;
import com.yunpos.model.SysToken;
import com.yunpos.service.SysMemberpayService;
import com.yunpos.service.SysTokenService;
import com.yunpos.utils.jqgrid.GridRequest;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridRequest;
import com.yunpos.utils.jqgrid.JqGridResponse;

/**
 * 
 * 功能描述：会员信息控制器
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
public class SysMemberpayController extends BaseController{
	@Autowired
	private SysMemberpayService sysMemberpayService;
	
	@Autowired
	private SysTokenService SysTokenService;
	
	@RequestMapping(value="/ajax/memberpay",method = GET)
	public List<SysMemberpay> list()throws ServiceException{
		return sysMemberpayService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/memberpay/search",method = GET)
	public JqGridResponse<SysMemberpay> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysMemberpay> dataResponse = sysMemberpayService.search(gridRequest);
		return new JqGridResponse<SysMemberpay>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/memberpay/{id}", method = GET)
	public SysMemberpay read(@PathVariable("id") int id) {
		return sysMemberpayService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/memberpay", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMemberpay sysMemberpay) {
		sysMemberpayService.save(sysMemberpay);
		return new GridRowResponse(sysMemberpay.getId());
	}

	@RequestMapping(value = "/ajax/memberpay/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMemberpay sysMemberpay, @PathVariable("id") int id) {
		sysMemberpay.setId(id);
		sysMemberpayService.update(sysMemberpay);
		return new GridRowResponse(sysMemberpay.getId());
	}

	@RequestMapping(value = "/ajax/memberpay/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMemberpayService.delete(id);
	}
	
	@RequestMapping(value="/partner/memberpay",method = GET)
	public String getMemberpayByTime(@RequestParam("access_token") String access_token, HttpServletRequest request)
			throws JsonProcessingException {
		Map<String,Object>  result= new HashMap<String,Object>();
		//进行Token合法性检查，暂时仅判断是否为空
		if (access_token.length() == 0) {
			result.put("errcode", 50002);
			result.put("errmsg", "无效的access_token");
		}else {
			//通过access_token获取合作伙伴的简写，消费记录来源对应该值
			SysToken token = SysTokenService.getTokenByAccessToken(access_token);
			if (token == null) {
				result.put("errcode", 50002);
				result.put("errmsg", "无效的access_token");			
			}else {
				String source = token.getAbbre();
			    String payorderId = request.getParameter("payorderId");
				if (payorderId != null && payorderId.length() > 0) {// 充值订单ID不为空，以充值订单ID为 主
					SysMemberpay sysMemberpay = sysMemberpayService.getMemberpayByorderId(source, payorderId);
					result.put("errcode", 200);
					result.put("errmsg", "");
					if (sysMemberpay !=null) {
						result.put("appid_userId", sysMemberpay.getAppid_userId());//合作伙伴中的会员ID
						result.put("price", sysMemberpay.getPrice());//充值金额
						result.put("payType", sysMemberpay.getPayType());//充值渠道类型
						Date createTime = sysMemberpay.getCreatedAt();
						if (createTime !=null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							result.put("createTime",sdf.format(createTime));//充值时间
						}
						result.put("payStatus", sysMemberpay.getPayStatus());//交易状态		
					}
				}else {//以会员Id为主
					String appid_userId = request.getParameter("appid_userId");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date startTime = sdf.parse(request.getParameter("bTime"));
						Date endTime = sdf.parse(request.getParameter("eTime"));
						List<SysMemberpay> sysMemberpays = sysMemberpayService.getMemberpayByuserIdAndTime(appid_userId, startTime, endTime);
						result.put("errcode", 200);
						result.put("errmsg", "");
						List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
						for (SysMemberpay sysMemberpay : sysMemberpays) {
							Map<String,Object>  tmp= new HashMap<String,Object>();
							tmp.put("payorderId", sysMemberpay.getPayorderId());//充值订单号
							tmp.put("price", sysMemberpay.getPrice());//充值金额
							tmp.put("payType", sysMemberpay.getPayType());//充值渠道类型
							Date createTime = sysMemberpay.getCreatedAt();
							if (createTime !=null) {
								tmp.put("createTime",sdf.format(createTime));//充值时间
							}
							tmp.put("payStatus", sysMemberpay.getPayStatus());//交易状态	
							items.add(tmp);	
						}
						if (items.size() > 0) {
							result.put("items", items);
						}	
					}  catch (ParseException e) {// 时间格式错误
						e.printStackTrace();
						e.printStackTrace();
						result.put("errcode", 50003);
						result.put("errmsg", "无效的日期格式");
					}
				}
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(result);	
	}
	
	@RequestMapping(value = "/ajax/memberpay/userId", method = RequestMethod.GET)
	public JqGridResponse<SysMemberpay> getMemberpayByUserId(@RequestParam("openId") String openId){
		GridResponse<SysMemberpay> sysMemberpays = sysMemberpayService.findByUserId(openId);
		return new JqGridResponse<SysMemberpay>(sysMemberpays);	
	}

}
