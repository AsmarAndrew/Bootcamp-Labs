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
            System.out.println("ᴘʀᴏꜰɪʟᴇ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 1) ᴠɪᴇᴡ ᴘʀᴏꜰɪʟᴇ");
            System.out.println("↳ 2) ᴜᴘᴅᴀᴛᴇ ᴘʀᴏꜰɪʟᴇ");
            System.out.println("↳ x) ɢᴏ ʙᴀᴄᴋ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
            String choice = scanner.nextLine().toUpperCase();
            System.out.println();

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
                System.out.println("ᴘʀᴏꜰɪʟᴇ ɪɴꜰᴏʀᴍᴀᴛɪᴏɴ:");
                System.out.println("ꜰɪʀꜱᴛ ɴᴀᴍᴇ: " + profile.getFirstName());
                System.out.println("ʟᴀꜱᴛ ɴᴀᴍᴇ: " + profile.getLastName());
                System.out.println("ᴘʜᴏɴᴇ: " + profile.getPhone());
                System.out.println("ᴇᴍᴀɪʟ: " + profile.getEmail());
                System.out.println("ᴀᴅᴅʀᴇꜱꜱ: " + profile.getAddress());
                System.out.println("ᴄɪᴛʏ: " + profile.getCity());
                System.out.println("ꜱᴛᴀᴛᴇ: " + profile.getState());
                System.out.println("ᴢɪᴘ: " + profile.getZip());
            } else {
                System.out.println("ᴘʀᴏꜰɪʟᴇ ɴᴏᴛ ꜰᴏᴜɴᴅ.");
            }
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ꜰᴇᴛᴄʜɪɴɢ ᴛʜᴇ ᴘʀᴏꜰɪʟᴇ.");
            e.printStackTrace();
        }
    }

    private void updateProfile(User user, Scanner scanner) {
        try {
            Profile profile = profileHandler.viewProfile(user.getUserId());

            if (profile != null) {
                System.out.println("ᴇɴᴛᴇʀ ɴᴇᴡ ᴘʀᴏꜰɪʟᴇ ɪɴꜰᴏʀᴍᴀᴛɪᴏɴ (ʟᴇᴀᴠᴇ ʙʟᴀɴᴋ ᴛᴏ ᴋᴇᴇᴘ ᴄᴜʀʀᴇɴᴛ ᴠᴀʟᴜᴇ.):");
                System.out.print("ꜰɪʀꜱᴛ ɴᴀᴍᴇ [" + profile.getFirstName() + "]: ");
                String firstName = scanner.nextLine();
                if (!firstName.isEmpty()) profile.setFirstName(firstName);

                System.out.print("ʟᴀꜱᴛ ɴᴀᴍᴇ [" + profile.getLastName() + "]: ");
                String lastName = scanner.nextLine();
                if (!lastName.isEmpty()) profile.setLastName(lastName);

                System.out.print("ᴘʜᴏɴᴇ [" + profile.getPhone() + "]: ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) profile.setPhone(phone);

                System.out.print("ᴇᴍᴀɪʟ [" + profile.getEmail() + "]: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) profile.setEmail(email);

                System.out.print("ᴀᴅᴅʀᴇꜱꜱ [" + profile.getAddress() + "]: ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) profile.setAddress(address);

                System.out.print("ᴄɪᴛʏ [" + profile.getCity() + "]: ");
                String city = scanner.nextLine();
                if (!city.isEmpty()) profile.setCity(city);

                System.out.print("ꜱᴛᴀᴛᴇ [" + profile.getState() + "]: ");
                String state = scanner.nextLine();
                if (!state.isEmpty()) profile.setState(state);

                System.out.print("ᴢɪᴘ [" + profile.getZip() + "]: ");
                String zip = scanner.nextLine();
                if (!zip.isEmpty()) profile.setZip(zip);

                profileHandler.updateProfile(profile);
                System.out.println("ᴘʀᴏꜰɪʟᴇ ᴜᴘᴅᴀᴛᴇᴅ ꜱᴜᴄᴄᴇꜱꜱꜰᴜʟʟʏ.");
            } else {
                System.out.println("ᴘʀᴏꜰɪʟᴇ ɴᴏᴛ ꜰᴏᴜɴᴅ.");
            }
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀᴇᴅ ᴡʜɪʟᴇ ᴜᴘᴅᴀᴛɪɴɢ ᴛʜᴇ ᴘʀᴏꜰɪʟᴇ.");
            e.printStackTrace();
        }
    }
}