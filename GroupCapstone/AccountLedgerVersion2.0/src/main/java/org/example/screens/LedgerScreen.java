package org.example.screens;

import org.example.handlers.LedgerHandler;
import org.example.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LedgerScreen {

    private final LedgerHandler ledgerHandler = new LedgerHandler();

    public void showLedgerScreen(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ʟᴇᴅɢᴇʀ ꜱᴄʀᴇᴇɴ");
            System.out.println("↳ 1) ᴀʟʟ - ᴅɪꜱᴘʟᴀʏ ᴀʟʟ ᴇɴᴛʀɪᴇꜱ");
            System.out.println("↳ 2) ᴅᴇᴘᴏꜱɪᴛꜱ - ᴅɪꜱᴘʟᴀʏ ᴏɴʟʏ ᴅᴇᴘᴏꜱɪᴛꜱ");
            System.out.println("↳ 3) ᴘᴀʏᴍᴇɴᴛꜱ - ᴅɪꜱᴘʟᴀʏ ᴏɴʟʏ ᴘᴀʏᴍᴇɴᴛꜱ");
            System.out.println("↳ 4) ʀᴇᴘᴏʀᴛꜱ");
            System.out.println("↳ x) ɢᴏ ʙᴀᴄᴋ");
            System.out.print("ᴇɴᴛᴇʀ ʏᴏᴜʀ ᴄʜᴏɪᴄᴇ: ");
            String choice = scanner.nextLine().toUpperCase();
            System.out.println();

            switch (choice) {
                case "1":
                    displayAllEntries(user);
                    break;
                case "2":
                    displayDeposits(user);
                    break;
                case "3":
                    displayPayments(user);
                    break;
                case "4":
                    ReportsScreen reportsScreen = new ReportsScreen();
                    reportsScreen.showReportsScreen(user);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("ɪɴᴠᴀʟɪᴅ ᴄʜᴏɪᴄᴇ, ᴘʟᴇᴀꜱᴇ ᴛʀʏ ᴀɢᴀɪɴ.");
            }
        }
    }

    private void displayAllEntries(User user) {
        try {
            ResultSet rs = ledgerHandler.showAllLedgers(user.getUserId());
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ꜰᴇᴛᴄʜɪɴɢ ᴛʀᴀɴꜱᴀᴄᴛɪᴏɴꜱ.");
            e.printStackTrace();
        }
    }

    private void displayDeposits(User user) {
        try {
            ResultSet rs = ledgerHandler.showAllDeposits(user.getUserId());
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching deposits.");
            e.printStackTrace();
        }
    }

    private void displayPayments(User user) {
        try {
            ResultSet rs = ledgerHandler.showAllPayments(user.getUserId());
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("ᴀɴ ᴇʀʀᴏʀ ᴏᴄᴄᴜʀʀᴇᴅ ᴡʜɪʟᴇ ꜰᴇᴛᴄʜɪɴɢ ᴅᴇᴘᴏꜱɪᴛꜱ.");
            e.printStackTrace();
        }
    }

    private void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("ᴛʀᴀɴꜱᴀᴄᴛɪᴏɴ ɪᴅ: " + rs.getInt("transaction_id"));
            System.out.println("ᴅᴀᴛᴇ: " + rs.getTimestamp("date"));
            System.out.println("ᴅᴇꜱᴄʀɪᴘᴛɪᴏɴ: " + rs.getString("description"));
            System.out.println("ᴠᴇɴᴅᴏʀ: " + rs.getString("vendor"));
            System.out.println("ᴀᴍᴏᴜɴᴛ: " + rs.getDouble("amount"));
            System.out.println("-----------------------------------");
            System.out.println();
        }
    }
}
