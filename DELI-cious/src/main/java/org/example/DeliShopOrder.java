package org.example;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class DeliShopOrder {

    //initializing
    private Scanner scanner = new Scanner(System.in);
    private SandwichBuilder.Builder sandwichBuilder = new SandwichBuilder.Builder();
    private SidesBuilder.Builder sidesBuilder = new SidesBuilder.Builder();
    private boolean hasSandwich = false;
    private boolean hasDrink = false;
    private boolean hasChips = false;

    /*

    First 5 Methods are for Handling The UserInterface

     */

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
            System.out.println("You must add at least a sandwich, drink, or chips to your order.");
            return;
        }

        while (true) {
            displayOrder();
            System.out.println("Do you want to remove anything? (yes/no)");
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
        System.out.println("Order Canceled");
    }

    /*

    Here is all the helper methods for the Methods above.

     */

    private int selectSize() {
        System.out.println("Select Size:");
        System.out.println("1) 4 inch");
        System.out.println("2) 8 inch");
        System.out.println("3) 12 inch");
        int choice = scanner.nextInt();
        return choice * 4;
    }

    private Inventory.bread selectBread() {
        System.out.println("Select Bread:");
        for (Inventory.bread bread : Inventory.bread.values()) {
            System.out.println(bread.ordinal() + 1 + ") " + bread);
        }
        int choice = scanner.nextInt();
        return Inventory.bread.values()[choice - 1];
    }

    private boolean isToasted() {
        System.out.println("Do you want your sandwich toasted? (yes/no)");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("yes");
    }

    private void addMeats() {
        System.out.println("Select Meats (enter 0 to finish):");
        while (true) {
            Inventory.meats meat = selectMeat();
            if (meat == null) break;
            sandwichBuilder.addMeat(meat);
        }
    }

    private Inventory.meats selectMeat() {
        for (Inventory.meats meat : Inventory.meats.values()) {
            System.out.println(meat.ordinal() + 1 + ") " + meat);
        }
        int choice = scanner.nextInt();
        if (choice == 0) return null;
        return Inventory.meats.values()[choice - 1];
    }

    private void addCheese() {
        System.out.println("Select Cheese (enter 0 to finish):");
        while (true) {
            Inventory.cheese cheese = selectCheese();
            if (cheese == null) break;
            sandwichBuilder.addCheese(cheese);
        }
    }

    private Inventory.cheese selectCheese() {
        for (Inventory.cheese cheese : Inventory.cheese.values()) {
            System.out.println(cheese.ordinal() + 1 + ") " + cheese);
        }
        int choice = scanner.nextInt();
        if (choice == 0) return null;
        return Inventory.cheese.values()[choice - 1];
    }

    private void addToppings() {
        System.out.println("Select Toppings (enter 0 to finish):");
        while (true) {
            Inventory.toppings topping = selectTopping();
            if (topping == null) break;
            sandwichBuilder.addTopping(topping);
        }
    }

    private Inventory.toppings selectTopping() {
        for (Inventory.toppings topping : Inventory.toppings.values()) {
            System.out.println(topping.ordinal() + 1 + ") " + topping);
        }
        int choice = scanner.nextInt();
        if (choice == 0) return null;
        return Inventory.toppings.values()[choice - 1];
    }

    private void addSauces() {
        System.out.println("Select Sauces (enter 0 to finish):");
        while (true) {
            Inventory.sauces sauce = selectSauce();
            if (sauce == null) break;
            sandwichBuilder.addSauce(sauce);
        }
    }

    private Inventory.sauces selectSauce() {
        for (Inventory.sauces sauce : Inventory.sauces.values()) {
            System.out.println(sauce.ordinal() + 1 + ") " + sauce);
        }
        int choice = scanner.nextInt();
        if (choice == 0) return null;
        return Inventory.sauces.values()[choice - 1];
    }

    private Inventory.drinks selectDrink() {
        System.out.println("Select Drink:");
        for (Inventory.drinks drink : Inventory.drinks.values()) {
            System.out.println(drink.ordinal() + 1 + ") " + drink);
        }
        int choice = scanner.nextInt();
        return Inventory.drinks.values()[choice - 1];
    }

    private Inventory.drinkSize selectDrinkSize() {
        System.out.println("Select Drink Size:");
        for (Inventory.drinkSize size : Inventory.drinkSize.values()) {
            System.out.println(size.ordinal() + 1 + ") " + size);
        }
        int choice = scanner.nextInt();
        return Inventory.drinkSize.values()[choice - 1];
    }

    private Inventory.chips selectChips() {
        System.out.println("Select Chips:");
        for (Inventory.chips chips : Inventory.chips.values()) {
            System.out.println(chips.ordinal() + 1 + ") " + chips);
        }
        int choice = scanner.nextInt();
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
        System.out.println("What would you like to remove?");
        System.out.println("1) Sandwich");
        System.out.println("2) Drink");
        System.out.println("3) Chips");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (hasSandwich) {
                    sandwichBuilder = new SandwichBuilder.Builder();
                    hasSandwich = false;
                    System.out.println("Sandwich removed from your order.");
                } else {
                    System.out.println("You don't have a sandwich in your order.");
                }
                break;
            case 2:
                if (hasDrink) {
                    sidesBuilder.getDrinks().clear();
                    hasDrink = false;
                    System.out.println("Drink removed from your order.");
                } else {
                    System.out.println("You don't have a drink in your order.");
                }
                break;
            case 3:
                if (hasChips) {
                    sidesBuilder.getChips().clear();
                    hasChips = false;
                    System.out.println("Chips removed from your order.");
                } else {
                    System.out.println("You don't have chips in your order.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}