/*
 * *
 *  * 功能描述：
 *  * <p>
 *  * 版权所有：小牛信息科技有限公司
 *  * <p>
 *  * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *  *
 *  * @author Bino Zhong 新增日期：${date}
 *  * @author Bino Zhong 修改日期：${date}
 *  *
 *
 */

package com.yunpos.mybatisPlugin;

import java.sql.Connection;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.yunpos.model.SysRole;
import com.yunpos.rewriter.Binding;
import com.yunpos.rewriter.DefaultStatementRewriter;
import com.yunpos.rewriter.StatementRewriter;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.security.SecurityUser;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.yunpos.model.SysDataRule;
import com.yunpos.translation.FilterGroup;
import com.yunpos.translation.FilterTranslator;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MybatisInterceptor implements Interceptor {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

	private Properties properties;
	
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();  
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();  

	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		// 分离代理对象链
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		statementHandler.getBoundSql();

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");

		HttpServletRequest request=null;
		Filter filter=null;
		try{
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			filter = (Filter) request.getAttribute("filter");
		}catch(Exception e){
			if(request!=null){
				logger.error("error when get filter from request",e);
			}
		}

		if (filter != null && null != originalSql && !"".equals(originalSql)) {
			StatementRewriter rewriter = new DefaultStatementRewriter();
			Map<String,Object> params=collectBindingParams();
			String rewritedSql = rewriter.rewrite(originalSql, filter, params);
			Class parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
			SqlSourceBuilder builder = new SqlSourceBuilder(configuration);
			SqlSource sqlSource = builder.parse(rewritedSql, parameterType, null);
			List<ParameterMapping> parameterMappings = sqlSource.getBoundSql(parameterObject).getParameterMappings();
			metaStatementHandler.setValue("delegate.boundSql.sql", sqlSource.getBoundSql(parameterObject).getSql());
			metaStatementHandler.setValue("delegate.boundSql.parameterMappings", parameterMappings);
			// 调用原始statementHandler的prepare方法
			statementHandler = (StatementHandler) metaStatementHandler.getOriginalObject();
			statementHandler.prepare((Connection) invocation.getArgs()[0]);
		}
		return invocation.proceed();
	}

	private SqlSource buildSqlSource(Configuration configuration, String originalSql,   
		    Class<?> parameterType) {  
		        SqlSourceBuilder builder = new SqlSourceBuilder(configuration);  
		        return builder.parse(originalSql, parameterType, null);  
		    }  
	private Map<String,Object> collectBindingParams(){
		PrincipalCollection principals= (PrincipalCollection) SecurityUtils.getSubject().getPrincipals();
		SecurityUser securityUser = (SecurityUser) principals.getPrimaryPrincipal();
		Map<String,Object> params=new HashMap<>();
		params.put(Binding.USER_ID,securityUser.getId());
		params.put(Binding.USER_NAME, securityUser.getUsername());
		List<Integer> roleList=new ArrayList<>();
		for(SysRole role:securityUser.getSysRoles()){
			roleList.add(role.getId());
		}
		params.put(Binding.SYS_ROLE,roleList);
		return params;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}
}
