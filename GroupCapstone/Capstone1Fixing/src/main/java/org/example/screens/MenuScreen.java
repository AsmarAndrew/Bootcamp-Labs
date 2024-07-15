package org.example.screens;

import org.example.models.User;
import java.util.Scanner;

public class MenuScreen {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. Profile");
            System.out.println("2. Home Screen");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ProfileScreen profileScreen = new ProfileScreen();
                    profileScreen.showProfileScreen(user);
                    break;
                case 2:
                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.showHomeScreen(user);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
