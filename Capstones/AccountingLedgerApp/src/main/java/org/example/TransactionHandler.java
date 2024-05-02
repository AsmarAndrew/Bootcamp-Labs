package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TransactionHandler {

    Scanner scanner = new Scanner(System.in);
    String transactionFile = "src/main/resources/transactions.csv";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void transactionReader(List<Transactions> transaction){
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            transaction.clear();
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split("\\|");
                String date = data[0];
                String time = data[1];
                String description = data[2];
                String vendor = data[3];
                double price = Double.parseDouble(data[4]);

                Transactions transactions = new Transactions(date,time,description,vendor,price);
                transaction.add(transactions);
            }
        }
        catch (IOException ex){
            System.out.println("Oops! It seems we couldn't find the file you're looking for.");
        }
    }

    public void addDeposit(List<Transactions> transactions){

        LocalDateTime today = LocalDateTime.now();

        System.out.println("\n=== Entering Deposit ===");
        System.out.println("\nEnter Vendor:");
        String vendor = scanner.nextLine();
        System.out.println("\nEnter Description:");
        String description = scanner.nextLine();
        System.out.println("\nEnter Amount:");
        String amountString = scanner.nextLine();
        double amount = 0;



        if (!vendor.isBlank() || !description.isBlank() || !amountString.isBlank()){

            try {
                amount = Math.abs(Double.parseDouble(amountString));
            }catch (NumberFormatException ex){
                System.out.println("\n!! Please enter a valid number and try again !!");
                addDeposit(transactions);
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile,true))){
                writer.write(today.format(dateFormatter) +  "|"  + today.format(timeFormatter) +  "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
            }catch (IOException ex){
                System.out.println("Whoops couldn't find right file.");
            }

            transactionReader(transactions);

        }
        else {
            System.out.println("\n!!! One of your entries was left empty. Please try again. !!!\n");
            addDeposit(transactions);
        }
    }

    public void makePayment(List<Transactions> transactions){

        LocalDateTime today = LocalDateTime.now();

        System.out.println("\n=== Enter Payment ===");
        System.out.println("\nEnter Vendor:");
        String vendor = scanner.nextLine();
        System.out.println("\nEnter Description:");
        String description = scanner.nextLine();
        System.out.println("\nEnter Amount:");
        String amountString = scanner.nextLine();
        double amount = 0;

        if (!vendor.isBlank() || !description.isBlank() || !amountString.isBlank()){

            try {
                amount = -(Double.parseDouble(amountString));
            }catch (NumberFormatException ex){
                System.out.println("\n!! Please enter a valid number and try again !!");
                addDeposit(transactions);
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile,true))){
                writer.write(today.format(dateFormatter) +  "|"  + today.format(timeFormatter) +  "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
            }catch (IOException ex){
                System.out.println("Whoops couldn't find right file.");
            }

            transactionReader(transactions);

        }
        else {
            System.out.println("\n!!! One of your entries was left empty. Please try again. !!!\n");
            addDeposit(transactions);
        }

    }

    public void allDisplay(List<Transactions> transactions){
        Screens screens = new Screens();
        System.out.println("=============================================");
        System.out.println("                 Transactions                ");
        System.out.println("=============================================");
        int i = 0;
        int page = 1;
        int transcation = 1;
        String answer;
        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions){

            int pages = (int)Math.ceil((double) transactions.size() /10);

            if (i == 10){
                System.out.println("This is Page " + page + " out of " + pages);
                do {
                    System.out.println("Would you like to see next page? (Yes/No)");
                    answer = scanner.nextLine().toLowerCase();
                    if (answer.equalsIgnoreCase("yes")){
                        page++;
                        i = 0;
                    }
                    if (answer.equalsIgnoreCase("no")){
                        System.out.println("=============================================");
                        System.out.println("            End of Transactions              ");
                        System.out.println("=============================================");
                        screens.returningMessage("Returning back to Ledger Screen");
                        screens.LegerScreen();

                    }

                    if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
            }
            i++;
            System.out.println(transcation + ": Date: " + display.getDate() + " Time: "
                    + display.getTime() + " Description: " + display.getDescription()
                    + " Vendor: " + display.getVendor() + " Price: $" + display.getPrice());
            transcation++;
        }
        System.out.println("=============================================");
        System.out.println("            End of Transactions              ");
        System.out.println("=============================================");




    }

    public void depositDisplay(List<Transactions> transactions){

        System.out.println("=============================================");
        System.out.println("             Deposits Made                    ");
        System.out.println("=============================================");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions){
            if (display.getPrice() > 0){
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        System.out.println("=============================================");
        System.out.println("            End of Deposits                   ");
        System.out.println("=============================================");

    }

    public void paymentDisplay(List<Transactions> transactions) {

        System.out.println("=============================================");
        System.out.println("             Payments Made                    ");
        System.out.println("=============================================");


        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {
            if (display.getPrice() < 0) {
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        System.out.println("=============================================");
        System.out.println("            End of Payments                   ");
        System.out.println("=============================================");

    }

}
