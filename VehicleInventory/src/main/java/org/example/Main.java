package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        //vehicleId,makeModel,color,odometerReading,price
        vehicles[0] = new Vehicle(101121,"Ford Explorer","Red",45000,13500);
        vehicles[1] = new Vehicle(101122,"Toyota Camry","Blue",60000,11000);
        vehicles[2] = new Vehicle(101123,"Chevrolet Malibu","Black",50000,9700);
        vehicles[3] = new Vehicle(101124,"Honda Civic","White",70000,7500);
        vehicles[4] = new Vehicle(101125,"Subaru Outback","Green",55000,14500);
        vehicles[5] = new Vehicle(101126,"Jeep Wrangler","Yellow",30000,16000);

        Scanner scanner = new Scanner(System.in);
        int counter = vehicles.length;
        int counter1 = 6;
        VehicleApp vehicle = new VehicleApp();
        while (true){
            System.out.println("\nWhat do you want to do?");
            System.out.println("1 - List all vehicles");
            System.out.println("2 - Search by make/model");
            System.out.println("3 - Search by price range");
            System.out.println("4 - Search by color");
            System.out.println("5 - Add a vehicle");
            System.out.println("6 - Quit");
            System.out.print("\nEnter your command: ");
            int userInput = scanner.nextInt();


            if (userInput == 1){
                vehicle.listVehicles(vehicles,counter);
            } else if (userInput == 2) {
                vehicle.makeModel(vehicles,counter);
            } else if (userInput == 3) {
                vehicle.priceRange(vehicles,counter);
            } else if (userInput == 4) {
                vehicle.color(vehicles,counter);
            } else if (userInput == 5) {
                if(counter1 < counter){
                    vehicles[counter1] = vehicle.addVehicle();
                    counter1++;
                    System.out.println("Vehicle added successfully!." );
                }
                else {
                    System.out.println("Cannot add more vehicles! It's full lot.");
                }
            } else if (userInput == 6) {
                break;
            }

        }
    }

}
