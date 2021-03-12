package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static private final String URL = "jdbc:mysql://localhost:3306/database";
    static private final String URL_FIX = "jdbc:mysql://localhost:3306/database?useUnicode=true&useSSL=false&" +
            "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static private final String LOG = "root";
    static private final String PASS = "root";

    public Util(){   }

    private static SessionFactory sessionFactory;
    public static SessionFactory getHibernateSessionFactory() {
        try{
            Configuration cfg = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", LOG)
                    .setProperty("hibernate.connection.password", PASS)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql","true")
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .setProperty("hibernate.connection.autocommit", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(User.class)
                    .addPackage("ru.mysql.db");
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(registryBuilder.build());
        }catch (Exception e){
            System.out.println("Error createHibernateSession\n" + e);
        }
        return sessionFactory;
    }

    private static Connection connection;
    public static Connection getUtilConnection() {
        try {
            connection = DriverManager.getConnection(URL_FIX, LOG, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
