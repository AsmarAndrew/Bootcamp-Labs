package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public void authenticate() {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            try {
                User user = userDAO.getUserByUsername(username);

                if (user != null && user.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    MenuScreen menu = new MenuScreen();
                    menu.showMenu(user);
                    break;
                } else {
                    System.out.println("Incorrect username or password, please try again.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while trying to log in. Please try again.");
                e.printStackTrace();
            }
        }
    }
}
