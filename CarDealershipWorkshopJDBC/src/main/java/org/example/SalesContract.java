package org.example;

import lombok.Getter;

@Getter
public class SalesContract extends Contract {
    private final boolean financeOption;

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice();
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            double interestRate = 0.05 / 12; // 5% annual interest rate divided by 12 months
            int termInMonths = 36; // 3-year term
            double loanAmount = getVehicleSold().getPrice();
            return (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -termInMonths));
        } else {
            return 0;
        }
    }
}