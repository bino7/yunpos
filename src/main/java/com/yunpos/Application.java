package com.yunpos;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.yunpos.service.FooBarService;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties
public class Application {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = new SpringApplicationBuilder(Application.class).run(args);
        FooBarService service = cac.getBean(FooBarService.class);
//        service.createTestData();
//        service.printAllFooAndAllBar();
    }
}
