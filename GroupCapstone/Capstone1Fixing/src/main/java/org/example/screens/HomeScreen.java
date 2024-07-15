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
            System.out.println("Home Screen");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment");
            System.out.println("3) Ledger");
            System.out.println("X) Go Back");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

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
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void handleAddDeposit(User user, Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            transactionHandler.addDeposit(user.getUserId(), description, vendor, amount);
            System.out.println("Deposit added successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the deposit.");
            e.printStackTrace();
        }
    }

    private void handleMakePayment(User user, Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        try {
            transactionHandler.makePayment(user.getUserId(), description, vendor, amount);
            System.out.println("Payment made successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while making the payment.");
            e.printStackTrace();
        }
    }
}
