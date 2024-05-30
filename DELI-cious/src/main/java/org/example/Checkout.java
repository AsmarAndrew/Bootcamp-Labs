package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Checkout {

    /*

    This class is to handle the checkout method with the receipt for the file.

     */

    public void generateReceipt(SandwichBuilder sandwich, SidesBuilder sides) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter receiptFmt = DateTimeFormatter.ofPattern("yyyy-MMdd-HHmmss");
        String receiptDate = "src/main/resources/" + today.format(receiptFmt) + ".txt";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm");
        String todayDate = today.format(fmt);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptDate))) {
            writeHeader(writer, todayDate);

            if (sandwich != null) {
                writeSandwichDetails(writer, sandwich);
            }

            if (!sides.getDrinks().isEmpty()) {
                writeDrinkDetails(writer, sides);
            }

            if (!sides.getChips().isEmpty()) {
                writeChipsDetails(writer, sides);
            }

            writeTotalCosts(writer, sandwich, sides, decimalFormat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*

    Everything below is handling all the details of what to print and how to print it.

     */

    private void writeHeader(BufferedWriter writer, String todayDate) throws IOException {
        writer.write("==================================================\n");
        writer.write("Today's Date: " + todayDate + "\n");
        writer.write("==================================================\n");
    }

    private void writeSandwichDetails(BufferedWriter writer, SandwichBuilder sandwich) throws IOException {
        writer.write("Sandwich Order Details:\n");
        writer.write("Bread: " + sandwich.getBread() + " ($" + Cost.getBreadCost(sandwich.getSize()) + ")\n");
        writer.write("Size: " + sandwich.getSize() + " inch\n");
        writer.write("Toasted: " + (sandwich.isToasted() ? "Yes" : "No") + "\n");

        writer.write("Meats:\n");
        writeMeatList(writer, sandwich.getMeatCounts(), sandwich.getSize());

        writer.write("Cheeses:\n");
        writeCheeseList(writer, sandwich.getCheeseCounts(), sandwich.getSize());

        writer.write("Toppings:\n");
        writeToppingsList(writer, sandwich.getToppings());

        writer.write("Sauces:\n");
        writeSaucesList(writer, sandwich.getSauces());
    }

    private void writeDrinkDetails(BufferedWriter writer, SidesBuilder sides) throws IOException {
        writer.write("Drinks:\n");
        for (Map.Entry<Inventory.drinks, Inventory.drinkSize> entry : sides.getDrinks().entrySet()) {
            writer.write("- " + entry.getKey() + " (" + entry.getValue() + "): $" + Cost.getDrinkCost(entry.getKey(), entry.getValue()) + "\n");
        }
    }

    private void writeChipsDetails(BufferedWriter writer, SidesBuilder sides) throws IOException {
        writer.write("Chips:\n");
        for (Map.Entry<Inventory.chips, Integer> entry : sides.getChips().entrySet()) {
            writer.write("- " + entry.getKey() + " x" + entry.getValue() + " ($" + Cost.getChipsCost(entry.getKey()) * entry.getValue() + ")\n");
        }
    }

    private void writeTotalCosts(BufferedWriter writer, SandwichBuilder sandwich, SidesBuilder sides, DecimalFormat decimalFormat) throws IOException {
        double totalCost = 0;
        if (sandwich != null) {
            totalCost += Cost.getBreadCost(sandwich.getSize());
            totalCost += Cost.calculateMeatCost(sandwich.getSize(), sandwich.getMeatCounts());
            totalCost += Cost.calculateCheeseCost(sandwich.getSize(), sandwich.getCheeseCounts());
            writer.write("Total Sandwich Cost: $" + decimalFormat.format(totalCost) + "\n");
        }
        if (!sides.getDrinks().isEmpty()) {
            totalCost += sides.calculateDrinkCost();
            writer.write("Total Drink Cost: $" + decimalFormat.format(sides.calculateDrinkCost()) + "\n");
        }
        if (!sides.getChips().isEmpty()) {
            totalCost += sides.calculateChipsCost();
            writer.write("Total Chips Cost: $" + decimalFormat.format(sides.calculateChipsCost()) + "\n");
        }
        writer.write("Total Cost: $" + decimalFormat.format(totalCost) + "\n");
        writer.write("==================================================\n");
    }

    private void writeMeatList(BufferedWriter writer, Map<Inventory.meats, Integer> counts, int size) throws IOException {
        for (Map.Entry<Inventory.meats, Integer> entry : counts.entrySet()) {
            Inventory.meats meat = entry.getKey();
            int count = entry.getValue();
            writer.write("- " + meat + ": $" + Cost.getMeatCost(size) + "\n");
            for (int i = 1; i < count; i++) {
                writer.write("  Extra: $" + Cost.getExtraMeatCost(size) + "\n");
            }
        }
    }

    private void writeCheeseList(BufferedWriter writer, Map<Inventory.cheese, Integer> counts, int size) throws IOException {
        for (Map.Entry<Inventory.cheese, Integer> entry : counts.entrySet()) {
            Inventory.cheese cheese = entry.getKey();
            int count = entry.getValue();
            writer.write("- " + cheese + ": $" + Cost.getCheeseCost(size) + "\n");
            for (int i = 1; i < count; i++) {
                writer.write("  Extra: $" + Cost.getExtraCheeseCost(size) + "\n");
            }
        }
    }

    private void writeToppingsList(BufferedWriter writer, List<Inventory.toppings> toppings) throws IOException {
        for (Inventory.toppings topping : toppings) {
            writer.write("- " + topping + "\n");
        }
    }

    private void writeSaucesList(BufferedWriter writer, List<Inventory.sauces> sauces) throws IOException {
        for (Inventory.sauces sauce : sauces) {
            writer.write("- " + sauce + "\n");
        }
    }
}