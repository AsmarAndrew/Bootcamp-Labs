package org.example;

import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    @Setter
    private Dealership dealership;
    private final DataManager dataManager = new DataManager("jdbc:mysql://localhost:3306/dealerships", "root", "YUm15510n");

    public UserInterface() {
        this.dealership = null;
    }

    public void getDealership() {
        List<Dealership> dealerships = dataManager.getAllDealerships();
        System.out.println("Howdy! Pick a dealership! Careful! Don't pick the wrong one...");

        for (int i = 0; i < dealerships.size(); i++) {
            System.out.println((i + 1) + ". " + dealerships.get(i).getName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= dealerships.size()) {
            setDealership(dealerships.get(choice - 1));
        } else {
            System.out.println("Invalid choice. Please try again.");
            getDealership();
        }
    }

    public void display() {
        while (true) {

            System.out.println("""
                \\______________________/
                  __/__|_________|__\\__
                /⭕⭕____________⭕⭕\\
                |_____/___________\\_____|
                \\💥💥__|_|_|__|_|__💥💥/
                
                       -Vroooom-
                
                Welcome welcome!!
                1. Add a vehicle.
                2. Display our stock!
                3. Check out our cars by their pricing!
                4. Display our cars by make & model!!
                5. Check them out by year!
                6. Or by color?
                7. Maybe you want ta see 'em by mileage?
                8. Look at 'em all by type!
                9. Remove a vehicle
                0. Buy or Lease vehicle
                99. Hey hey where you goin'!??! (Exit)
                
                What you lookin' for?""");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    processAddVehicleRequest();
                    break;
                case 2:
                    processGetAllVehiclesRequest();
                    break;
                case 3:
                    processGetByPriceRequest();
                    break;
                case 4:
                    processGetByMakeModelRequest();
                    break;
                case 5:
                    processGetByYearRequest();
                    break;
                case 6:
                    processGetByColorRequest();
                    break;
                case 7:
                    processGetByMileage();
                    break;
                case 8:
                    processGetByVehicleTypeRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    sellLeaseCar();
                    break;
                case 99:
                    System.out.println("\nSo Long SUCKER (Exiting)");
                    return;
                default:
                    System.out.println("\nMake some SENSE! (Invalid Choice)");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.println("How low you willin' to go?(Minimum Price):\n");
        double minPrice = scanner.nextDouble();
        System.out.println("Whats your max budget lookin' like?(Maximum Price):");
        double maxPrice = scanner.nextDouble();

        dealership.getVehiclesByPrice(minPrice, maxPrice);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter make: ");
        String make = scanner.next();
        System.out.println("Enter model: ");
        String model = scanner.next();

        dealership.getVehiclesByMakeModel(make, model);
    }

    public void processGetByYearRequest() {
        System.out.println("Enter Minimum Year: ");
        int minYear = scanner.nextInt();
        System.out.println("Enter Maximum Year: ");
        int maxYear = scanner.nextInt();

        dealership.getVehiclesByYear(minYear, maxYear);
    }

    public void processGetByColorRequest() {
        System.out.println("Enter Color: ");
        String color = scanner.next();

        dealership.getVehiclesByColor(color);
    }

    public void processGetByMileage() {
        System.out.println("Enter Min Mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.println("Enter Max Mileage: ");
        double maxMileage = scanner.nextDouble();

        dealership.getVehiclesByMileage(minMileage, maxMileage);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter Type: ");
        String type = scanner.next();

        dealership.getVehiclesByType(type);
    }

    public void processGetAllVehiclesRequest() {
        dealership.getAllVehicles();
    }

    public void processAddVehicleRequest() {
        System.out.println("Enter VIN:");
        String vin = scanner.next();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        System.out.println("Enter make:");
        String make = scanner.next();
        System.out.println("Enter model:");
        String model = scanner.next();
        System.out.println("Enter vehicle type:");
        String vehicleType = scanner.next();
        System.out.println("Enter color:");
        String color = scanner.next();
        System.out.println("Enter odometer reading:");
        int odometer = scanner.nextInt();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        dealership.addVehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }

    public void processRemoveVehicleRequest() {
        System.out.println("Please enter VIN Number: ");
        String vinNumber = scanner.next();
        scanner.nextLine();

        dealership.removeVehicle(vinNumber);
    }

    public void sellLeaseCar() {
        System.out.println("Welcome to CarDealership! What would you like to do today?");
        System.out.println("\t1) Lease a shiny new ride");
        System.out.println("\t2) Buy your dream car");
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                leaseVehicle();
                break;
            case 2:
                sellVehicle();
                break;
            default:
                System.out.println("\nOops! That's not a valid choice. Please try again and make some SENSE!");
        }
    }

    public void sellVehicle() {
        scanner.nextLine();

        System.out.println("Let's get you that dream car! First, we need some info.");
        System.out.println("Enter the vehicle's VIN (Very Important Number):");
        String VIN = scanner.nextLine();

        Vehicle vehicle = dealership.findVehicleByVIN(VIN);

        if (vehicle == null) {
            System.out.println("Hmm... We can't seem to find a car with that VIN. Did you type it correctly?");
            return;
        }

        System.out.println("Great choice! Now, let's get down to business.");
        System.out.println("Enter the contract date (YYYY-MM-DD):");
        String dateOfContract = scanner.nextLine();

        System.out.println("Enter the customer's name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter the customer's email (for sending important updates):");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter the customer's phone (for sending car memes and important stuff):");
        String customerPhone = scanner.nextLine();

        System.out.println("Do you want to finance your new beauty? (yes/no):");
        String financeOption = scanner.nextLine();
        boolean booleanFinance = financeOption.equalsIgnoreCase("yes");

        SalesContract salesContract = new SalesContract(dateOfContract, customerName, customerEmail, vehicle, booleanFinance);
        dealership.addSale(VIN, dateOfContract, customerName, customerEmail, customerPhone, booleanFinance, salesContract.getTotalPrice());
    }

    public void leaseVehicle() {
        scanner.nextLine();

        System.out.println("Leasing a car? Smart choice! Let's get started.");
        System.out.println("Enter the vehicle's VIN (Very Important Number):");
        String VIN = scanner.nextLine();

        Vehicle vehicle = dealership.findVehicleByVIN(VIN);

        if (vehicle == null) {
            System.out.println("Oops! We couldn't find a car with that VIN. Are you sure you typed it correctly?");
            return;
        }

        System.out.println("Awesome! Let's move on to the details.");
        System.out.println("Enter the contract date (YYYY-MM-DD):");
        String dateOfContract = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateOfContract);
        LocalDate endDate = date.plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String endDateString = endDate.format(formatter);

        System.out.println("Enter the customer's name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter the customer's email (for sending important updates):");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter the customer's phone (for sending car memes and important stuff):");
        String customerPhone = scanner.nextLine();

        LeaseContract leaseContract = new LeaseContract(dateOfContract, customerName, customerEmail, vehicle);
        dealership.addLease(VIN, dateOfContract, endDateString, customerName, customerEmail, customerPhone, leaseContract.getMonthlyPayment(), leaseContract.getTotalPrice());
    }
}