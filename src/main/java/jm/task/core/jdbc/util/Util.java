package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "192837465aA.A1";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty(Environment.URL, URL)
                .setProperty(Environment.USER, USERNAME)
                .setProperty(Environment.PASS, PASSWORD)
                .setProperty(Environment.SHOW_SQL, "true")
                .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
}
