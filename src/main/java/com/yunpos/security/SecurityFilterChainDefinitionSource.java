package com.yunpos.security;

import org.apache.shiro.config.Ini;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.FactoryBean;

public class SecurityFilterChainDefinitionSource implements FactoryBean<Ini.Section> {
    @Override
    public Ini.Section getObject() throws Exception {
        return loadSection();
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 重新加载配置文件
     */
    public void reloadSecuritySection() {

    }

    private Ini.Section loadSection() {
        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        return section;
    }
}
