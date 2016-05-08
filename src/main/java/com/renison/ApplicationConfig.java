package com.renison;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configurable
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class ApplicationConfig {
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/ept");
		dataSource.setUsername("han");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.renison.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	// @Bean
	// public EntityManagerFactory entityManagerFactory() {
	//
	// HibernateJpaVendorAdapter vendorAdapter = new
	// HibernateJpaVendorAdapter();
	// vendorAdapter.setGenerateDdl(true);
	//
	// LocalContainerEntityManagerFactoryBean factory = new
	// LocalContainerEntityManagerFactoryBean();
	// factory.setJpaVendorAdapter(vendorAdapter);
	// factory.setPackagesToScan("com.renison.model");
	// factory.setDataSource(dataSource());
	// factory.afterPropertiesSet();
	// return factory.getObject();
	// }

	// @Bean
	// public PlatformTransactionManager transactionManager() {
	//
	// JpaTransactionManager txManager = new JpaTransactionManager();
	// txManager.setEntityManagerFactory(entityManagerFactory());
	// return txManager;
	// }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}

			public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
				MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

				ObjectMapper mapper = new ObjectMapper();
				// Registering Hibernate4Module to support lazy objects
				Hibernate4Module hibernate4Module = new Hibernate4Module();
				hibernate4Module.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
				mapper.registerModule(hibernate4Module);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				messageConverter.setObjectMapper(mapper);
				return messageConverter;

			}

			@Override
			public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
				// Here we add our custom-configured HttpMessageConverter
				converters.add(jacksonMessageConverter());
				super.configureMessageConverters(converters);
			}
		};
	}

	// @Bean
	// @Primary
	// public Jackson2ObjectMapperBuilder configureObjectMapper() {
	// return new
	// Jackson2ObjectMapperBuilder().modulesToInstall(Hibernate4Module.class)
	// .annotationIntrospector(new JsonEptViewAnnotationIntrospector());
	// }
	//
	// @Bean
	// public ObjectMapper objectMapper() {
	// ObjectMapper mapper = new ObjectMapper();
	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	// mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);// include
	// all fields by default
	// AnnotationIntrospector introspector = new
	// JsonEptViewAnnotationIntrospector();
	// mapper.setAnnotationIntrospector(introspector);
	// return mapper;
	// }
}
