package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Util {
    private static SessionFactory sessionFactory;
    private static Connection connection;
    private static String user;
    public static Connection connect() {
        Properties property = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/Resources/mysql_config")) {
            property.load(input);
            user = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            StringBuilder url = new StringBuilder();
            url.append("jdbc:")
                    .append(property.getProperty("db.driver"))
                    .append(property.getProperty("db.host"))
                    .append(property.getProperty("db.port"))
                    .append(property.getProperty("db.base"));
            connection = DriverManager.getConnection(url.toString(), user, password);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void printConnectionInfo(Connection connection) throws SQLException {
        System.out.println(new Date());
        System.out.printf("Текущий ползователь: %s\n", user);
        System.out.printf("Data base: %s\n", connection.getMetaData().getDatabaseProductName());
        System.out.printf("version: %s\n", connection.getMetaData().getDatabaseProductVersion());
        System.out.printf("Driver: %s\n", connection.getMetaData().getDriverName());
        System.out.printf("Autocommit: %s\n", connection.getAutoCommit());
        System.out.println("------------------------------------------------");
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, Object> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jm-base-module-one");
                settings.put(Environment.USER, "jm-admin");
                settings.put(Environment.PASS, "!Jmc0urs3");
                settings.put(Environment.ORDER_UPDATES, true);
                settings.put(Environment.HBM2DDL_AUTO, "validate");
                settings.put(Environment.SHOW_SQL, true);

                registryBuilder.applySettings(settings);
                StandardServiceRegistry registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}