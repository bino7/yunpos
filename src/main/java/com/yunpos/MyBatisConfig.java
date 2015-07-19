package com.yunpos;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@MapperScan(value = "com.yunpos.persistence.dao", sqlSessionFactoryRef = "sqlSessionFactoryForPrimary")
public class MyBatisConfig {
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
		return ssfb.getObject();
	}
}
