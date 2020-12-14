package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL ="jdbc:mysql://localhost:3306/mydbtest2?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

//-----Hibernate
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = getConfig();
           //configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);// соединение создание фабрики
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    public static Configuration getConfig() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username",USERNAME);
        properties.setProperty("hibernate.connection.password",PASSWORD);
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
       // properties.setProperty("hibernate.show_sql", "true");// Показывает запросы
        properties.setProperty("hibernate.hbm2ddl.auto", "update");// Прочитать отдельно
        properties.setProperty("hibernate.current_session_context_class", "thread");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration .addAnnotatedClass(User.class);//передаем класс с аннотациями

    return configuration;
}
    public static Session getSession () {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //-----
    private Connection connection;
    {
   /* public Util(){// в конструкторе регистрируем драйвер, а соединение в методе
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
        } catch (SQLException ex) {
            System.out.println("Ошибка регистриции драйвера");
            return;
        }*/
    }

    public Connection getConnection(String url, String username, String password) throws SQLException {

            /*  if (connection != null)
            return connection;
        connection = DriverManager.getConnection(url, username, password);
        //System.out.println("Соединение установленно");*/
        return connection;


    }


}
