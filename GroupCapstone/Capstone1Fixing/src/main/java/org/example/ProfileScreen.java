package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileScreen {
    public void viewProfile(User user) {
        ProfileDAO profileDAO = new ProfileDAO();

        try {
            Profile profile = profileDAO.getProfileByUserId(user.getUserId());

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

    public void updateProfile(User user, Scanner scanner) {
        ProfileDAO profileDAO = new ProfileDAO();

        try {
            Profile profile = profileDAO.getProfileByUserId(user.getUserId());

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

                profileDAO.updateProfile(profile);
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
