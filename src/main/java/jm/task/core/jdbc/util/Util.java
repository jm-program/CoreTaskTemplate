package jm.task.core.jdbc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Util {
    Connection connect;

    public Connection connect() {
        try {
            Scanner s = new Scanner(new File("/home/nethunter/IdeaProjects/CoreTaskTemplate/src/main/java/jm/task/core/jdbc/util/mysql_config.txt"));
            while (s.hasNext()) {
                String url = s.next() +
                        ":" + s.next() +
                        ":" + "//" + s.next() +
                        ":" + s.next() +
                        "/" + s.next();
                String user = s.next();
                String password = s.next();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connect = DriverManager.getConnection(url, user, password);
            }
        } catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}