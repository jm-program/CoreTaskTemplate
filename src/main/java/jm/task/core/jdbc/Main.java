package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
/* мои заметки
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";

    private static final String USERNAME = "root1";
    private static final String PASSWORD = "12345_Aa";
*/

    //    private static Connection connection;
    private static final String INSERT_NEW = "INSERT INTO users VALUES(?,?,?,?)";


    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
//
////        userService.dropUsersTable();
        try {
            userService.createUsersTable();

        userService.saveUser("Stive2", "Libovski", (byte) 43);
        userService.saveUser("Stive3", "Libovski", (byte) 43);
        userService.saveUser("Stive4", "Libovski", (byte) 43);
////            userService.removeUserById(5);
//
////        System.out.println(userService.getAllUsers().toString());
//        userService.cleanUsersTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
/*
//Добавить пользователя по id
        PreparedStatement preparedStatement = null;

        Util worker = new Util();

        try {
            preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
            preparedStatement.setLong(1, 4L);
            preparedStatement.setString(2, "newName");
            preparedStatement.setString(3, "newLastName");
            preparedStatement.setByte(4, (byte) 22);

            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
*/


/*        Util worker = new Util();

        String query = "SELECT * FROM users";
        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                System.out.println(user.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

/* мои заметки
//            statement.addBatch();//Пакетная обработка
//
//            statement.addBatch("INSERT INTO users (name,lastName,age) VALUES('Stive','Lebovski',35)");
//            statement.addBatch("INSERT INTO users (name,lastName,age) VALUES('Ivan','Ivanov',25)");
//            statement.addBatch("INSERT INTO users (name,lastName,age) VALUES('Alex','Semenov',56)");
//
//
//            statement.executeBatch();
//
//            statement.clearBatch(); //Стирает старые запросы из Batch



        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
            System.err.println("Неудалось загрузить класс драйвера!");
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД Установлено!");
            }

//            statement.execute("INSERT INTO users (name,age,email) VALUES('Stive1',56,'stive1@devcolibri.com')"); //вставить в бд

//                int res = statement.executeUpdate("UPDATE users SET name = 'Alex2', age = 55 WHERE id =9");//Обновить данные в БД. Вернуть id записи
//            System.out.println(res);

//            ResultSet res = statement.executeQuery("SELECT * FROM users");// Возвращает resultSet

//        statement.addBatch();//Пакетная обработка
*/
/*            statement.addBatch("INSERT INTO users (name,age,email) VALUES('Stive1',56,'stive1@devcolibri.com')");
            statement.addBatch("INSERT INTO users (name,age,email) VALUES('Stive1',56,'stive1@devcolibri.com')");
            statement.addBatch("INSERT INTO users (name,age,email) VALUES('Stive1',56,'stive1@devcolibri.com')");

            statement.executeBatch();

            statement.clearBatch(); //Стирает старые запросы из Batch
            *//*


            boolean status = statement.isClosed();//Закрыт ли statement
            System.out.println(status);

            statement.getConnection();//Получить соединение с БД

//            connection.close();// закрыть connection
//            if (connection.isClosed()) {
//                System.out.println("Соединение с БД закрыто!");
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/

    }
}
