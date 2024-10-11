package com.siva.chatbot.util;



import com.siva.chatbot.model.Conversations;
import com.siva.chatbot.util.ConnectionUtil;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.PersistenceException;

public class SessionUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws PersistenceException {
        if (sessionFactory == null) {
            try {
                // Get the connection from ConnectionUtil
                Connection connection = ConnectionUtil.getConnection();

                // Set up Hibernate properties programmatically
                Configuration configuration = new Configuration();
                
                // Set Hibernate properties
                Properties hibernateProperties = new Properties();
                hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                hibernateProperties.put("hibernate.show_sql", "true");
                hibernateProperties.put("hibernate.hbm2ddl.auto", "update");

                // Apply properties to the configuration
                configuration.setProperties(hibernateProperties);
                
                // Add annotated entity classes
                configuration.addAnnotatedClass(Conversations.class); // Replace with your entity class

                // Build ServiceRegistry and create the SessionFactory
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                try {
					sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

