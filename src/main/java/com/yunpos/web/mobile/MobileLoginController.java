package com.yunpos.web.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.yunpos.model.SysMerchant;
import com.yunpos.model.SysStore;
import com.yunpos.model.SysUser;
import com.yunpos.service.SysMerchantService;
import com.yunpos.service.SysStoreService;
import com.yunpos.service.SysUserService;
import com.yunpos.utils.Response.Code;
import com.yunpos.web.BaseController;
/**
 * 
 * 功能描述：手机登录
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月21日
 * @author Devin_Yang 修改日期：2015年7月21日
 *
 */
@Controller
public class MobileLoginController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(MobileLoginController.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMerchantService sysMerchantService;
    @Autowired
    private SysStoreService sysStoreService;

    
    @RequestMapping(value = "/mobile/login")
    public @ResponseBody String  login(HttpServletRequest request,HttpServletResponse response, String userName, String password) throws JsonProcessingException {
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        try {
        	 if(Strings.isNullOrEmpty(userName)||Strings.isNullOrEmpty(password)){
             	jsonMap.put("error", Code.PARAM_NULL);
             	return objectMapper.writeValueAsString(jsonMap);
             }
        	 List<SysUser> sysUsers = sysUserService.findByUserName(userName);
             if(sysUsers== null ||sysUsers.size()<1){
             	jsonMap.put("error", Code.USER_NOT_EXISTS);
             	return objectMapper.writeValueAsString(jsonMap);
             }
             if(!sysUsers.get(0).getUserName().equals(userName)){
              	jsonMap.put("error", Code.NAME_ERROR);
              	return objectMapper.writeValueAsString(jsonMap);
              }
             if(!sysUsers.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(password.trim().getBytes()))){
             	jsonMap.put("error", Code.PASSWORD_ERROR);
             	return objectMapper.writeValueAsString(jsonMap);
             }
             if(sysUsers.get(0).getOrgNo().length()<12){
            	 jsonMap.put("error", Code.NOT_STORE_USER);
              	return objectMapper.writeValueAsString(jsonMap);
             }
             //商户号统一使用组织结构号
            
             SysMerchant sysMerchant = sysMerchantService.findBySerialNo(sysUsers.get(0).getOrgNo().substring(0, 8));
             Map<String,String> sysMerchantMap = new HashMap<>();
             sysMerchantMap.put("storeNo", sysUsers.get(0).getOrgNo());
             sysMerchantMap.put("serialNo", sysMerchant.getSerialNo()); 		//商户号
             sysMerchantMap.put("md5Key", sysMerchant.getMd5Key());				//商户秘钥
             sysMerchantMap.put("companyName", sysMerchant.getCompanyName());	//商户名称（公司名称）
             jsonMap.put("user", sysMerchantMap);
		} catch (Exception e) {
			jsonMap.put("error", Code.EXCEPTION);
			logger.error("error:"+e.getMessage());
			return objectMapper.writeValueAsString(jsonMap);
		}
        return objectMapper.writeValueAsString(jsonMap);
    }
   
}