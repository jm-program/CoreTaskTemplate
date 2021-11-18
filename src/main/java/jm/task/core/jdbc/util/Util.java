package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/new_base";
    private final static String USER = "root";
    private final static String PASSWORD = "springcourse";
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration config = new Configuration();
            Properties proper = new Properties();
            proper.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            proper.put("hibernate.connection.url", URL);
            proper.put("hibernate.connection.username", USER);
            proper.put("hibernate.connection.password", PASSWORD);
            proper.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            proper.put("hibernate.current_session_context_class", "thread");
            proper.put("hibernate.show_sql", "true");
            proper.put("hibernate.hbm2ddl.auto", "update");
            config.setProperties(proper);
            config.addAnnotatedClass(User.class);
            ServiceRegistry service =
                    new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(service);
        }
        return sessionFactory;
    }

    public Connection getConnect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
