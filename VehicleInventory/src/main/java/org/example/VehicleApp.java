package org.example;

import java.util.Scanner;

public class VehicleApp {
    public void listVehicles(Vehicle[] vehicles, int counter){
        System.out.println("\nList of all vehicles\n");
        for (int i = 0; i < counter; i++){
            if (vehicles[i] == null){
                System.out.println("No Vehicle");
            }else {
                System.out.println(vehicles[i].getVehicleId() + "," + vehicles[i].getMakeModel() + "," + vehicles[i].getColor() + "," + vehicles[i].getOdometerReading() + "," + vehicles[i].getPrice());
            }
        }
    }

    public void makeModel(Vehicle[] vehicles, int counter){
        System.out.println("\nSearch by Make / Model");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("\n");

        for (int i = 0; i < counter; i++){
            if (vehicles[i] == null){
                continue;
            }
            String search = vehicles[i].getMakeModel();
            if (search.equalsIgnoreCase(userInput)){
                System.out.println(vehicles[i].getVehicleId() + "," + vehicles[i].getMakeModel() + "," + vehicles[i].getColor() + "," + vehicles[i].getOdometerReading() + "," + vehicles[i].getPrice());
            }
        }
    }

    public void priceRange(Vehicle[] vehicles, int counter){
        System.out.println("\nSearch by Price Range:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nLowest Price Looking for: ");
        float lowestPrice = scanner.nextInt();
        System.out.println("Highest Price looking for: ");
        float highestPrice = scanner.nextInt();
        System.out.println("\n");
        Vehicle[] vehiclesInRange = new Vehicle[counter];

        int index = 0;
        for (int i = 0; i < counter; i++){
            if(vehicles[i] == null){
                continue;
            }
            float search = vehicles[i].getPrice();
            if(search >= lowestPrice && search <= highestPrice){
                vehiclesInRange[index] =  vehicles[i];
                index++;
            }
        }

        for(int i = 0; i < index - 1; i++){
            for (int j = 0; j < index - i - 1; j++){
                if (vehiclesInRange[j].getPrice() > vehiclesInRange[j + 1].getPrice()){
                    Vehicle temp = vehiclesInRange[j];
                    vehiclesInRange[j] = vehiclesInRange[j + 1];
                    vehiclesInRange[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < index; i++){
            System.out.println(vehiclesInRange[i].getVehicleId() + "," + vehiclesInRange[i].getMakeModel() + "," + vehiclesInRange[i].getColor() + "," + vehiclesInRange[i].getOdometerReading() + "," + vehiclesInRange[i].getPrice());
        }

    }

    public void color(Vehicle[] vehicles, int counter){
        System.out.println("\nSearch by Color:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("\n");

        for (int i = 0; i < counter; i++){
            if (vehicles[i] == null){
                continue;
            }
            String search = vehicles[i].getColor();
            if (search.equalsIgnoreCase(userInput)){
                System.out.println(vehicles[i].getVehicleId() + "," + vehicles[i].getMakeModel() + "," + vehicles[i].getColor() + "," + vehicles[i].getOdometerReading() + "," + vehicles[i].getPrice());
            }
        }
    }
    public Vehicle addVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle ID: ");
        long vehicleId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter make/model: ");
        String makeModel = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter odometer reading: ");
        int odometerReading = scanner.nextInt();
        System.out.print("Enter price: ");
        float price = scanner.nextFloat();

        return new Vehicle(vehicleId, makeModel, color, odometerReading, price);
    }

    
}
