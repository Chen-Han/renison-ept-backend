package com.renison;

import java.text.SimpleDateFormat;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configurable
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {

        // EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        // return builder.setType(EmbeddedDatabaseType.HSQL).build();
        // DriverManagerDataSource driverManagerDataSource = new
        // DriverManagerDataSource("jdbc:postgresql://localhost:5432/ept",
        // "hanchen",
        // "");
        // return driverManagerDataSource;
        // EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ept");
        dataSource.setUsername("hanchen");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.renison.repository");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return b;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
                registry.addMapping("/**").allowedOrigins("*");
            }

            //            public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
            //                MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
            //
            //                ObjectMapper mapper = new ObjectMapper();
            //                //Registering Hibernate4Module to support lazy objects
            //                mapper.registerModule(new Hibernate4Module());
            //
            //                messageConverter.setObjectMapper(mapper);
            //                return messageConverter;
            //
            //            }
            //
            //            @Override
            //            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            //                //Here we add our custom-configured HttpMessageConverter
            //                converters.add(jacksonMessageConverter());
            //                super.configureMessageConverters(converters);
            //            }
        };
    }

    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .modulesToInstall(Hibernate4Module.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);// include all fields by default
        return mapper;
    }
}
