package org.example;

import java.time.LocalDateTime;
import java.util.*;

public class ReportHandler {
    LocalDateTime today = LocalDateTime.now();

    public void monthToMonth(List<Transactions> transactions) {

        System.out.println("=============================================");
        System.out.println("        Month to Date Transactions           ");
        System.out.println("=============================================");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int month = Integer.parseInt(dateInput[1]);
            int year = Integer.parseInt(dateInput[0]);

            if (today.getMonthValue() == month && today.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        System.out.println("=============================================");
        System.out.println("             End of Report                   ");
        System.out.println("=============================================");


    }

    public void previousMonth(List<Transactions> transactions) {

        System.out.println("=============================================");
        System.out.println("            Previous Month Transactions      ");
        System.out.println("=============================================");


        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int month = Integer.parseInt(dateInput[1]);
            int year = Integer.parseInt(dateInput[0]);


            int prevMonth = today.getMonthValue() - 1;
            int prevYear = today.getYear();
            if (month == 12) {
                prevYear = prevYear - 1;
            }

            LocalDateTime previousMonth = LocalDateTime.of(prevYear, prevMonth, today.getDayOfMonth(), today.getHour(), today.getMinute());

            if (previousMonth.getMonthValue() == month && previousMonth.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        System.out.println("=============================================");
        System.out.println("             End of Report                   ");
        System.out.println("=============================================");



    }

    public void yearToDate(List<Transactions> transactions) {

        System.out.println("=============================================");
        System.out.println("          Year to Date Transactions          ");
        System.out.println("=============================================");

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        for (Transactions display : transactions) {

            String[] dateInput = display.getDate().split("-");
            int year = Integer.parseInt(dateInput[0]);

            if (today.getYear() == year)
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
        }

        System.out.println("=============================================");
        System.out.println("             End of Report                   ");
        System.out.println("=============================================");


    }

    public void previousYear(List<Transactions> transactions) {

        System.out.println("=============================================");
        System.out.println("         Previous Year Transactions          ");
        System.out.println("=============================================");


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

        System.out.println("=============================================");
        System.out.println("             End of Report                   ");
        System.out.println("=============================================");


    }

    public void searchVendor(List<Transactions> transactions, Scanner scanner) {
        System.out.println("=============================================");
        System.out.println("             Searching Vendor                ");
        System.out.println("=============================================");

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

        System.out.println("=============================================");
        System.out.println("             End of Report                   ");
        System.out.println("=============================================");
    }

    public void customerSearch(List<Transactions> transactions, Scanner scanner) {

        TransactionCustom transactionCustom = new TransactionCustom();

        transactions.sort(Comparator.comparingLong(Transactions::fixedDate));
        System.out.println("=============================================");
        System.out.println("             Custom Search                   ");
        System.out.println("=============================================");
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

        System.out.println("=============================================");
        System.out.println("             Custom Search Results            ");
        System.out.println("=============================================");

        for (Transactions display : transactions) {

            long date = Long.parseLong(display.getDate().replaceAll("-", ""));


            if (transactionCustom.getMinPrice() <= display.getPrice() && transactionCustom.getMaxPrice() >= display.getPrice() &&
                    (transactionCustom.getDescription().isEmpty() || transactionCustom.getDescription().equalsIgnoreCase(display.getDescription())) &&
                    (transactionCustom.getVendor().isEmpty() ||  transactionCustom.getVendor().equalsIgnoreCase(display.getVendor())) &&
                transactionCustom.getDateMin() <= date && transactionCustom.getDateMax() >= date) {
                System.out.println("Date: " + display.getDate() + " Time: "
                        + display.getTime() + " Description: " + display.getDescription()
                        + " Vendor: " + display.getVendor() + " Price: " + display.getPrice());
            }
        }

        System.out.println("=============================================");
        System.out.println("             End of Transactions             ");
        System.out.println("=============================================");


    }
}

