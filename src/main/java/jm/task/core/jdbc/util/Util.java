
package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private Connection connection;
    public Connection connect() {
        Properties property = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/Resources/mysql_config")) {
            property.load(input);
            String user = property.getProperty("db.login");
            String password = property.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            StringBuilder url = new StringBuilder();
            url.append(property.getProperty("db.driver"));
            url.append(property.getProperty("db.type"));
            url.append(property.getProperty("db.host"));
            url.append(property.getProperty("db.port"));
            url.append(property.getProperty("db.base"));
            connection = DriverManager.getConnection(url.toString(), user, password);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}