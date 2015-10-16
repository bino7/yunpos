package com.yunpos.web.card;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yunpos.card.weixin.WxCardBaseInfo;
import com.yunpos.card.weixin.WxCardGroupon;
import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.payment.wxwap.utils.HttpTool;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysWechatConfigService;
import com.yunpos.service.card.SysCardTemplateService;
import com.yunpos.utils.Tools;
import com.yunpos.utils.jqgrid.GridResponse;
import com.yunpos.utils.jqgrid.GridRowResponse;
import com.yunpos.utils.jqgrid.JqGridResponse;
import com.yunpos.web.BaseController;

import net.sf.json.JSONObject;

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
public class SysCardTemplateController extends BaseController {
	
	@Autowired
	private  SysCardTemplateService sysCardTemplateService;
	
	@Autowired
	private  SysMerchantService sysMerchantService;
	
	@Autowired
	private SysWechatConfigService sysWechatConfigService;
	

	/**
	 * 卡券列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/cardTemplate",method = GET)
	public List<SysCardTemplate> list()throws ServiceException{
		return sysCardTemplateService.findAll();
		
	}
	
	/**
	 * 卡券分页查询查询
	 * @param jqGridRequest
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value="/ajax/sysCardTemplate/search",method = GET)
	public JqGridResponse<SysCardTemplate> search(SysCardTemplate sysCardTemplate)throws ServiceException{
		GridResponse<SysCardTemplate> dataResponse = sysCardTemplateService.search(sysCardTemplate);
		return new JqGridResponse<SysCardTemplate>(dataResponse);
	}
	
	/**
	 * 卡券详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = GET)
	public SysCardTemplate read(@PathVariable("id") int id) {
		return sysCardTemplateService.findById(id);
	}
	
	/**
	 * 新增卡券信息，现在卡券用户信息
	 * @param sysCardTemplate
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate", method = RequestMethod.POST)
	public GridRowResponse create(@Valid SysCardTemplate sysCardTemplate) {
		sysCardTemplate.setType(1);
		sysCardTemplate.setOrgId(getUser().getOrgId());
		sysCardTemplate.setCreatedAt(new Date());
		sysCardTemplate.setStatus(0);
		sysCardTemplateService.save(sysCardTemplate);

		return new GridRowResponse(-2);
	}

	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardTemplate
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = RequestMethod.PUT)
	public GridRowResponse update(@Valid SysCardTemplate sysCardTemplate, @PathVariable("id") int id) {
		if(!Tools.isNullOrEmpty(sysCardTemplate)){
			sysCardTemplate.setId(id);
			sysCardTemplateService.update(sysCardTemplate);
		}
		return new GridRowResponse(sysCardTemplate.getId());
	}

	
	
	/**
	 * 卡券更新 更新卡券用户信息
	 * @param sysCardTemplate
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/send/{id}", method = RequestMethod.PUT)
	public GridRowResponse sendCard(@Valid SysCardTemplate sysCardTemplate, @PathVariable("id") int id) {
		SysCardTemplate sysCardTemplateSend = sysCardTemplateService.findById(id);
		SysMerchant sysMerchant = new SysMerchant();
		sysMerchant.setOrgId(sysCardTemplateSend.getOrgId());
		
		SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(sysCardTemplateSend.getMerNo());
		String access_token = HttpTool.getAccessToken(sysWechatConfig.getAppId(),sysWechatConfig.getAppSecret());
		
		  WxCardGroupon card = new WxCardGroupon();
	        WxCardBaseInfo baseInfo = card.getBaseInfo();
	        baseInfo.setLogoUrl("123");
	        baseInfo.setDateInfoTimeRange(sysCardTemplateSend.getStartDate(), sysCardTemplateSend.getEndDate());
	        baseInfo.setBrandName("brandname");
	        baseInfo.setBindOpenid(false);
	        baseInfo.setCanGiveFriend(false);
	        baseInfo.setCanShare(true);
	        baseInfo.setCodeType(WxCardBaseInfo.CODE_TYPE_QRCODE);
	        baseInfo.setColor("Color010");
	        baseInfo.setDescription("desc");
	        baseInfo.setGetLimit(3);
	        baseInfo.setUseCustomCode(false);
	        baseInfo.setNotice("notice");
	        baseInfo.setServicePhone("phone");
	        baseInfo.addLocationIdList(123123);
	        baseInfo.addLocationIdList(5345);
	        baseInfo.setUseLimit(5);
	        baseInfo.setQuantity(10000000);
	        
	        baseInfo.setTitle(sysCardTemplateSend.getTitle());
	        baseInfo.setSub_title("小牛卡券测试");
	        baseInfo.setCustom_url_name("立即使用");
	        baseInfo.setCustom_url("http://www.qq.com");
	        baseInfo.setCustom_url_sub_title("6个汉字tips");
	        baseInfo.setPromotion_url_name("更多优惠");
	        baseInfo.setPromotion_url("http://www.qq.com");
	        baseInfo.setSource("大众点评");
	        
	        System.out.println(baseInfo.toJsonString());
	        baseInfo.setLogoUrl("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
	        ArrayList<Integer> locationIdList = new ArrayList<Integer>();
	        locationIdList.add(809809);
	        locationIdList.add(423532);
	        card.setDealDetail("小牛卡券测试！！！");
	        
	        System.out.println(locationIdList.getClass().isArray());
	        baseInfo.setLocationIdList(locationIdList);
	        System.out.println(card.toJsonString());
	        System.out.println(access_token);	
	   String requestUrl = "https://api.weixin.qq.com/card/create?access_token=" + access_token;
	   
	   JSONObject jsonObject =HttpTool.httpRequest(requestUrl,"POST", card.toJsonString());
	    if("0".equals(jsonObject.get("errcode").toString())){
	    	 sysCardTemplateSend.setWeixin_card_id(jsonObject.get("card_id").toString());
	    	 sysCardTemplateSend.setStatus(1);
	    	 sysCardTemplateService.update(sysCardTemplateSend);
	    	 
	    	 //获取二维码地址
	    	 String jsondata = "{\"action_name\":\"QR_CARD\",\"action_info\":{\"card\":{\"card_id\":\"" + jsonObject.get("card_id").toString() + "\"}}}";
	    	 
	    	 String qrcodeRequestUrl = "https://api.weixin.qq.com/card/qrcode/create?access_token=" + access_token;
	    	 JSONObject qrcodeJsonObject =HttpTool.httpRequest(qrcodeRequestUrl,"POST", jsondata);
	    	 System.out.println(jsondata);
	    	 System.out.println(qrcodeJsonObject.toString());
	    	 if("0".equals(qrcodeJsonObject.get("errcode").toString())){
	    		 String show_qrcode_url = qrcodeJsonObject.get("show_qrcode_url").toString();
	    		 sysCardTemplateSend.setWeixin_show_qrcode_url(show_qrcode_url);
	    		 sysCardTemplateService.update(sysCardTemplateSend);
	    	 }
	    }
		return new GridRowResponse(sysCardTemplate.getId());
	}
	
	/**
	 * 卡券删除
	 * @param id
	 */
	@RequestMapping(value = "/ajax/sysCardTemplate/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		sysCardTemplateService.delete(id);
	}
	
	/**
	 * 接收微信推送信息
	 * @param id
	 */
	@RequestMapping(value = "/weixin/msg", method = RequestMethod.DELETE)
	public void weixinMsg(HttpServletRequest request , HttpServletResponse response) {
		System.out.println("request = " + request);
		System.out.println("request = " + request.getParameterMap());
		System.out.println("request = " + request.getAttributeNames());
		System.out.println("request = " + request.getParameterNames());
	}
	
}
