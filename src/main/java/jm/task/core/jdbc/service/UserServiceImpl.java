package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица user добавленв");
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица user удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.printf("User с именем %s добавлен в базу данных", name);
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.printf("пользователь по id=%d удален", id);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userDaoJDBC.getAllUsers();
        return allUsers;
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Данные очищены");
    }
}
