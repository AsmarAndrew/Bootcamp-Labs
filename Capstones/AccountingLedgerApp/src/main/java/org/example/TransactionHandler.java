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

    //This method will be called if transaction.csv needs to be read again, usually when it's updated.
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

    //This method adds the user's deposit
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


        //This if statement will check if either are blank it'll tell the user to try again.
        if (!vendor.isBlank() || !description.isBlank() || !amountString.isBlank()){

            //Reason for the try catch is because if the user entered a char instead of a number it would throw an error. So this prevents it.
            try {
                amount = Math.abs(Double.parseDouble(amountString));//Math.abs would make sure it's only a positive number.
            }catch (NumberFormatException ex){
                System.out.println("\n!! Please enter a valid number and try again !!");
                addDeposit(transactions); //Will loop back the method
            }

            //This try catch is writing the users input into the transaction.csv file.
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile,true))){
                writer.write(today.format(dateFormatter) +  "|"  + today.format(timeFormatter) +  "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
            }catch (IOException ex){
                System.out.println("Whoops couldn't find right file.");
            }
            //This method is called to update the Reader live to the user.
            transactionReader(transactions);
        }
        else {
            System.out.println("\n!!! One of your entries was left empty. Please try again. !!!\n");
            addDeposit(transactions);
        }


    }

    //This method adds the user's payment
    public void makePayment(List<Transactions> transactions){

        //Make Payment is very similar concept to add Deposit.

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
                if (amountString.startsWith("-")){ //This if statement will check if user did enter -
                    amountString = amountString.replace("-",""); //If they did it'll be blanked out
                }
                amount = -(Double.parseDouble(amountString)); //Reason for if statement is if the user doesn't enter one it'll still run a negative value.
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

    //This method runs all the transactions.
    public void allDisplay(List<Transactions> transactions){

        Screens screens = new Screens();
        footer("Transactions");
        int i = 0;
        int page = 1;
        int transcation = 1;
        String answer = "";

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));//Using this will sort using the fixedDates.

        for (Transactions display : transactions){

            int pages = (int)Math.ceil((double) transactions.size() /10); //This will tell you how many

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
                        endFooter("Transactions");
                    }

                    if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
            }
            i++;
            if (!answer.equalsIgnoreCase("no")) {
                System.out.println(transcation + ": Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: $" + display.getPrice());
                transcation++;
            }else {
                return;
            }
        }
        endFooter("Transactions");

    }

    //This method will only show deposits.
    public void depositDisplay(List<Transactions> transactions){

        footer("Deposit Made");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions){
            if (display.getPrice() > 0){
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }
        endFooter("Deposits");

    }

    //This method will only show payments.
    public void paymentDisplay(List<Transactions> transactions) {

        footer("Payments Made");


        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {
            if (display.getPrice() < 0) {
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        endFooter("Payments");

    }


    //Both are footers
    public void endFooter(String endOf){
        System.out.println("=============================================");
        System.out.println("            End of " + endOf);
        System.out.println("=============================================");
    }

    public void footer(String type){
        System.out.println("=============================================");
        System.out.println("             "+type);
        System.out.println("=============================================");
    }

}
