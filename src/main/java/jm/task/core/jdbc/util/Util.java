package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL =
            "jdbc:mysql://localhost/firstdb?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME  = "root";
    private static final String PASS = "E8d4a51000";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String sqlCreate = "CREATE TABLE IF NOT EXISTS User " +
            "( id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR (20), lastName VARCHAR (20), " +
            "age SMALLINT NOT NULL, PRIMARY KEY (id))";
    private static final String sqlDrop = "DROP TABLE IF EXISTS User";


    public static String getSqlDrop() {
        return sqlDrop;
    }

    public static String getSqlCreate() {
        return sqlCreate;
    }

    public static  Connection SQLConnection () {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory HibConnection () {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url",URL)
                .setProperty("hibernate.connection.username",USERNAME)
                .setProperty("hibernate.connection.password",PASS)
                .setProperty("hibernate.connection.driver_class",DRIVER)
                .setProperty("dialect","org.hibernate.dialect.MySQLDialect")
                .addAnnotatedClass(User.class);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
        }

    }

