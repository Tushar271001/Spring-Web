package com.maven.configuration;

import com.maven.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.maven.services.Impl.UserServiceImpl;
import com.maven.services.UserService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.maven"})
@EnableTransactionManagement
public class SpringConfig {
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
    @Bean
    public Properties getHibernateProperties(){
        Properties p = new Properties();
        p.setProperty("hibernate.hbm2ddl.auto","update");
        p.setProperty("hibernate.show_sql", "true");
        p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return p;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setAnnotatedClasses(User.class);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }
    @Bean
    public HibernateTemplate getHibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(getSessionFactory().getObject());
        return hibernateTemplate;
    }


    @Bean("userService")
    public UserService userService(){
        UserServiceImpl userService=new UserServiceImpl();
        userService.setHibernateTemplate(getHibernateTemplate());
        return userService;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hb = new HibernateTransactionManager();
        hb.setSessionFactory(getSessionFactory().getObject());
        return hb;
    }
}
