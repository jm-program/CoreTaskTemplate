package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        // Создание таблицы User(ов)
        //userDaoJDBC.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
        // должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userDaoJDBC.saveUser("biba2","boba2", (byte) 32);

    }
}
