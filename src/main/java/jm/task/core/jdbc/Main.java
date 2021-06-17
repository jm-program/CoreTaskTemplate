package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();

        System.out.println("Создаем таблицу пользователей...");
        service.createUsersTable();


        System.out.println("Добавляем пользователей...");
        service.saveUser("Тимур", "Батрутдинов", (byte) 38);
        service.saveUser("Гарик", "Мартиросян", (byte) 44);
        service.saveUser("Гарик", "Мартиросян", (byte) 44);
        service.saveUser("Павел", "Воля", (byte) 40);
        service.saveUser("Семен", "Слепаков", (byte) 41);


        System.out.println("Выводим список всех пользователей...");
        List<User> users = new ArrayList<>(service.getAllUsers());
        users.stream()
                .sorted((u1, u2) -> u1.getName().compareToIgnoreCase(u2.getName()))
                .forEach(System.out::println);


        System.out.println("Очищаем и удаляем таблицу пользователей");
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
