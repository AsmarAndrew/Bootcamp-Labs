package org.example.screens;

import org.example.dao.UserDAO;
import org.example.models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginScreen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("Login Screen");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    try {
                        User user = userDAO.getUserByUsername(username);
                        if (user != null && user.getPassword().equals(password)) {
                            System.out.println("Login successful!");
                            MenuScreen menuScreen = new MenuScreen();
                            menuScreen.showMenu(user);
                        } else {
                            System.out.println("Invalid username or password, please try again.");
                        }
                    } catch (SQLException e) {
                        System.out.println("An error occurred while logging in.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
