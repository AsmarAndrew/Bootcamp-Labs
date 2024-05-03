package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Screens {

    //Everything I needed to call

    Scanner scanner = new Scanner(System.in);
    TransactionHandler transactionHandler = new TransactionHandler();
    ReportHandler reportHandler = new ReportHandler();
    List<Transactions> transaction = new ArrayList<>();
    String userInput;

    //Home Screen Method

    public void HomeScreen(){

        //Reading the transcations.csv before user starts inputting.

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

            //Switch case to decide where to send user depending on their choice

            switch (userInput) {
                case "1":
                    transactionHandler.addDeposit(transaction);
                    returningMessage("Returning back to Home Screen"); //This method prints out a message with Thread.sleep
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
                    returningMessage("Exiting Program, Goodbye..");
                    returningMessage("\nWe'll see each other soon again..");
                    System.out.println("\u001B[32m⣿⣿⣿⣿⣿⠟⠋⠉⠀⠀⣀⠈⠉⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠉⠈⣀⠀⠀⠉⠙⠿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠉⠙⠓⢶⣭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣥⡶⠚⠋⠁⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⢁⣀⣤⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣤⣀⡘⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⢀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⠟⠛⣉⣉⣉⣏⣙⠛⠿⣿⣦⠈⣿⣿⣿⣿⣿⣿⣿⡟⢀⣴⡿⠿⠛⡋⣹⣍⣉⣉⠛⠻⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣧⠹⣿⣿⡟⠁⠔⠉⠁⠀⠀⠀⠀⠈⠐⠀⠙⣷⣼⢸⣿⣿⣿⣿⡇⣧⡾⠉⠀⠊⠁⠀⠀⠀⠀⠈⠙⠢⠈⢻⣿⣿⢏⣽⣿\n" +
                            "⣿⣿⠗⠈⠉⠀⠘⠤⠤⣀⣀⠀⡀⠀⠀⠤⠀⢀⣿⣿⢸⣿⣿⣿⣿⡇⣿⣷⡀⠠⠤⣀⡀⢀⡀⡀⡀⠤⠔⠃⢀⠉⠁⠺⢿⣿\n" +
                            "⡿⢃⣴⣾⣿⣿⣷⣶⣤⣤⣄⣠⣇⣤⣴⣶⣾⣿⣿⡇⢸⣿⣿⣻⣿⡇⢻⣿⣿⣷⣶⣦⣤⣼⣄⣤⣤⣤⣶⣾⣿⣿⣷⣦⡘⢿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⢸⡟⣿⣿⣿⡇⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⢸⣿⣿⣿⣿⡇⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⣠⣾⠀⣸⣿⣿⣿⣿⡇⢸⣦⡀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣏⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⣩⢁⣾⣿⣿⠀⣿⣿⣿⣿⣿⣧⠀⣿⣿⣦⢸⡇⠈⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⠏⣻⣿\n" +
                            "⣿⣿⡄⠈⢙⠛⠛⠛⢋⣉⣀⣤⣶⣾⣿⡌⣿⡟⢿⡀⠿⣿⣿⣿⣿⠟⢠⡿⠿⡟⣸⣿⣷⣦⣤⣈⣉⡙⠛⠛⠛⡋⠁⢰⣿⣿\n" +
                            "⣿⣿⣷⠀⠘⣦⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⡵⠦⣤⣭⣭⡤⠴⢥⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⢀⣾⠁⢀⣿⣿⣿\n" +
                            "⣿⣿⣿⣧⠀⠸⣷⡀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⢀⡀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⢠⣾⠃⢀⣾⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣧⠀⠙⣿⣄⠀⠈⠙⠛⠛⠉⠉⠉⠀⠀⠀⠀⣰⣿⣿⣄⠀⠀⠀⠀⠉⠉⠉⠙⠛⠋⠀⠀⣰⡿⠃⢀⣾⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣧⡀⠈⠻⣦⣄⣀⣀⣀⣀⣀⡀⠀⠀⠀⠾⠿⠿⠿⠿⠦⠀⠀⠀⣀⣀⣀⣀⣀⣀⣠⣾⠟⠀⢀⣾⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣷⣄⠐⣦⡙⠿⣿⣿⣿⣿⣿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣿⣿⣿⣿⣿⠟⣋⡴⠁⣠⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣦⡘⢿⣷⣶⣭⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣯⣭⣶⣾⡿⢁⣼⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⠻⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⠟⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡙⢿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⡿⢋⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "\n\u001B[0m");
                    break;
                default:
                    System.out.println("\n\u001B[31mInvalid choice, please try again\u001B[0m");
            }
        } while (!userInput.equals("4"));

    }

    //Leger Screen Method
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
                    ReportScreen();
                    return;
                case "5":
                    returningMessage("Returning back to Home Screen");
                    HomeScreen();
                    return;
                default:
                    System.out.println("\n\u001B[31mInvalid choice, please try again\u001B[0m");
            }
        } while (true);
    }

    //Report Screen Method
    public void ReportScreen(){
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
                    HomeScreen();
                    return;
                default:
                    System.out.println("\n\u001B[31mInvalid choice, please try again\u001B[0m");
            }
        } while (true);
    }

    //Once this method is called it'll print out the String that was given and add a second cool down before continuing program.
    public void returningMessage(String message){
        try {
            System.out.println("\n"+message+"...\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
