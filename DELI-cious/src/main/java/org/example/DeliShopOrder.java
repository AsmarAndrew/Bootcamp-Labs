package org.example;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class DeliShopOrder {

    // Initializing
    private Scanner scanner = new Scanner(System.in);
    private SandwichBuilder.Builder sandwichBuilder = new SandwichBuilder.Builder();
    private SidesBuilder.Builder sidesBuilder = new SidesBuilder.Builder();
    private boolean hasSandwich = false;
    private boolean hasDrink = false;
    private boolean hasChips = false;

    // First 5 Methods are for Handling The UserInterface

    public void addSandwich() {
        sandwichBuilder.setBread(selectBread());
        sandwichBuilder.setSize(selectSize());
        sandwichBuilder.setToasted(isToasted());
        addMeats();
        addCheese();
        addToppings();
        addSauces();
        hasSandwich = true;
    }

    public void addDrink() {
        Inventory.drinks drink = selectDrink();
        Inventory.drinkSize size = selectDrinkSize();
        sidesBuilder.addDrink(drink, size);
        hasDrink = true;
    }

    public void addChips() {
        Inventory.chips chips = selectChips();
        sidesBuilder.addChips(chips);
        hasChips = true;
    }

    public void checkout() {
        if (!hasSandwich && !hasDrink && !hasChips) {
            System.out.println("\n\nYou must add at least a sandwich, drink, or chips to your order.");
            return;
        }

        while (true) {
            displayOrder();
            System.out.println("\nDo you want to remove anything? (yes/no)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                removeItem();
            } else {
                break;
            }
        }

        SandwichBuilder sandwich = hasSandwich ? sandwichBuilder.build() : null;
        SidesBuilder sides = sidesBuilder.build();
        Checkout checkout = new Checkout();
        checkout.generateReceipt(sandwich, sides);
    }

    public void cancelOrder() {
        sandwichBuilder = new SandwichBuilder.Builder();
        sidesBuilder = new SidesBuilder.Builder();
        hasSandwich = false;
        hasDrink = false;
        hasChips = false;
        System.out.println("\nOrder Canceled");
    }

    // Helper methods for the Methods above.

    private int selectSize() {
        int choice = -1;
        while (choice < 1 || choice > 3) {
            System.out.println("\n\nSelect Size:");
            System.out.println("1) 4 inch");
            System.out.println("2) 8 inch");
            System.out.println("3) 12 inch");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return choice * 4;
    }

    private Inventory.bread selectBread() {
        int choice = -1;
        while (choice < 1 || choice > Inventory.bread.values().length) {
            System.out.println("\n\nSelect Bread:");
            for (Inventory.bread bread : Inventory.bread.values()) {
                System.out.println(bread.ordinal() + 1 + ") " + bread);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.bread.values()[choice - 1];
    }

    private boolean isToasted() {
        System.out.println("\n\nDo you want your sandwich toasted? (yes/no)");
        while (true) {
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                return true;
            } else if (choice.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("\nInvalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private void addMeats() {
        while (true) {
            System.out.println("\n\nSelect Meats:");
            Inventory.meats meat = selectMeat();
            if (meat != null) {
                sandwichBuilder.addMeat(meat);
                System.out.println("\nDo you want to add more meats? (yes/no)");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }

    private Inventory.meats selectMeat() {
        int choice = -1;
        while (choice < 0 || choice > Inventory.meats.values().length) {
            for (Inventory.meats meat : Inventory.meats.values()) {
                System.out.println(meat.ordinal() + 1 + ") " + meat);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0) return null;
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.meats.values()[choice - 1];
    }

    private void addCheese() {
        while (true) {
            System.out.println("\n\nSelect Cheese:");
            Inventory.cheese cheese = selectCheese();
            if (cheese != null) {
                sandwichBuilder.addCheese(cheese);
                System.out.println("\nDo you want to add more cheese? (yes/no)");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }

    private Inventory.cheese selectCheese() {
        int choice = -1;
        while (choice < 0 || choice > Inventory.cheese.values().length) {
            for (Inventory.cheese cheese : Inventory.cheese.values()) {
                System.out.println(cheese.ordinal() + 1 + ") " + cheese);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0) return null;
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.cheese.values()[choice - 1];
    }

    private void addToppings() {
        while (true) {
            System.out.println("\n\nSelect Toppings:");
            Inventory.toppings topping = selectTopping();
            if (topping != null) {
                sandwichBuilder.addTopping(topping);
                System.out.println("\nDo you want to add more toppings? (yes/no)");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }

    private Inventory.toppings selectTopping() {
        int choice = -1;
        while (choice < 0 || choice > Inventory.toppings.values().length) {
            for (Inventory.toppings topping : Inventory.toppings.values()) {
                System.out.println(topping.ordinal() + 1 + ") " + topping);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0) return null;
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.toppings.values()[choice - 1];
    }

    private void addSauces() {
        while (true) {
            System.out.println("\n\nSelect Sauces:");
            Inventory.sauces sauce = selectSauce();
            if (sauce != null) {
                sandwichBuilder.addSauce(sauce);
                System.out.println("\nDo you want to add more sauces? (yes/no)");
                String choice = scanner.next();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }

    private Inventory.sauces selectSauce() {
        int choice = -1;
        while (choice < 0 || choice > Inventory.sauces.values().length) {
            for (Inventory.sauces sauce : Inventory.sauces.values()) {
                System.out.println(sauce.ordinal() + 1 + ") " + sauce);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0) return null;
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.sauces.values()[choice - 1];
    }

    private Inventory.drinks selectDrink() {
        int choice = -1;
        while (choice < 1 || choice > Inventory.drinks.values().length) {
            System.out.println("Select Drink:");
            for (Inventory.drinks drink : Inventory.drinks.values()) {
                System.out.println(drink.ordinal() + 1 + ") " + drink);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.drinks.values()[choice - 1];
    }

    private Inventory.drinkSize selectDrinkSize() {
        int choice = -1;
        while (choice < 1 || choice > Inventory.drinkSize.values().length) {
            System.out.println("Select Drink Size:");
            for (Inventory.drinkSize size : Inventory.drinkSize.values()) {
                System.out.println(size.ordinal() + 1 + ") " + size);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.drinkSize.values()[choice - 1];
    }

    private Inventory.chips selectChips() {
        int choice = -1;
        while (choice < 1 || choice > Inventory.chips.values().length) {
            System.out.println("Select Chips:");
            for (Inventory.chips chips : Inventory.chips.values()) {
                System.out.println(chips.ordinal() + 1 + ") " + chips);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }
        return Inventory.chips.values()[choice - 1];
    }

    private void displayOrder() {
        double totalCost = 0;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Current Order:");

        if (hasSandwich) {
            System.out.println("Sandwich:");
            System.out.println("  Bread: " + sandwichBuilder.getBread() + " ($" + df.format(Cost.getBreadCost(sandwichBuilder.getSize())) + ")");
            System.out.println("  Size: " + sandwichBuilder.getSize() + " inch");
            System.out.println("  Toasted: " + (sandwichBuilder.isToasted() ? "Yes" : "No"));
            System.out.println("  Meats:");
            for (Map.Entry<Inventory.meats, Integer> entry : sandwichBuilder.getMeatCounts().entrySet()) {
                System.out.println("    - " + entry.getKey() + " x" + entry.getValue() + " ($" + df.format(Cost.getMeatCost(sandwichBuilder.getSize())) + ")");
            }
            System.out.println("  Cheeses:");
            for (Map.Entry<Inventory.cheese, Integer> entry : sandwichBuilder.getCheeseCounts().entrySet()) {
                System.out.println("    - " + entry.getKey() + " x" + entry.getValue() + " ($" + df.format(Cost.getCheeseCost(sandwichBuilder.getSize())) + ")");
            }
            System.out.println("  Toppings:");
            for (Inventory.toppings topping : sandwichBuilder.getToppings()) {
                System.out.println("    - " + topping);
            }
            System.out.println("  Sauces:");
            for (Inventory.sauces sauce : sandwichBuilder.getSauces()) {
                System.out.println("    - " + sauce);
            }
            totalCost += Cost.getBreadCost(sandwichBuilder.getSize());
            totalCost += Cost.calculateMeatCost(sandwichBuilder.getSize(), sandwichBuilder.getMeatCounts());
            totalCost += Cost.calculateCheeseCost(sandwichBuilder.getSize(), sandwichBuilder.getCheeseCounts());
        }

        if (hasDrink) {
            System.out.println("Drinks:");
            for (Map.Entry<Inventory.drinks, Inventory.drinkSize> entry : sidesBuilder.getDrinks().entrySet()) {
                System.out.println("  - " + entry.getKey() + " (" + entry.getValue() + "): $" + df.format(Cost.getDrinkCost(entry.getKey(), entry.getValue())));
                totalCost += Cost.getDrinkCost(entry.getKey(), entry.getValue());
            }
        }

        if (hasChips) {
            System.out.println("Chips:");
            for (Map.Entry<Inventory.chips, Integer> entry : sidesBuilder.getChips().entrySet()) {
                System.out.println("  - " + entry.getKey() + " x" + entry.getValue() + " ($" + df.format(Cost.getChipsCost(entry.getKey())) + ")");
                totalCost += Cost.getChipsCost(entry.getKey()) * entry.getValue();
            }
        }

        System.out.println("Total Cost: $" + df.format(totalCost));
    }

    private void removeItem() {
        int choice = -1;
        while (choice < 1 || choice > 3) {
            System.out.println("\n\nWhat would you like to remove?");
            System.out.println("1) Sandwich");
            System.out.println("2) Drink");
            System.out.println("3) Chips");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
                choice = -1;
            }
        }

        switch (choice) {
            case 1:
                if (hasSandwich) {
                    sandwichBuilder = new SandwichBuilder.Builder();
                    hasSandwich = false;
                    System.out.println("\nSandwich removed from your order.");
                } else {
                    System.out.println("\nYou don't have a sandwich in your order.");
                }
                break;
            case 2:
                if (hasDrink) {
                    sidesBuilder.getDrinks().clear();
                    hasDrink = false;
                    System.out.println("\nDrink removed from your order.");
                } else {
                    System.out.println("\nYou don't have a drink in your order.");
                }
                break;
            case 3:
                if (hasChips) {
                    sidesBuilder.getChips().clear();
                    hasChips = false;
                    System.out.println("\nChips removed from your order.");
                } else {
                    System.out.println("\nYou don't have chips in your order.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}