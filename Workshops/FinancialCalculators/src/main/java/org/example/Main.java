package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //defining needed variables for this function
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);


        System.out.println("==Welcome to Financing with Andrew!==");
        //Created a while loop so that the user can explore different calculators in one session and can quit whenever.
        while (loop == true) {
            //Giving user options to pick from
            System.out.println("\nSelect calculator of choice!");
            System.out.println("(1) Mortgage Calculator");
            System.out.println("(2) Future Value");
            System.out.println("(3) Present Value");
            System.out.println("(4) Quit Program");
            System.out.print("\nEnter Option: ");
            String choice = scanner.nextLine();

            //If Statements to decided which choice the user selected.
            if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("Mortgage") || choice.equalsIgnoreCase("Mortgage Calculator")) {
                MortgageCalculator option1 = new MortgageCalculator();
                option1.handleOption();
            } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("Future") || choice.equalsIgnoreCase("Future Value")) {
                FutureValue option2 = new FutureValue();
                option2.handleOption();
            } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("Present") || choice.equalsIgnoreCase("Present Value")) {
                PresentValue option3 = new PresentValue();
                option3.handleOption();
            } else if (choice.equalsIgnoreCase("4") || choice.equalsIgnoreCase("Quit") || choice.equalsIgnoreCase("Quit Program")) {
                loop = false;
            }else {
                System.out.println("Please enter a valid option.");
            }
        }loop = true;

    }
}

//Different class to be called if user picks Mortgage
class MortgageCalculator {
    public void handleOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mortgage Calculator");
        System.out.println("\nThis calculator is a tool used to estimate monthly " +
                "mortgage payments \nbased on factors such as loan amount, interest rate, and loan term. ");

        //Collecting the variables from user.
        System.out.println("\nCollect Detail:\n");
        System.out.print("Loan Amount (Principal): ");
        double principal = scanner.nextDouble();
        System.out.print("Interest Rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Loan Length (Years): ");
        double loanLength = scanner.nextDouble();

        //This section is to make my life a little easier to read.
        double annualInterest = interestRate / 100;
        double monthlyInterest = annualInterest / 12;
        double monthLength = loanLength * 12;
        double x = Math.pow(1 + monthlyInterest, monthLength);

        //Figuring out their monthly payment & total interest.
        double monthlyPayment = ((principal * monthlyInterest * x) / (x - 1));
        double totalPayment = monthlyPayment * monthLength;
        double totalInterest = totalPayment - principal;

        //Formatting result to two decimals.
        DecimalFormat df = new DecimalFormat("#.##");

        //Printing Results
        System.out.println("\n\n ===== Results =====");
        System.out.println("\nMonthly Payment: $" + df.format(monthlyPayment));
        System.out.println("Total Interest: $" + df.format(totalInterest));
    }
}

//Different class to be called if user picks Future Value
class FutureValue {
    public void handleOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Future Value Calculator");
        System.out.println("\nThis calculator is a financial tool that estimates the value of an investment or asset \n" +
                "at a specified future date based on factors such as initial investment, interest rate, and compounding period.");

        //Collecting the variables from user.
        System.out.println("\nCollect Detail:\n");
        System.out.print("Deposit: ");
        double deposit = scanner.nextDouble();
        System.out.print("Interest Rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Number of Years: ");
        double numberYears = scanner.nextDouble();

        //Figuring out their future value & earned value.
        double futureValue = deposit * Math.pow((1+((interestRate/100)/365)), (365*numberYears));
        double earned = futureValue - deposit;

        //Formatting result to two decimals.
        DecimalFormat df = new DecimalFormat("#.##");

        //Printing Results
        System.out.println("\n\n ===== Results =====");
        System.out.println("\nFuture Value: $" + df.format(futureValue));
        System.out.println("Total Interest Earned: $" + df.format(earned));
    }
}

//Different class to be called if user picks Present Value
class PresentValue{
    public void handleOption(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Present Value Calculator");
        System.out.println("\nThis calculator calculator determines the current worth of a future sum of money by discounting\n" +
                " it back to its equivalent value in today's dollars based on a specified interest rate and time period.");

        //Collecting the variables from user.
        System.out.println("\nCollect Detail:\n");
        System.out.print("Monthly Payout: ");
        double monthlyPay = scanner.nextDouble();
        System.out.print("Expected Interest Rate: ");
        double expInterestRate = scanner.nextDouble();
        System.out.print("Years to out: ");
        double numberYears = scanner.nextDouble();

        //Figuring out their present value.
        double interestRate = expInterestRate / 100;
        double x = interestRate / 12;
        double x1 = Math.pow((1 + x), -(12 * numberYears));
        double total = monthlyPay * ((1-x1)/x);

        //Formatting result to two decimals.
        DecimalFormat df = new DecimalFormat("#.##");

        //Printing Results
        System.out.println("\n\n ===== Results =====");
        System.out.println("\nYou would need to invest: $" + df.format(total));
    }
    
}