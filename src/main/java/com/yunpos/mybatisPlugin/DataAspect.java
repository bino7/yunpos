//package com.yunpos.mybatisPlugin;
//
//import java.lang.reflect.Method;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.DataSource;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.yunpos.model.DataRule;
//
//@Aspect
//@Component
//public class DataAspect {
//
//	@Autowired(required = true)
//	private HttpServletRequest request;
//
//	@Around("execution(public * com.yunpos.persistence.dao.*Mapper.*(..))")
//	public Object addOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println("Completed: " + joinPoint);
//		Object[] args = joinPoint.getArgs();
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		DataRule dataRule = (DataRule) request.getAttribute("DATA_RULE");
//		// String dr = dataRule.getDatarule();
//
//		Object target = joinPoint.getTarget();
//		System.out.println(target.toString());
//		String method = joinPoint.getSignature().getName();
//		System.out.println(method);
//		Class<?>[] classz = target.getClass().getInterfaces();
//		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
//		try {
//			Method m = classz[0].getMethod(method, parameterTypes);
//			System.out.println(m.getAnnotations());
//			System.out.println(m.getName());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return joinPoint.proceed();
//	}
//}