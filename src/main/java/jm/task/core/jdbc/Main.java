package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Александр", "Пушкин", (byte) 37);
        userService.saveUser("Максим", "Горький", (byte) 68);
        userService.saveUser("Фёдор", "Достоевский", (byte) 59);
        userService.saveUser("Иван", "Бунин", (byte) 83);

        userService.removeUserById(1);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}