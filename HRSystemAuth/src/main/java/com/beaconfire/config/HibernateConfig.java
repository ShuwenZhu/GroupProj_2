package com.beaconfire.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableTransactionManagement
@Getter
@Setter
public class HibernateConfig {

	@Value("${database.hibernate.url}")
	private String url;

	@Value("${database.hibernate.driver}")
	private String driver;

	@Value("${database.hibernate.username}")
	private String username;

	@Value("${database.hibernate.password}")
	private String password;

	@Value("${database.hibernate.dialect}")
	private String dialect;

	@Value("${database.hibernate.showsql}")
	private String showsql;

	@Value("${database.hibernate.hbm2ddl.auto}")
	private String hbm2ddl;

	@Bean
	protected LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.beaconfire.domain");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(getDriver());
		dataSource.setUrl(getUrl());
		dataSource.setUsername(getUsername());
		dataSource.setPassword(getPassword());
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.show_sql", getShowsql());
		hibernateProperties.setProperty("hibernate.dialect", getDialect());
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", getHbm2ddl());
		return hibernateProperties;
	}
}
