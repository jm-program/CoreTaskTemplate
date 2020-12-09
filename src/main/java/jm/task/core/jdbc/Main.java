package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.util.Util;


import java.net.PasswordAuthentication;
import java.sql.*;


public class Main {
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/business?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {

        Util util = new Util();
        Connection conn = util.getConnection(URL,USERNAME,PASSWORD);
        Statement statement = conn.createStatement();

        ResultSet resSet = statement.executeQuery("select * from business.products");

        while (resSet.next()){
            int id;
            String name;
            double price;
            int shopID;
            id = resSet.getInt("product_id");
            name = resSet.getString("product_name");
            price = resSet.getDouble("price");
            shopID = resSet.getInt("shop_id");
            Product product = new Product(id, name, price, shopID);

            System.out.println(product);
        }

        statement.close();
        conn.close();

       /* public void createUsersTable() {
            String SQL = "CREATE TABLE IF NOT EXISTS `jmDatabase`.`" + table + "` (\n" +
                    "  `id` ong NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastname` VARCHAR(45) NULL,\n" +
                    "  `age` TINYINT NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            Statement statement = new Util;
            try {
                statement.executeUpdate(SQL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
    }
}
