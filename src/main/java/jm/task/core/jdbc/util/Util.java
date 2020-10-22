package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    /*
    Hibernate connector!
     */
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/USER?useUnicode=true&serverTimezone=UTC");
                properties.put(Environment.USER, "Test");
                properties.put(Environment.PASS, "1262");
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                properties.put(Environment.SHOW_SQL, "true");

                Configuration configuration = new Configuration().setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connection success");
            } catch (Exception e) {
                System.out.println("Connection Error");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    //SQL connector!

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String CONNECTION = "jdbc:mysql://localhost:3306/?useUnicode=true&serverTimezone=UTC";
    private final String username = "Test";
    private final String password = "1262";
    private java.sql.Statement myStatement;

    public Statement getmyStatement() {
        return myStatement;
    }

    public void connect() {
        try {
            Class.forName(DRIVER);
            System.out.println("JDBC driver connected");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver problem");
        }
        try {
            Connection connection_link = DriverManager.getConnection(CONNECTION, username, password);
            myStatement = connection_link.createStatement();
            System.out.println("SQL Server connection success");
        } catch (SQLException throwables) {
            throw new IllegalStateException("Cannot connect SQL Server");
        }
    }
}