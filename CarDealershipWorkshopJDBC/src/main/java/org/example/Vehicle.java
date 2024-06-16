package org.example;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vehicle {
    String vin;
    int year;
    String make;
    String model;
    String vehicleType;
    String color;
    double odometer;
    double price;

    public Vehicle(String vin, int year, String make, String model, String vehicleType, String color, double odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format(vin + "|" + year + "|" + make + "|" + model + "|" + vehicleType + "|" + color + "|" + odometer + "|" + price);
    }
}
