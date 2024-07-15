package org.example.screens;

import org.example.handlers.ProfileHandler;
import org.example.models.Profile;
import org.example.models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileScreen {

    private final ProfileHandler profileHandler = new ProfileHandler();

    public void showProfileScreen(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Profile Screen");
            System.out.println("1) View Profile");
            System.out.println("2) Update Profile");
            System.out.println("X) Go Back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    viewProfile(user);
                    break;
                case "2":
                    updateProfile(user, scanner);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewProfile(User user) {
        try {
            Profile profile = profileHandler.viewProfile(user.getUserId());

            if (profile != null) {
                System.out.println("Profile Information:");
                System.out.println("First Name: " + profile.getFirstName());
                System.out.println("Last Name: " + profile.getLastName());
                System.out.println("Phone: " + profile.getPhone());
                System.out.println("Email: " + profile.getEmail());
                System.out.println("Address: " + profile.getAddress());
                System.out.println("City: " + profile.getCity());
                System.out.println("State: " + profile.getState());
                System.out.println("ZIP: " + profile.getZip());
            } else {
                System.out.println("Profile not found.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching the profile.");
            e.printStackTrace();
        }
    }

    private void updateProfile(User user, Scanner scanner) {
        try {
            Profile profile = profileHandler.viewProfile(user.getUserId());

            if (profile != null) {
                System.out.println("Enter new profile information (leave blank to keep current value):");
                System.out.print("First Name [" + profile.getFirstName() + "]: ");
                String firstName = scanner.nextLine();
                if (!firstName.isEmpty()) profile.setFirstName(firstName);

                System.out.print("Last Name [" + profile.getLastName() + "]: ");
                String lastName = scanner.nextLine();
                if (!lastName.isEmpty()) profile.setLastName(lastName);

                System.out.print("Phone [" + profile.getPhone() + "]: ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) profile.setPhone(phone);

                System.out.print("Email [" + profile.getEmail() + "]: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) profile.setEmail(email);

                System.out.print("Address [" + profile.getAddress() + "]: ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) profile.setAddress(address);

                System.out.print("City [" + profile.getCity() + "]: ");
                String city = scanner.nextLine();
                if (!city.isEmpty()) profile.setCity(city);

                System.out.print("State [" + profile.getState() + "]: ");
                String state = scanner.nextLine();
                if (!state.isEmpty()) profile.setState(state);

                System.out.print("ZIP [" + profile.getZip() + "]: ");
                String zip = scanner.nextLine();
                if (!zip.isEmpty()) profile.setZip(zip);

                profileHandler.updateProfile(profile);
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("Profile not found.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the profile.");
            e.printStackTrace();
        }
    }
}
