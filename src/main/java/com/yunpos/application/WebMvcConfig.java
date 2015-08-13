package com.yunpos.application;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.yunpos.web.filter.SitemeshFilter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 视图设置
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 扩展已至此上传进度监控
     */
//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new FileUploadMultipartResolver();
//        resolver.setMaxUploadSize(1000000000);
//        return resolver;
//    }
    
    /**
     * 错误信息国际化配置
     */
//    @Bean(name = "messageSource")
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("/WEB-INF/i18n/messages");
//        messageSource.setCacheSeconds(5);
//        return messageSource;
//    }

    
    /**
     * sitemeshFilter过滤器配置
     * @return
     */
    @Bean(name = "sitemeshFilter")
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new SitemeshFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1111);
        return bean;
    }
    
    /**
     * 解决springmvc 控制器接收不了put请求参数问题
     * @return
     */
    @Bean(name = "HttpMethodFilter")
    public FilterRegistrationBean httpMethodFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new HttpPutFormContentFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
    
    /**
     * 页面登录验证码Servlet配置
     * @return
     */
/*    @Bean(name = "captchaServlet")
    public ServletRegistrationBean captchaServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new CaptchaServlet());
        bean.addUrlMappings("/captcha");
        return bean;
    }*/
}
