package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Making all Variables
        Scanner scanner = new Scanner(System.in);
        String datePickup = "";
        int daysRented = 0;
        boolean loop = true;
        boolean tollTag = false;
        boolean GPS = true;
        boolean roadsideAs = true;
        int age = 0;
        double basicCar = 29.99;
        double optionCost = 0.0;
        double underAge = 0.0;
        double totalCost = 0.0;

        //Statement
        System.out.println("Welcome To Andrew's 5-Star Spectacular Car Rental Company!");
        System.out.println("We'll need you to fill out some information about your rental of the Porsche 911.");
        //Collecting Pickup
        System.out.print("Date of pickup: ");
        datePickup = scanner.nextLine();
        //Collecting Rental Days
        System.out.print("How many days will you need for the rental?: ");
        daysRented = scanner.nextInt();
        scanner.nextLine();

        //Making a Loop to see if user agrees with buying toll tag
        while (loop == true) {
            System.out.print("Would you like to add electronic toll tag for an additional $3.95/day: (yes or no) ");
            String YN = scanner.nextLine();
            if (YN.equalsIgnoreCase("yes") || YN.equalsIgnoreCase("y")) {
                tollTag = true;
                loop = false;
            } else if (YN.equalsIgnoreCase("no") || YN.equalsIgnoreCase("n")) {
                tollTag = false;
                loop = false;
            } else {
                System.out.println("Please enter a valid answer.");
                loop = true;
            }
        }loop = true;
        //Making a Loop to see if user agrees with buying GPS
        while (loop == true) {
            System.out.print("Would you want a GPS for an additional $2.95/day: (yes or no) ");
            String YN = scanner.nextLine();
            if (YN.equalsIgnoreCase("yes") || YN.equalsIgnoreCase("y")) {
                GPS = true;
                loop = false;
            } else if (YN.equalsIgnoreCase("no") || YN.equalsIgnoreCase("n")) {
                GPS = false;
                loop = false;
            } else {
                System.out.println("Please enter a valid answer.");
                loop = true;
            }
        }loop = true;
        //Making a Loop to see if user agrees with buying Roadside Assistance
        while (loop == true) {
            System.out.print("Would you want roadside assistance for an additional $3.95/day: (yes or no) ");
            String YN = scanner.nextLine();
            if (YN.equalsIgnoreCase("yes") || YN.equalsIgnoreCase("y")) {
                roadsideAs = true;
                loop = false;
            } else if (YN.equalsIgnoreCase("no") || YN.equalsIgnoreCase("n")) {
                roadsideAs = false;
                loop = false;
            } else {
                System.out.println("Please enter a valid answer.");
                loop = true;
            }
        }loop = true;
        //Collecting Age
        System.out.print("Current Age: ");
        age = scanner.nextInt();
        System.out.println("\n\nAmazing you're almost done:");
        //BasicCar total
        basicCar = (basicCar * daysRented);
        //Judging how much they need to pay.
        if (age < 25){
            underAge = (basicCar * 0.3);
        } else if (age >= 25) {
            underAge = 0.0;
        }
        //Judging if they bought one of the optionals if so add it to optionCost
        if (tollTag == true){
            optionCost += (3.95 * daysRented);
        }
        if (GPS == true){
            optionCost += (2.95 * daysRented);
        }
        if (roadsideAs == true){
            optionCost += (3.95 * daysRented);
        }

        //Total cost
        totalCost = (basicCar + optionCost + underAge);

        //Formatting my decimals
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedTotalCost = df.format(totalCost);
        String formattedUnderAge = df.format(underAge);
        String formattedOptionCost = df.format(optionCost);

        //Printing everything
        System.out.println("\nAndrew's 5-Star Spectacular Car Rental Company Invoice:");
        System.out.println("Basic Car Rental: $" + basicCar);
        //This is so if they said no to everything doesn't print
        if (optionCost > 0.0){
            System.out.println("Optional Cost: $" + formattedOptionCost);
        }
        //This is so if they said no to everything doesn't print
        if (underAge > 0.0){
            System.out.println("Underage Driver Surcharge $" + formattedUnderAge);
        }
        System.out.println("Total Cost: $" + formattedTotalCost);
        System.out.println("\nThank you for choosing Andrew's 5-star Spectacular Car Rental Company.");

    }
}

/*
1. Welcome the Customer to Andrew's 5-Star Spectacular Car Rental Company
2. Tell the customer to enter today's date before starting, (collect it String)
3. Ask the customer how many days they'll need to rent the Porsche 911 for (collect it int)
4. Ask the customer if they would like to have an electronic toll tag for an additional $3.95/day (collect it boolean)
5. Ask the customer if they would like to have GPS for an additional $2.95/day (collect it boolean)
6. Ask the customer if they would like roadside assistance for an additional $3.95 (collect it boolean)
7. Finally ask customer for their age.

8. To Calculate and Display the total cost first we need to take the amount of days times the basic car rental day (29.99)
9. Next we'll need the total cost of options cost so we can see whether the customer said yes to the optional cost, and if so take that value times the days rented.
10. Next, we'll see if the customer is under the age of 25, if not the result will be 0 if the customer adds an additional
11. Last, the total cost will be what the last 3 totals are.

12. Tell the customer their total, ask for a payment, and tell them to have a good rest of their day.mak
 */