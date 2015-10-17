package com.yunpos.web.card;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.google.common.base.Strings;
import com.yunpos.card.weixin.WxCardBaseInfo;
import com.yunpos.card.weixin.WxCardGroupon;
import com.yunpos.exception.ServiceException;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysWechatConfigWithBLOBs;
import com.yunpos.model.card.SysCardCoupon;
import com.yunpos.model.card.SysCardTemplate;
import com.yunpos.payment.wxwap.utils.HttpTool;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysWechatConfigService;
import com.yunpos.service.card.SysCardCouponService;
import com.yunpos.service.card.SysCardTemplateService;
import com.yunpos.utils.ConfigContants;
import com.yunpos.utils.Tools;
import com.yunpos.utils.XMLUtil;
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
	
	@Autowired
	private  SysCardCouponService sysCardCouponService;
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
	public GridRowResponse create(HttpServletRequest request , @Valid SysCardTemplate sysCardTemplate) {
		SysMerchant  sysMerchant = new SysMerchant();
		sysMerchant.setOrgId(this.getUser().getOrgId());
		List<SysMerchant> sysMerchantList = sysMerchantService.findByParms(sysMerchant);
		sysMerchant = sysMerchantList.get(0);
		
		SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(sysMerchant.getSerialNo());
		String access_token = HttpTool.getAccessToken(sysWechatConfig.getAppId(),sysWechatConfig.getAppSecret());
		
		//*****************************************多媒体上传图片 begin***************************************************
		WritableResource resource = new FileSystemResource(new File(sysCardTemplate.getLogo().replace(ConfigContants.IMAGEURL, ConfigContants.IMAGEPATH)));
		MultiValueMap data = new LinkedMultiValueMap();
		data.add("buffer", resource);
		
		String urlString = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+access_token;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(urlString, data, String.class);
		JSONObject jsonObject = JSONObject.fromObject(result);
		System.out.println(result);
		sysCardTemplate.setLogo(jsonObject.getString("url").toString());
		sysCardTemplate.setType(1);
		sysCardTemplate.setOrgId(getUser().getOrgId());
		sysCardTemplate.setMerNo(sysMerchant.getSerialNo());
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
		
		SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(sysCardTemplateSend.getMerNo());
		String access_token = HttpTool.getAccessToken(sysWechatConfig.getAppId(),sysWechatConfig.getAppSecret());
		
		SysMerchant  sysMerchant = new SysMerchant();
		sysMerchant.setOrgId(sysCardTemplateSend.getOrgId());
		List<SysMerchant> sysMerchantList = sysMerchantService.findByParms(sysMerchant);
		sysMerchant = sysMerchantList.get(0);
		
		  WxCardGroupon card = new WxCardGroupon();
	        WxCardBaseInfo baseInfo = card.getBaseInfo();
	        baseInfo.setLogoUrl("123");
	        baseInfo.setDateInfoTimeRange(sysCardTemplateSend.getStartDate(), sysCardTemplateSend.getEndDate());
	        baseInfo.setBrandName("brandname");
	        baseInfo.setBindOpenid(false);
	        baseInfo.setCanGiveFriend(false);
	        baseInfo.setCanShare(true);
	        baseInfo.setCodeType(WxCardBaseInfo.CODE_TYPE_BARCODE);   //卡券展示类型，条形码、二维码
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
	        baseInfo.setSub_title(sysCardTemplateSend.getSubtitle());
	        baseInfo.setCustom_url_name(sysCardTemplateSend.getUrltitle());
	        baseInfo.setCustom_url(sysCardTemplateSend.getUrlcontent());
	        baseInfo.setCustom_url_sub_title(sysCardTemplateSend.getUrldesc());
//	        baseInfo.setPromotion_url_name("更多优惠");
//	        baseInfo.setPromotion_url("http://www.qq.com");
	        baseInfo.setSource(sysMerchant.getMerchantName());
	        
	        System.out.println(baseInfo.toJsonString());
	        baseInfo.setLogoUrl(sysCardTemplateSend.getLogo());
	        ArrayList<Integer> locationIdList = new ArrayList<Integer>();
	        locationIdList.add(809809);
	        locationIdList.add(423532);
	        card.setDealDetail(sysMerchant.getMerchantName());
	        
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
	@RequestMapping(value = "/weixin/msg")
	public void weixinMsg(HttpServletRequest request , HttpServletResponse response) {
		System.out.println("request = " + request);
		try {
			if(!Strings.isNullOrEmpty(request.getParameter("echostr"))){
				response.getWriter().println(request.getParameter("echostr").toString());
				System.out.println("request = " + request.getParameterMap());
				System.out.println("request = " + request.getParameter("signature"));
				System.out.println("request = " + request.getParameter("echostr"));
				System.out.println("request = " + request.getParameter("timestamp"));
				System.out.println("request = " + request.getParameter("nonce"));
				System.out.println("request = " + request.getParameterNames());
			}
			
			java.io.BufferedReader bis = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()));
		      
			 String line = null;
			 String result = "";
			   
			 try {
			  while ((line = bis.readLine()) != null) {
			      result += line+"\r\n";
			  }
			 } catch (Exception e) {
			  e.printStackTrace();
			 } finally {
			  bis.close();
			 }
			 System.out.println("result = " + result);
			 if (!Strings.isNullOrEmpty(result)) {
				 Map<String, String> resultMap = new HashMap<String, String>();
				 XMLUtil.parse(result, resultMap);

				 if(!Tools.isNullOrEmpty(resultMap.get("Event"))){
					 String Event = resultMap.get("Event").toString();
					 
					 System.out.println("resultMap = " + resultMap.size());
					 
					 System.out.println("ToUserName = " + resultMap.get("ToUserName"));
					 System.out.println("FromUserName = " + resultMap.get("FromUserName"));
					 System.out.println("CreateTime = " + resultMap.get("CreateTime"));
					 System.out.println("MsgType = " + resultMap.get("MsgType"));
					 System.out.println("Event = " + resultMap.get("Event"));
					 System.out.println("CardId = " + resultMap.get("CardId"));
					
					 if(Event.equals("card_pass_check") || Event.equals("card_not_pass_check") ){//卡券通过审核 , 卡券未通过审核
						 
						 SysCardTemplate sysCardTemplate = new SysCardTemplate();
						 sysCardTemplate.setWeixin_card_id(resultMap.get("CardId").toString());
						 List<SysCardTemplate> sysCardTemplateSendList = sysCardTemplateService.findByParms(sysCardTemplate);
						 sysCardTemplate = sysCardTemplateSendList.get(0);
						 if(Event.equals("card_pass_check") ){
							 sysCardTemplate.setStatus(3);
						 }else if(Event.equals("card_not_pass_check") ){
							 sysCardTemplate.setStatus(2);
						 }
						 sysCardTemplateService.update(sysCardTemplate);
						 System.out.println("卡券通过审核,未通过审核");
					 }else {
						 System.out.println("UserCardCode = " + resultMap.get("UserCardCode"));
						
						 if(Event.equals("user_get_card")){//用户领取卡券
							 SysCardTemplate sysCardTemplate = new SysCardTemplate();
							 sysCardTemplate.setWeixin_card_id(resultMap.get("CardId").toString());
							 List<SysCardTemplate> sysCardTemplateSendList = sysCardTemplateService.findByParms(sysCardTemplate);
							 sysCardTemplate = sysCardTemplateSendList.get(0);
							 SysCardCoupon sysCardCoupon = new SysCardCoupon();
							 sysCardCoupon.setAppid_cardId(sysCardTemplateSendList.get(0).getWeixin_card_id());
							 sysCardCoupon.setSn(resultMap.get("UserCardCode").toString());
							 sysCardCoupon.setStatus(new Byte("0"));
							 sysCardCoupon.setTitle(sysCardTemplate.getTitle());
							 sysCardCoupon.setSource("微信");
							 sysCardCoupon.setAppid_userId(resultMap.get("FromUserName").toString());
							 sysCardCoupon.setCreatedAt(new Date());
							 sysCardCoupon.setStartTime(sysCardTemplate.getStartDate());
							 sysCardCoupon.setEndTime(sysCardTemplate.getEndDate());
							 sysCardCoupon.setType(new Byte("3"));
							 sysCardCouponService.insert(sysCardCoupon);
							 System.out.println("IsGiveByFriend = " + resultMap.get("IsGiveByFriend"));
							 System.out.println("OuterId = " + resultMap.get("OuterId"));
							 System.out.println("FriendUserName = " + resultMap.get("FriendUserName"));
						 }else if(Event.equals("user_consume_card")){//核销事件
							 System.out.println("核销事件");
							 SysCardCoupon sysCardCoupon = new SysCardCoupon();
							 sysCardCoupon.setSn(resultMap.get("UserCardCode").toString());
							 List<SysCardCoupon> sysCardCouponList = sysCardCouponService.findByParms(sysCardCoupon); 
							 SysCardCoupon sysCardCouponConsume = sysCardCouponList.get(0);
							 sysCardCoupon.setStatus(new Byte("1"));
							 sysCardCouponService.update(sysCardCouponConsume);;
							 
							 System.out.println("ConsumeSource = " + resultMap.get("ConsumeSource"));
							 System.out.println("OutTradeNo = " + resultMap.get("OutTradeNo"));
							 System.out.println("TransId = " + resultMap.get("TransId"));
							 System.out.println("LocationName = " + resultMap.get("LocationName"));
							 System.out.println("StaffOpenId = " + resultMap.get("StaffOpenId"));
							 
						 } else if(Event.equals("user_del_card")){//用户删除卡券
							 SysCardCoupon sysCardCoupon = new SysCardCoupon();
							 sysCardCoupon.setSn(resultMap.get("UserCardCode").toString());
							 List<SysCardCoupon> sysCardCouponList = sysCardCouponService.findByParms(sysCardCoupon); 
							 SysCardCoupon sysCardCouponConsume = sysCardCouponList.get(0);
							 sysCardCoupon.setStatus(new Byte("3"));
							 sysCardCouponService.update(sysCardCouponConsume);;
							 
						 }else if(Event.equals("user_view_card")){//进入会员卡事件推送
							 System.out.println("进入会员卡事件推送");
							 
						 }
					 }
				 }
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 卡券核销
	 * @param id
	 */
	@RequestMapping(value = "/ajax/cardConsume", method = RequestMethod.POST)
	public void cardConsume(HttpServletRequest request , HttpServletResponse response) {
		
		String merNo = request.getParameter("merNo");
		String cardCode = request.getParameter("cardCode");
//		String card_id = "px6wPuHrD4dkjl1JaktPhRBHSS9w";
		SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(merNo);
		String access_token = HttpTool.getAccessToken(sysWechatConfig.getAppId(),sysWechatConfig.getAppSecret());
		
		 SysCardCoupon sysCardCoupon = new SysCardCoupon();
		 sysCardCoupon.setSn(cardCode);
		 List<SysCardCoupon> sysCardCouponList = sysCardCouponService.findByParms(sysCardCoupon); 
		 SysCardCoupon sysCardCouponConsume = sysCardCouponList.get(0);
		
		String card_id = sysCardCouponConsume.getAppid_cardId();
		
		String cardCodeCheckRequestUrl = "https://api.weixin.qq.com/card/code/get?access_token=" + access_token;
		String cardCodeCheckJson = "{\"card_id\" : \"" + card_id + "\",\"code\" : \"" + cardCode + "\",\"check_consume\" : true}";   
		JSONObject jsonCheckObject =HttpTool.httpRequest(cardCodeCheckRequestUrl,"POST", cardCodeCheckJson);
		System.out.println(jsonCheckObject);
		String returnJson = "";
		 if("0".equals(jsonCheckObject.get("errcode").toString())){
			String cardCodeRequestUrl = "https://api.weixin.qq.com/card/code/consume?access_token=" + access_token;
			String cardCodeJson = "{\"code\" : \"" + cardCode + "\",\"card_id\" : \"" + card_id + "\"}";   
			JSONObject jsonObject =HttpTool.httpRequest(cardCodeRequestUrl,"POST", cardCodeJson);
			
			 sysCardCouponConsume.setStatus(new Byte("1"));
			 sysCardCouponService.update(sysCardCouponConsume);;
			
			returnJson = "{\"status\" : \"success\" }";
			System.out.println(jsonObject);
		 }else {
			 System.out.println(jsonCheckObject);
			 returnJson = "{\"status\" : \"fail\" , \"errmsg\": \"" + jsonCheckObject.get("errmsg").toString() + "\" }";
		 }
		 try {
			response.getWriter().print(returnJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 微信上传logo
	 * @param id
	 */
	@RequestMapping(value = "/weixin/uplodLogo")
	public void uplodLogo(HttpServletRequest request , HttpServletResponse response) {
		System.out.println("request = " + request);
		
		SysMerchant  sysMerchant = new SysMerchant();
		sysMerchant.setOrgId(this.getUser().getOrgId());
		List<SysMerchant> sysMerchantList = sysMerchantService.findByParms(sysMerchant);
		sysMerchant = sysMerchantList.get(0);
		SysWechatConfigWithBLOBs sysWechatConfig = sysWechatConfigService.findByMerchantNo(sysMerchant.getSerialNo());
		String access_token = HttpTool.getAccessToken(sysWechatConfig.getAppId(),sysWechatConfig.getAppSecret());
		
		
		//*****************************************多媒体上传图片 begin***************************************************
		WritableResource resource = new FileSystemResource(new File("C:/Users/IBM_ADMIN/Desktop/a.jpg"));
		MultiValueMap data = new LinkedMultiValueMap();
		data.add("media", resource);
		String urlString = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type=image";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(urlString, data, String.class);
		 
		System.out.println(result);
	}
}
