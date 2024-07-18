package org.example.screens;

import org.example.models.User;
import java.util.Scanner;

public class MenuScreen {

    public void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ᴡᴇʟᴄᴏᴍᴇ, " + user.getUsername());
            System.out.println("↳ 1. ᴘʀᴏꜰɪʟᴇ");
            System.out.println("↳ 2. ʜᴏᴍᴇ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 3. ʟᴏɢᴏᴜᴛ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
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
                    System.out.println("ʟᴏɢɢɪɴɢ ᴏᴜᴛ...");
                    return;
                default:
                    System.out.println("ɪɴᴠᴀʟɪᴅ ᴄʜᴏɪᴄᴇ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ.");
            }
        }
    }
}