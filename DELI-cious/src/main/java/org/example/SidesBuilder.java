package org.example;

import java.util.HashMap;
import java.util.Map;

/*

Side Builder Class

 */

public class SidesBuilder {
    private Map<Inventory.drinks, Inventory.drinkSize> drinks;
    private Map<Inventory.chips, Integer> chips;

    private SidesBuilder(Builder builder) {
        this.drinks = builder.drinks;
        this.chips = builder.chips;
    }

    public Map<Inventory.drinks, Inventory.drinkSize> getDrinks() {
        return drinks;
    }

    public Map<Inventory.chips, Integer> getChips() {
        return chips;
    }

    public double calculateDrinkCost() {
        double totalCost = 0;
        for (Map.Entry<Inventory.drinks, Inventory.drinkSize> entry : drinks.entrySet()) {
            totalCost += Cost.getDrinkCost(entry.getKey(), entry.getValue());
        }
        return totalCost;
    }

    public double calculateChipsCost() {
        double totalCost = 0;
        for (Map.Entry<Inventory.chips, Integer> entry : chips.entrySet()) {
            totalCost += Cost.getChipsCost(entry.getKey()) * entry.getValue();
        }
        return totalCost;
    }

    public static class Builder {
        private Map<Inventory.drinks, Inventory.drinkSize> drinks = new HashMap<>();
        private Map<Inventory.chips, Integer> chips = new HashMap<>();

        public Builder addDrink(Inventory.drinks drink, Inventory.drinkSize size) {
            drinks.put(drink, size);
            return this;
        }

        public Builder addChips(Inventory.chips chip) {
            chips.put(chip, chips.getOrDefault(chip, 0) + 1);
            return this;
        }

        public Map<Inventory.drinks, Inventory.drinkSize> getDrinks() {
            return drinks;
        }

        public Map<Inventory.chips, Integer> getChips() {
            return chips;
        }

        public SidesBuilder build() {
            return new SidesBuilder(this);
        }
    }
}