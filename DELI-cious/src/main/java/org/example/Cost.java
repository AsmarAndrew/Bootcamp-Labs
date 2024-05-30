package org.example;

import java.util.Map;

public class Cost {

    /*

    This class is just dealing with cost of items at the Deli Shop.

     */

    public static double getMeatCost(int size) {
        switch (size) {
            case 4:
                return 1.00;
            case 8:
                return 2.00;
            case 12:
                return 3.00;
            default:
                throw new IllegalArgumentException("Invalid bread size");
        }
    }

    public static double getExtraMeatCost(int size) {
        switch (size) {
            case 4:
                return 0.50;
            case 8:
                return 1.00;
            case 12:
                return 1.50;
            default:
                throw new IllegalArgumentException("Invalid bread size");
        }
    }

    public static double getCheeseCost(int size) {
        switch (size) {
            case 4:
                return 0.75;
            case 8:
                return 1.50;
            case 12:
                return 2.25;
            default:
                throw new IllegalArgumentException("Invalid bread size");
        }
    }

    public static double getExtraCheeseCost(int size) {
        switch (size) {
            case 4:
                return 0.38;
            case 8:
                return 0.75;
            case 12:
                return 0.90;
            default:
                throw new IllegalArgumentException("Invalid bread size");
        }
    }

    public static double getBreadCost(int size) {
        switch (size) {
            case 4:
                return 5.50;
            case 8:
                return 7.00;
            case 12:
                return 8.50;
            default:
                throw new IllegalArgumentException("Invalid bread size");
        }
    }

    public static double getDrinkCost(Inventory.drinks drink, Inventory.drinkSize size) {
        switch (drink) {
            case SODA:
                switch (size) {
                    case SMALL:
                        return 2.00;
                    case MEDIUM:
                        return 2.50;
                    case LARGE:
                        return 3.00;
                }
            case JUICE:
                switch (size) {
                    case SMALL:
                        return 2.00;
                    case MEDIUM:
                        return 2.50;
                    case LARGE:
                        return 3.00;
                }
            case WATER:
                switch (size) {
                    case SMALL:
                        return 2.00;
                    case MEDIUM:
                        return 2.50;
                    case LARGE:
                        return 3.00;
                }
            default:
                throw new IllegalArgumentException("Invalid drink type or size");
        }
    }

    public static double getChipsCost(Inventory.chips chips) {
        switch (chips) {
            case REGULAR:
                return 1.50;
            case BBQ:
                return 1.50;
            case SOUR_CREAM_ONION:
                return 1.50;
            case SALT_VINEGAR:
                return 1.50;
            default:
                throw new IllegalArgumentException("Invalid chips type");
        }
    }

    /*

    This is to handle cost with the Extra's being added to them.

     */

    public static double calculateMeatCost(int size, Map<Inventory.meats, Integer> meatCounts) {
        double totalCost = 0;
        for (Map.Entry<Inventory.meats, Integer> entry : meatCounts.entrySet()) {
            int count = entry.getValue();
            totalCost += getMeatCost(size);
            if (count > 1) {
                totalCost += (count - 1) * getExtraMeatCost(size);
            }
        }
        return totalCost;
    }

    public static double calculateCheeseCost(int size, Map<Inventory.cheese, Integer> cheeseCounts) {
        double totalCost = 0;
        for (Map.Entry<Inventory.cheese, Integer> entry : cheeseCounts.entrySet()) {
            int count = entry.getValue();
            totalCost += getCheeseCost(size);
            if (count > 1) {
                totalCost += (count - 1) * getExtraCheeseCost(size);
            }
        }
        return totalCost;
    }
}