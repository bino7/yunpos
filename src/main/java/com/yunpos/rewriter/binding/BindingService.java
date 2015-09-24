package com.yunpos.rewriter.binding;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/9/7
 * @author bino 修改日期：2015/9/7
 */
@Component
public class BindingService {

    public Map<String,Object> getBindingParams(){
        /*Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails user=(OAuth2AuthenticationDetails)auth.getDetails();
        Map<String,Object> params=new HashMap<>();
        params.put(Binding.USER_NAME, auth.getName());
        List<String> authenticationCollection=new ArrayList<>();

        params.put(Binding.SYS_AUTHORITY,authenticationCollection);
        return params;*/


        Map<String,Object> params=new HashMap<>();
        params.put(Binding.USER_NAME, "admin");
        List<String> authenticationCollection=new ArrayList<>();

        params.put(Binding.SYS_AUTHORITY,authenticationCollection);
        return params;
    }
}
