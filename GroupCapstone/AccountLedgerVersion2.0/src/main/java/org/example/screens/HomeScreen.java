package org.example.screens;

import org.example.handlers.TransactionHandler;
import org.example.models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class HomeScreen {

    private final TransactionHandler transactionHandler = new TransactionHandler();

    public void showHomeScreen(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ʜᴏᴍᴇ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 1) ᴀᴅᴅ ᴅᴇᴘᴏꜱɪᴛ");
            System.out.println("↳ 2) ᴍᴀᴋᴇ ᴘᴀʏᴍᴇɴᴛ");
            System.out.println("↳ 3) ʟᴇᴅɢᴇʀ");
            System.out.println("↳ x) ɢᴏ ʙᴀᴄᴋ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
            String choice = scanner.nextLine().toUpperCase();
            System.out.println();
            switch (choice) {
                case "1":
                    handleAddDeposit(user, scanner);
                    break;
                case "2":
                    handleMakePayment(user, scanner);
                    break;
                case "3":
                    LedgerScreen ledgerScreen = new LedgerScreen();
                    ledgerScreen.showLedgerScreen(user);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("ɪɴᴠᴀʟɪᴅ ᴄʜᴏɪᴄᴇ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ.");
            }
        }
    }

    private void handleAddDeposit(User user, Scanner scanner) {
        System.out.print("ᴇɴᴛᴇʀ ᴅᴇꜱᴄʀɪᴘᴛɪᴏɴ: ");
        String description = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴠᴇɴᴅᴏʀ: ");
        String vendor = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴀᴍᴏᴜɴᴛ: ");
        double amount = scanner.nextDouble();
        System.out.println();
        scanner.nextLine();

        try {
            transactionHandler.addDeposit(user.getUserId(), description, vendor, amount);
            System.out.println("ᴅᴇᴘᴏꜱɪᴛ ᴀᴅᴅᴇᴅ ꜱᴜᴄᴄᴇꜱꜱꜰᴜʟʟʏ.");
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ᴀᴅᴅɪɴɢ ᴛʜᴇ ᴅᴇᴘᴏꜱɪᴛ.");
            e.printStackTrace();
        }
    }

    private void handleMakePayment(User user, Scanner scanner) {
        System.out.print("ᴇɴᴛᴇʀ ᴅᴇꜱᴄʀɪᴘᴛɪᴏɴ: ");
        String description = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴠᴇɴᴅᴏʀ: ");
        String vendor = scanner.nextLine();
        System.out.print("ᴇɴᴛᴇʀ ᴀᴍᴏᴜɴᴛ: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();

        try {
            transactionHandler.makePayment(user.getUserId(), description, vendor, amount);
            System.out.println("ᴘᴀʏᴍᴇɴᴛ ᴍᴀᴅᴇ ꜱᴜᴄᴄᴇꜱꜱꜰᴜʟʟʏ.");
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ᴍᴀᴋɪɴɢ ᴛʜᴇ ᴘᴀʏᴍᴇɴᴛ.");
            e.printStackTrace();
        }
    }
}