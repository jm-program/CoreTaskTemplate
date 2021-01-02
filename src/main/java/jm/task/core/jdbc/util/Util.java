package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void driverRegister () {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
