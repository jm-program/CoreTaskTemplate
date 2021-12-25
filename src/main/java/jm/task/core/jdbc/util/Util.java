package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    

// Реализация без проперти
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "22121995";
    private static SessionFactory sessionFactory;
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String SHOW_SQL = "show_sql";
    private static final String FORMAT_SQL = "format_sql";
//    private static final String HBM2DDL = "hibernate.hbm2ddl.auto";

    private Util() {
    }
// Реализация без проперти
//    public static Connection open() {
//        try {
//            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", PropertiesUtil.get(URL_KEY))
                        .setProperty("hibernate.connection.username", PropertiesUtil.get(USERNAME_KEY))
                        .setProperty("hibernate.connection.password", PropertiesUtil.get(PASSWORD_KEY))
                        .setProperty("show_sql", PropertiesUtil.get(SHOW_SQL))
                        .setProperty("format_sql", PropertiesUtil.get(FORMAT_SQL))
                        .addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Исключение " + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
