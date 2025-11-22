package com.example.MultipleDataSourceDemo.account;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.MultipleDataSourceDemo.account"},
        entityManagerFactoryRef = "accountEntityManager",
        transactionManagerRef = "accountTransactionManager"
)
public class AccountDbConfiguration {

    @Autowired
    Environment environment;

    @Bean
    @ConfigurationProperties(prefix = "spring.accountds")
    public DataSource accountDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean accountEntityManager() {
        LocalContainerEntityManagerFactoryBean rm = new LocalContainerEntityManagerFactoryBean();

        rm.setDataSource(accountDataSource());
        rm.setPackagesToScan("com.example.hibernate.demo.account");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        rm.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("accountds.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        rm.setJpaPropertyMap(properties);
        return rm;
    }

    @Bean
    public PlatformTransactionManager accountTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(accountEntityManager().getObject());
        return jpaTransactionManager;
    }
}
