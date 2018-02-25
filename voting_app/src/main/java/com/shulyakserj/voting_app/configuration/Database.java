package com.shulyakserj.voting_app.configuration;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.shulyakserj.voting_app.repository" }, transactionManagerRef = "voting_app_transaction_manager", entityManagerFactoryRef = "voting_app_entity_manager_factory")
public class Database {
	@Autowired
	private Environment env;
 
	@Bean(name = "voting_app_data_source")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public ComboPooledDataSource loadingDataSource() throws PropertyVetoException {

		final String url = env.getProperty("spring.datasource.url");
		final String user = env.getProperty("spring.datasource.username");
		final String password = env.getProperty("spring.datasource.password");
		final String driver_classname = env.getProperty("spring.datasource.driver-class-name");

		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass(driver_classname);
 
		StringBuilder urlStringBuilder = new StringBuilder();

		urlStringBuilder.append(url);
		urlStringBuilder.append("//?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&autoReconnect=true");
		cpds.setJdbcUrl(urlStringBuilder.toString());

		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setTestConnectionOnCheckout(true);
		cpds.setPreferredTestQuery("SELECT 1");
		cpds.setAcquireRetryAttempts(1);
		cpds.setTestConnectionOnCheckout(true);

		return cpds;

	}
	

	@Bean(name = "voting_app_entity_manager_factory")
	@Primary
	public LocalContainerEntityManagerFactoryBean loadingEntityManagerFactory(EntityManagerFactoryBuilder builder)
			throws PropertyVetoException {
		Map<String, String> props = new HashMap<String, String>();
		final String dialect = env.getProperty("hibernate.dialect");
		
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect", dialect);
		props.put("hibernate.connection.characterEncoding", "utf8");
		props.put("hibernate.connection.useUnicode", "true");
		props.put("hibernate.connection.charSet", "UTF-8");

		props.put("hibernate.testOnBorrow", "true");
		props.put("hibernate.dbcp.validationQuery", "SELECT 1");
		props.put("hibernate.hbm2ddl.auto", "validate");
		props.put("hibernate.format_sql", "true");

		props.put("spring.datasource.dbcp2.test-on-borrow", "true");
		props.put("spring.datasource.dbcp2.validation-query", "SELECT 1");
		props.put("spring.datasource.dbcp2.initial-size", "1");
		props.put("spring.datasource.dbcp2.maxIdle", "20");
		
	    LocalContainerEntityManagerFactoryBean em =  builder.dataSource(loadingDataSource()).properties(props).persistenceUnit("voting_app_pu")
				.packages("com.shulyakserj.voting_app.entity").build();
	        
	    ///for hibernate.hbm2ddl.auto
	    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter(vendorAdapter);

	    Properties properties = new Properties();
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    em.setJpaProperties(properties);
	    
	    return em;
	}

	@Bean(name = "voting_app_entity_manager")
	@Primary
	@PersistenceContext(name = "voting_app_pu")
	public EntityManager entityManager(	@Qualifier("voting_app_entity_manager_factory") EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}
	
	@Bean(name = "voting_app_transaction_manager")
	@Primary
	@PersistenceContext(name = "voting_app_pu")
	public PlatformTransactionManager transactionManager(
			@Qualifier("voting_app_entity_manager_factory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
