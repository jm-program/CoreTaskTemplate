package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД
  private static Connection connection = null;
  private static final String URL = "jdbc:mysql://localhost:3306/db_user?autoReconnect=true&useSSL=false";
  private static final String USERNAME = "newroot";
  private static final String PASSWORD = "root";
  private Util(){

  }

  public static Connection getDBConnection(){
    try {
      if(connection == null || connection.isClosed()){
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
      }
    } catch (SQLException  throwables) {
      System.out.println("Ошибка подключения: " + throwables);
    }
    return connection;
  }

  // +++++++++++++++++++++++++++++++++++++++ Hibernate connection ++++++++++++++++++++++++++++++++++//

  private static SessionFactory sessionFactory;
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null ) {
      try {
        Configuration configuration = new Configuration();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/db_user?autoReconnect=true&useSSL=false");
        settings.put(Environment.USER, "newroot");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        settings.put(Environment.HBM2DDL_AUTO, "create"); // create-drop

        configuration.setProperties(settings);

        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sessionFactory;
  }

//  public static void setSessionFactory(){
//    assert  sessionFactory != null;
//     sessionFactory = Util.SessionFactory();
//  }

  public static void closeSF(){
    assert  sessionFactory != null;
    sessionFactory.close();
  }

}
