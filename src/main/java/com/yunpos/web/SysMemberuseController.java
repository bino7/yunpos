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
import com.yunpos.model.SysMemberuse;
import com.yunpos.model.SysToken;
import com.yunpos.model.SysTransaction;
import com.yunpos.service.SysMemberuseService;
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
public class SysMemberuseController extends BaseController{
	@Autowired
	private SysMemberuseService sysMemberuseService;
	
	@Autowired
	private SysTokenService SysTokenService;
	
	@RequestMapping(value="/ajax/memberuse",method = GET)
	public List<SysMemberuse> list()throws ServiceException{
		return sysMemberuseService.findAll();
		
	}
	
	@RequestMapping(value="/ajax/memberuse/search",method = GET)
	public JqGridResponse<SysMemberuse> search(JqGridRequest jqGridRequest)throws ServiceException{
		GridRequest gridRequest = jqGridRequest.createDataRequest();
		GridResponse<SysMemberuse> dataResponse = sysMemberuseService.search(gridRequest);
		return new JqGridResponse<SysMemberuse>(dataResponse);
	}
	
	@RequestMapping(value = "/ajax/memberuse/{id}", method = GET)
	public SysMemberuse read(@PathVariable("id") int id) {
		return sysMemberuseService.findById(id);
	}
	
	@RequestMapping(value = "/ajax/memberuse", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysMemberuse sysMemberuse) {
		sysMemberuseService.save(sysMemberuse);
		return new GridRowResponse(sysMemberuse.getId());
	}

	@RequestMapping(value = "/ajax/memberuse/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysMemberuse sysMemberuse, @PathVariable("id") int id) {
		sysMemberuse.setId(id);
		sysMemberuseService.update(sysMemberuse);
		return new GridRowResponse(sysMemberuse.getId());
	}

	@RequestMapping(value = "/ajax/memberuse/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysMemberuseService.delete(id);
	}
	
	@RequestMapping(value="/partner/memberuse",method = GET)
	public String getMemberuseByTime(@RequestParam("access_token") String access_token, HttpServletRequest request)
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
			    String appid_useId = request.getParameter("appid_useId");
				if (appid_useId != null && appid_useId.length() > 0) {// 消费记录ID不为空，以消费记录ID为主
					SysMemberuse sysMemberuse = sysMemberuseService.getMemberUseByuseId(source, appid_useId);
					result.put("errcode", 200);
					result.put("errmsg", "");
					if (sysMemberuse !=null) {
						result.put("appid_userId", sysMemberuse.getAppid_userId());//合作伙伴中的会员ID
						result.put("expense", sysMemberuse.getExpense());//消费金额
						result.put("useType", sysMemberuse.getUseType());//消费类型
						result.put("itemType", sysMemberuse.getItemType());//消费媒介类型
						result.put("itemSerialNo", sysMemberuse.getItemSerialNo());//消费媒介序列号
						result.put("score", sysMemberuse.getScore());//消费积分
						Date createTime = sysMemberuse.getCreatedAt();
						if (createTime !=null) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							result.put("createTime",sdf.format(createTime));//使用时间	
						}	
					}
				}else {//以会员Id为主
					String appid_userId = request.getParameter("appid_userId");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date startTime = sdf.parse(request.getParameter("bTime"));
						Date endTime = sdf.parse(request.getParameter("eTime"));
						List<SysMemberuse> sysMemberuses = sysMemberuseService.getMemberUseByuserIdAndTime(appid_userId, startTime, endTime);
						result.put("errcode", 200);
						result.put("errmsg", "");
						List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
						for (SysMemberuse sysMemberuse : sysMemberuses) {
							Map<String,Object>  tmp= new HashMap<String,Object>();
							tmp.put("expense", sysMemberuse.getExpense());//消费金额
							tmp.put("useType", sysMemberuse.getUseType());//消费类型
							tmp.put("itemType", sysMemberuse.getItemType());//消费媒介类型
							tmp.put("itemSerialNo", sysMemberuse.getItemSerialNo());//消费媒介序列号
							tmp.put("score", sysMemberuse.getScore());//消费积分
							tmp.put("appid_useId", sysMemberuse.getAppid_useId());//合作伙伴中的消费记录ID
							Date createTime = sysMemberuse.getCreatedAt();
							if (createTime !=null) {
								tmp.put("createTime",sdf.format(createTime));//使用时间	
							}
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
	
	@RequestMapping(value = "/ajax/memberuse/userId", method = RequestMethod.GET)
	public JqGridResponse<SysMemberuse> getMemberpayByUserId(@RequestParam("openId") String openId){
		GridResponse<SysMemberuse> sysMemberuses = sysMemberuseService.findByUserId(openId);
		return new JqGridResponse<SysMemberuse>(sysMemberuses);	
	}
}
