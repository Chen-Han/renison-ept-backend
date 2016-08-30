package com.renison;

import java.util.List;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configurable
@Configuration
@EnableTransactionManagement
public class ApplicationConfig {
	@Autowired
	private Environment env;
	private @Value("${spring.datasource.url}") String datasourceUrl;
	private @Value("${spring.datasource.username}") String username;
	private @Value("${spring.datasource.password}") String password;
	private @Value("${spring.datasource.driver-class-name}") String driverClassName;
	private @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

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

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
		return properties;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "POST", "GET", "DELETE");
			}

			// attempt to serve static content along with rest API
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public");
			}

			// see
			// http://stackoverflow.com/questions/24661289/spring-boot-not-serving-static-content
			@Override
			public void configurePathMatch(PathMatchConfigurer configurer) {
				super.configurePathMatch(configurer);
				configurer.setUseSuffixPatternMatch(false);
			}

			public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
				MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

				ObjectMapper mapper = new ObjectMapper();
				// Registering Hibernate4Module to support lazy objects
				Hibernate4Module hibernate4Module = new Hibernate4Module();
				hibernate4Module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
				// supports transient field being serialized
				hibernate4Module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
				mapper.registerModule(hibernate4Module);
				mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
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

	// this solves lazily loaded fields cannot be serialized
	@Bean
	public OpenSessionInViewFilter configureOpenSessionInViewFilter() {
		OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
		FilterConfig filterConfig = openSessionInViewFilter.getFilterConfig();
		return openSessionInViewFilter;
	}

	// trying to resolve accepting "OPTIONS" request
	// @Bean
	// public DispatcherServletBeanPostProcessor
	// dispatcherServletBeanPostProcessor() {
	// return new DispatcherServletBeanPostProcessor();
	// }
	//
	// public static class DispatcherServletBeanPostProcessor implements
	// BeanPostProcessor {
	// @Override
	// public Object postProcessBeforeInitialization(Object bean, String
	// beanName) throws BeansException {
	// if (bean instanceof DispatcherServlet) {
	// ((DispatcherServlet) bean).setDispatchOptionsRequest(true);
	// }
	// return bean;
	// }
	//
	// @Override
	// public Object postProcessAfterInitialization(Object bean, String
	// beanName) throws BeansException {
	// return bean;
	// }
	// }

	// attempt to provide session factory bean as a wrapper for entity manager
	// factory
	// note that this would solve the pre-update pre-persist listening problem
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
	//
	// @Bean
	// @Autowired
	// public PlatformTransactionManager transactionManager(EntityManagerFactory
	// emf) {
	//
	// JpaTransactionManager txManager = new JpaTransactionManager();
	// txManager.setEntityManagerFactory(emf);
	// return txManager;
	// }
	//
	// @Bean
	// public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory
	// emf) {
	// HibernateJpaSessionFactoryBean factory = new
	// HibernateJpaSessionFactoryBean();
	// factory.setEntityManagerFactory(emf);
	// return factory;
	// }
}
