package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Lennon", (byte) 40));
        users.add(new User("Paul", "McCartney", (byte) 78));
        users.add(new User("George", "Harrison", (byte) 58));
        users.add(new User("Ringo", "Starr", (byte) 80));
        for (User user: users) {
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
            StringBuilder sb = new StringBuilder();
            sb.append("User с именем ")
                    .append(user.getName())
                    .append(" добавлен в базу данных");
            System.out.println(sb.toString());
        }
        List<User> usersFromDb = service.getAllUsers();
        for (User user: usersFromDb) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
