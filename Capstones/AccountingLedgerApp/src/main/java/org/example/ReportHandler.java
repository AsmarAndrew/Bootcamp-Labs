package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class ReportHandler {
    LocalDateTime today = LocalDateTime.now();

    public void monthToMonth(List<Transactions> transactions) {

        footer("Month to Date Transactions");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int month = Integer.parseInt(dateInput[1]);
            int year = Integer.parseInt(dateInput[0]);

            if (today.getMonthValue() == month && today.getYear() == year) //This will check if the month and year equals today if not doesn't print
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        endFooter("Report");



    }

    public void previousMonth(List<Transactions> transactions) {
        footer("Previous Month Transactions");


        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int month = Integer.parseInt(dateInput[1]);
            int year = Integer.parseInt(dateInput[0]);


            int prevMonth = today.getMonthValue() - 1;
            int prevYear = today.getYear();
            if (month == 12) { //This is just a precaution if the month is january.
                prevYear = prevYear - 1;
            }

            LocalDateTime previousMonth = LocalDateTime.of(prevYear, prevMonth, today.getDayOfMonth(), today.getHour(), today.getMinute());

            if (previousMonth.getMonthValue() == month && previousMonth.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        endFooter("Report");



    }

    public void yearToDate(List<Transactions> transactions) {
        footer("Year to Date Transactions");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int year = Integer.parseInt(dateInput[0]);

            if (today.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        endFooter("Report");


    }

    public void previousYear(List<Transactions> transactions) {
        footer("Previous Year Transactions");



        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int year = Integer.parseInt(dateInput[0]);
            int prevYear = today.getYear() - 1;

            LocalDateTime previousYear = LocalDateTime.of(prevYear, today.getMonthValue(), today.getDayOfMonth(), today.getHour(), today.getMinute());

            if (previousYear.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        endFooter("Report");


    }

    public void searchVendor(List<Transactions> transactions, Scanner scanner) {
        footer("Searching Vendor");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        System.out.println("Which Vendor would you like to search up?");
        String userInput = scanner.nextLine();

        for (Transactions display : transactions) {
            if (userInput.equalsIgnoreCase(display.getVendor())) {
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        endFooter("Report");
    }

    public void customerSearch(List<Transactions> transactions, Scanner scanner) {

        //This is the fun part
        //Made a new class for custom transcation.

        TransactionCustom transactionCustom = new TransactionCustom();

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        footer("Custom Search");
        System.out.println("Enter Start Date (YYYY-MM-DD):");
        String startDate = scanner.nextLine();
        System.out.println("Enter End Date (YYYY-MM-DD):");
        String endDate = scanner.nextLine();
        System.out.println("Enter Description:");
        String description = scanner.nextLine();
        System.out.println("Enter Vendor:");
        String vendor = scanner.nextLine();
        System.out.println("Enter Minimum Amount:");
        String minAmount = scanner.nextLine();
        System.out.println("Enter Maximum Amount:");
        String maxAmount = scanner.nextLine();


        //After collecting everything I'm checking whether the user input something blank or even something valid.
        //Cuz if it's empty it'll be set to nothing.
        if (!startDate.isEmpty()){
            try {
                transactionCustom.setDateMin(Long.parseLong(startDate.replaceAll("-", "")));
            }catch (NumberFormatException ex){
                System.out.println("Not a valid date please try again!");
                customerSearch(transactions,scanner);
            }

        }
        if (!endDate.isEmpty()){
            try {
                transactionCustom.setDateMax(Long.parseLong(endDate.replaceAll("-", "")));
            }catch (NumberFormatException ex){
                System.out.println("Not a valid date please try again!");
                customerSearch(transactions,scanner);
            }

        }
        if (!description.isEmpty()){
            transactionCustom.setDescription(description);
        }
        if (!vendor.isEmpty()){
            transactionCustom.setVendor(vendor);
        }
        if (!minAmount.isEmpty()){
            try {
                transactionCustom.setMinPrice(Double.parseDouble(minAmount));
            }catch (NumberFormatException ex){
                System.out.println("Not a valid number please try again!");
                customerSearch(transactions,scanner);
            }


        }
        if (!maxAmount.isEmpty()){
            try {
                transactionCustom.setMaxPrice(Double.parseDouble(maxAmount));
            }catch (NumberFormatException ex){
                System.out.println("Not a valid number please try again!");
                customerSearch(transactions,scanner);
            }

        }
        footer("Custom Search Results");

        for (Transactions display : transactions) {

            long date = Long.parseLong(display.getDate().replaceAll("-", "")); //Getting date of the for loop display


            //A whole if statement deciding what to print ;-;
            //Like bruh.
            //Prob easier or different way, but, yeah.

            if (transactionCustom.getMinPrice() <= display.getPrice() && transactionCustom.getMaxPrice() >= display.getPrice() &&
                    (transactionCustom.getDescription().isEmpty() || transactionCustom.getDescription().equalsIgnoreCase(display.getDescription())) &&
                    (transactionCustom.getVendor().isEmpty() ||  transactionCustom.getVendor().equalsIgnoreCase(display.getVendor())) &&
                transactionCustom.getDateMin() <= date && transactionCustom.getDateMax() >= date) {
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        endFooter("Results");


    }

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

