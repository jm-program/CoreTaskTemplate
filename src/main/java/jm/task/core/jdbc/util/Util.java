package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;

public class Util {
    private static String dbURL = "jdbc:mysql://localhost:3306/users";
    private static String dbUsername = "root";
    private static String dbPassword = "root";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory () {
        Configuration configuration = new Configuration()
                .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                .setProperty(Environment.URL, dbURL)
                .setProperty(Environment.USER, dbUsername)
                .setProperty(Environment.PASS, dbPassword)
                .setProperty(Environment.SHOW_SQL, "true")
                .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
}
