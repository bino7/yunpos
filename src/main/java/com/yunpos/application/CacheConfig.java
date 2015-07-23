package com.yunpos.application;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {
    // --------------  Cache Cofig --------------
    @Bean(name = "ehcache")
    public static EhCacheCacheManager ehcache() {
        return new EhCacheCacheManager(cacheManager().getObject());
    }

    @Bean(name = "cacheManager")
    public static EhCacheManagerFactoryBean cacheManager() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
    // --------------  Cache Cofig --------------
}
