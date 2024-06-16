package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    @Getter
    private String name;
    @Getter
    private String address;
    @Getter
    private String phone;
    @Setter
    @Getter
    private List<Vehicle> inventory;
    @Getter
    @Setter
    private int dealership_id;

    private final DataManager dataManager;

    public Dealership(String name, String address, String phone, int dealership_id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dealership_id = dealership_id;
        this.inventory = new ArrayList<>();
        this.dataManager = new DataManager("jdbc:mysql://localhost:3306/dealerships", "root", "YUm15510n");
    }

    public Vehicle findVehicleByVIN(String VIN) {
        List<Vehicle> result = dataManager.getByVin(VIN, this.dealership_id);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public void getVehiclesByPrice(double min, double max) {
        var result = dataManager.getByPriceRange(min, max, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getVehiclesByMakeModel(String make, String model) {
        var result = dataManager.getByMakeAndModel(make, model, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getVehiclesByYear(int min, int max) {
        var result = dataManager.getByYear(min, max, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getVehiclesByColor(String color) {
        var result = dataManager.getByColor(color, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getVehiclesByMileage(double min, double max) {
        var result = dataManager.getByMileage(min, max, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getVehiclesByType(String vehicleType) {
        var result = dataManager.getByType(vehicleType, this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void getAllVehicles() {
        var result = dataManager.getAllVehicles(this.dealership_id);
        for (Vehicle vehicle : result) {
            System.out.println(vehicle);
        }
    }

    public void addVehicle(String vin, int year, String make, String model, String type, String color, double odometer, double price) {
        dataManager.addVehicle(vin, year, make, model, type, color, odometer, price, this.dealership_id);
        System.out.println("\nSuccessfully added");
    }

    public void removeVehicle(String vin) {
        dataManager.removeVehicle(vin, this.dealership_id);
        System.out.println("\nSuccessfully Removed");
    }

    public void addLease(String vin, String startDate, String endDate, String customerName, String customerEmail, String customerPhone, double monthlyPayment, double totalAmount) {
        dataManager.addLease(vin, startDate, endDate, customerName, customerEmail, customerPhone, monthlyPayment, totalAmount, this.dealership_id);
        System.out.println("\nSuccessfully Added Lease");
    }

    public void addSale(String vin, String contractDate, String customerName, String customerEmail, String customerPhone, boolean financeOption, double totalAmount) {
        dataManager.addSale(vin, contractDate, customerName, customerEmail, customerPhone, financeOption, totalAmount, this.dealership_id);
        System.out.println("\nSuccessfully Added Sale");
    }
}