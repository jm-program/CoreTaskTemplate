package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String url = "jdbc:mysql://localhost:3306/users?esuSSL=false";
    private final static String us = "root";
    private final static String pass = "12345";
    private static SessionFactory sessionFactory = null;

    public Util() {
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, us, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.url", url)
                        .setProperty("hibernate.connection.username", us)
                        .setProperty("hibernate.connection.password", pass)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
