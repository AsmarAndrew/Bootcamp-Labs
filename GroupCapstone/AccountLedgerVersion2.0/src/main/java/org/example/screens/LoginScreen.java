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
            System.out.println("ʟᴏɢɪɴ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 1. ʟᴏɢɪɴ");
            System.out.println("↳ 2. ᴇxɪᴛ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("ᴜꜱᴇʀɴᴀᴍᴇ: ");
                    String username = scanner.nextLine();
                    System.out.print("ᴘᴀꜱꜱᴡᴏʀᴅ: ");
                    String password = scanner.nextLine();
                    System.out.println();

                    try {
                        User user = userDAO.getUserByUsername(username);
                        if (user != null && user.getPassword().equals(password)) {
                            System.out.println("ʟᴏɢɪɴ ꜱᴜᴄᴄᴇꜱꜱꜰᴜʟ!");
                            MenuScreen menuScreen = new MenuScreen();
                            menuScreen.showMenu(user);
                        } else {
                            System.out.println("ɪɴᴠʟᴀɪᴅ ᴜꜱᴇʀɴᴀᴍᴇ ᴏʀ ᴘᴀꜱꜱᴡᴏʀᴅ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ.");
                        }
                    } catch (SQLException e) {
                        System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ʟᴏɢɢɪɴɢ ɪɴ.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("ᴇxɪᴛɪɴɢ...");
                    return;
                default:
                    System.out.println("ɪɴᴠᴀʟɪᴅ ᴄʜᴏɪᴄᴇ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ.");
            }
        }
    }
}