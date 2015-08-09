package com.yunpos;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages="com")
@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = new SpringApplicationBuilder(Application.class).run(args);
//        UserService service = cac.getBean(UserService.class);
//        service.createTestData();
//        service.printAllFooAndAllBar();
    }
}
