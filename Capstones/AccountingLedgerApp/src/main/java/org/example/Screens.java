package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Screens {

    Scanner scanner = new Scanner(System.in);
    TransactionHandler transactionHandler = new TransactionHandler();
    ReportHandler reportHandler = new ReportHandler();
    List<Transactions> transaction = new ArrayList<>();
    String userInput;

    public void HomeScreen(){

        transactionHandler.transactionReader(transaction);

        do {

            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("        Welcome to Your Banking App");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment");
            System.out.println("3) View Ledger");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    transactionHandler.addDeposit(transaction);
                    returningMessage("Returning back to Home Screen");
                    break;
                case "2":
                    transactionHandler.makePayment(transaction);
                    returningMessage("Returning back to Home Screen");
                    break;
                case "3":
                    returningMessage("Sending to Ledger Screen");
                    LegerScreen();
                    break;
                case "4":
                    returningMessage("Exiting Program, Goodbye");
                    break;
                default:
                    System.out.println("\nInvalid choice, please try again");
            }
        } while (!userInput.equals("4"));

    }

    public void LegerScreen(){

        do {
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("        Ledger Screen");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("1) View All Transactions");
            System.out.println("2) View Deposits");
            System.out.println("3) View Payments");
            System.out.println("4) Generate Reports");
            System.out.println("5) Return to Home Screen");

            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    transactionHandler.allDisplay(transaction);
                    returningMessage("Returning back to Ledger Screen");
                    break;
                case "2":
                    transactionHandler.depositDisplay(transaction);
                    returningMessage("Returning back to Ledger Screen");
                    break;
                case "3":
                    transactionHandler.paymentDisplay(transaction);
                    returningMessage("Returning back to Ledger Screen");
                    break;
                case "4":
                    returningMessage("Sending to Reports Screen");
                    do {
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
                        System.out.println("        Reports Screen");
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━");
                        System.out.println("1) View Month to Date");
                        System.out.println("2) View Previous Month");
                        System.out.println("3) View Year to Date");
                        System.out.println("4) View Previous Year");
                        System.out.println("5) Search by Vendor");
                        System.out.println("6) Custom Search");
                        System.out.println("0) Return to Ledger Screen");
                        System.out.println("H) Return to Home Screen");

                        userInput = scanner.nextLine();
                        switch (userInput.toLowerCase()) {
                            case "1":
                                reportHandler.monthToMonth(transaction);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "2":
                                reportHandler.previousMonth(transaction);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "3":
                                reportHandler.yearToDate(transaction);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "4":
                                reportHandler.previousYear(transaction);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "5":
                                reportHandler.searchVendor(transaction,scanner);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "6":
                                reportHandler.customerSearch(transaction,scanner);
                                returningMessage("Returning back to Reports Screen");
                                break;
                            case "0":
                                returningMessage("Returning back to Ledger Screen");
                                LegerScreen();
                                return;
                            case "h":
                                returningMessage("Returning back to Home Screen");
                                return;
                            default:
                                System.out.println("\nInvalid choice, please try again");
                        }
                    } while (!userInput.equals("0"));
                    break;
                case "5":
                    returningMessage("Returning back to Home Screen");
                    return;
                default:
                    System.out.println("\nInvalid choice, please try again");
            }
        } while (!userInput.equals("5"));
    }

    public void returningMessage(String message){
        try {
            System.out.println("\n"+message+"...\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
