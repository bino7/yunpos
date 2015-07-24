package com.yunpos.application;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yunpos.mybatisPlugin.MybatisInterceptor;

@Configuration
@EnableTransactionManagement
//@MapperScan("com.*.*.mapper")
@MapperScan(value = "com.yunpos.persistence.dao", sqlSessionFactoryRef = "sqlSessionFactoryForPrimary")
public class MyBatisConfig {
	
	
	@Bean
	public MybatisInterceptor getMybatisInterceptor(){
		return new MybatisInterceptor();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource-primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public JdbcTemplate primaryJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(primaryDataSource());
		// jdbcTemplate.execute("create table foo (id integer primary key, name
		// varchar(64) not null)");
		return jdbcTemplate;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryForPrimary() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(primaryDataSource());
		Interceptor[] plugins = {getMybatisInterceptor()};
		ssfb.setPlugins(plugins);
		return ssfb.getObject();
	}
	
//	@Bean
//	public Plugins mybatisPlugin() throws Exception {
//		
//		return new MybatisInterceptor();
//	}
//	
	
}
