package org.example;

import java.util.Scanner;

public class UserInterface {

    private DeliShopOrder deliShopOrder = new DeliShopOrder();
    private Scanner scanner = new Scanner(System.in);

    public void displayHomeScreen() {
        int choice;
        do {
            System.out.println("Welcome to DELI-cious!");
            System.out.println("Prepare your taste buds for a flavor explosion!\n");
            System.out.println("1) Start a New Order and make your tummy happy");
            System.out.println("0) Exit and leave your taste buds longing for more\n");
            System.out.print("Enter your delicious choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayOrderScreen();
                    break;
                case 0:
                    System.out.println("\n\nThank you for visiting DELI-cious! Come back soon!");
                    break;
                default:
                    System.out.println("\n\nOops! That's not a valid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void displayOrderScreen() {
        int choice;
        do {
            System.out.println("\n\nOrder Menu:");
            System.out.println("1) Add a Scrumptious Sandwich");
            System.out.println("2) Add a Delightful Drink");
            System.out.println("3) Add Crunchy Chips");
            System.out.println("4) Checkout and satisfy your cravings");
            System.out.println("0) Cancel Order and leave us heartbroken\n");
            System.out.print("Enter your yummy choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    deliShopOrder.addSandwich();
                    System.out.println("\nA sandwich? Great choice! Let's add some more goodies.\n");
                    break;
                case 2:
                    deliShopOrder.addDrink();
                    System.out.println("\nA drink? Hydration is key! What else can we get for you?\n");
                    break;
                case 3:
                    deliShopOrder.addChips();
                    System.out.println("\nChips? Crunch away! Anything else?\n");
                    break;
                case 4:
                    deliShopOrder.checkout();
                    System.out.println("\nChecking out? We hope you enjoy your meal!\n");
                    return;
                case 0:
                    deliShopOrder.cancelOrder();
                    System.out.println("\nOrder cancelled. We're sad to see you go. Come back soon!\n");
                    return;
                default:
                    System.out.println("\nInvalid choice. Try again, your taste buds are counting on you.\n");
            }
        } while (choice != 0);
    }
}