package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();;

        // Создание таблицы User(ов)
        //userDaoJDBC.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
        // должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userDaoJDBC.saveUser("biba2","boba2", (byte) 32);
        userDaoJDBC.saveUser("biba3","boba3", (byte) 32);
        userDaoJDBC.saveUser("biba4","boba4", (byte) 32);
        userDaoJDBC.saveUser("biba5","boba5", (byte) 32);

        //Удаление таблицы
       // userDaoJDBC.dropUsersTable();

        //После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )

        //Очистка таблицы User(ов)
        //userDaoJDBC.cleanUsersTable();

        //Получение всех User из базы и вывод в консоль
        System.out.println(userDaoJDBC.getAllUsers());
        System.out.println(userService.getAllUsers());
//        List<User> user = userDaoJDBC.getAllUsers();
//        for (User s : user) {
//            System.out.println(s);
//        }


        //закрыть соединения
        // https://ru.stackoverflow.com/questions/551191/java-%D0%91%D0%94-%D0%9A%D0%BE%D0%BD%D1%81%D0%BE%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%B2%D0%B2%D0%BE%D0%B4-%D0%B2%D1%8B%D0%B2%D0%BE%D0%B4
    }
}
