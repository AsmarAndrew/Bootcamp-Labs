package org.example;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Contract {
    private String dateOfContract;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}