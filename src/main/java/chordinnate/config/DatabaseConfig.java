package chordinnate.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"chordinnate.*"})
@EnableJpaRepositories(basePackages = {"chordinnate.repository"})
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    private static final String DEFAULT_DB_DRIVER = "org.sqlite.JDBC";
    private static final String DEFAULT_DB_URL = "jdbc:sqlite:src/main/resources/chordinnate.db";
    private static final String DEFAULT_DIALECT = "com.enigmabridge.hibernate.dialect.SQLiteDialect";

    @Value("${db.driver}")
    private String driver;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${entitymanager.packagesToScan}")
    private String packagesToScan;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        boolean configured = StringUtils.isNotBlank(driver) && StringUtils.isNotBlank(url);

        // Use embedded SQLite DB as default if no configuration specified
        dataSource.setDriverClassName(configured ? driver : DEFAULT_DB_DRIVER);
        dataSource.setUrl(configured ? url : DEFAULT_DB_URL);
        dataSource.setUsername(configured ? username : StringUtils.EMPTY);
        dataSource.setPassword(configured ? password : StringUtils.EMPTY);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(packagesToScan);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", StringUtils.isNotBlank(dialect) ? dialect : DEFAULT_DIALECT);
        hibernateProperties.put("hibernate.show_sql", showSql);
        em.setJpaProperties(hibernateProperties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
