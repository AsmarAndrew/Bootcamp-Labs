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
            System.out.println("Ledger Screen");
            System.out.println("1) All - Display all Entries");
            System.out.println("2) Deposits - Display only deposits");
            System.out.println("3) Payments - Display only payments");
            System.out.println("4) Reports");
            System.out.println("X) Go Back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

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
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void displayAllEntries(User user) {
        try {
            ResultSet rs = ledgerHandler.showAllLedgers(user.getUserId());
            displayResultSet(rs);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching transactions.");
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
            System.out.println("An error occurred while fetching payments.");
            e.printStackTrace();
        }
    }

    private void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
            System.out.println("Date: " + rs.getTimestamp("date"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Vendor: " + rs.getString("vendor"));
            System.out.println("Amount: " + rs.getDouble("amount"));
            System.out.println("-----------------------------------");
        }
    }
}
