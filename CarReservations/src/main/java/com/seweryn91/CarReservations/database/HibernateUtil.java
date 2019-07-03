package com.seweryn91.CarReservations.database;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef="transactionManager",
            entityManagerFactoryRef="entityManagerFactory",
            value="com.seweryn91.CarReservations.repository")
public class HibernateUtil {

        @Bean
        public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan( "com.seweryn91.CarReservations.model");
            sessionFactory.setHibernateProperties(additionalProperties());

            return sessionFactory;
        }

        @Bean
        HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(sessionFactory().getObject());

            return transactionManager;
        }

        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/carreservations");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManager
                    = new LocalContainerEntityManagerFactoryBean();
            entityManager.setDataSource(dataSource());
            entityManager.setPackagesToScan("com.seweryn91.CarReservations");
            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            entityManager.setJpaVendorAdapter(vendorAdapter);
            entityManager.setJpaProperties(additionalProperties());
            return entityManager;
        }

        Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "none");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            return properties;
        }
}