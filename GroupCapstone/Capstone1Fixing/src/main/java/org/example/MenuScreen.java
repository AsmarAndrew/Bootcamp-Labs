package org.example;

import java.util.Scanner;

public class MenuScreen {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        ProfileScreen profileScreen = new ProfileScreen();

        while (true) {
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    profileScreen.viewProfile(user);
                    break;
                case 2:
                    profileScreen.updateProfile(user, scanner);
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
