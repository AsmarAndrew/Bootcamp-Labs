package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Sandwich Builder Class

 */

public class SandwichBuilder {
    private Inventory.bread bread;
    private int size;
    private boolean isToasted;
    private Map<Inventory.meats, Integer> meatCounts = new HashMap<>();
    private Map<Inventory.cheese, Integer> cheeseCounts = new HashMap<>();
    private List<Inventory.toppings> toppings = new ArrayList<>();
    private List<Inventory.sauces> sauces = new ArrayList<>();

    public SandwichBuilder() {
    }

    private SandwichBuilder(Builder builder) {
        this.bread = builder.bread;
        this.size = builder.size;
        this.isToasted = builder.isToasted;
        this.meatCounts = builder.meatCounts;
        this.cheeseCounts = builder.cheeseCounts;
        this.toppings = builder.toppings;
        this.sauces = builder.sauces;
    }

    public Inventory.bread getBread() {
        return bread;
    }

    public int getSize() {
        return size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public Map<Inventory.meats, Integer> getMeatCounts() {
        return meatCounts;
    }

    public Map<Inventory.cheese, Integer> getCheeseCounts() {
        return cheeseCounts;
    }

    public List<Inventory.toppings> getToppings() {
        return toppings;
    }

    public List<Inventory.sauces> getSauces() {
        return sauces;
    }

    public static class Builder {
        private Inventory.bread bread;
        private int size;
        private boolean isToasted;
        private Map<Inventory.meats, Integer> meatCounts = new HashMap<>();
        private Map<Inventory.cheese, Integer> cheeseCounts = new HashMap<>();
        private List<Inventory.toppings> toppings = new ArrayList<>();
        private List<Inventory.sauces> sauces = new ArrayList<>();

        public Builder setBread(Inventory.bread bread) {
            this.bread = bread;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setToasted(boolean isToasted) {
            this.isToasted = isToasted;
            return this;
        }

        public Builder addMeat(Inventory.meats meat) {
            meatCounts.put(meat, meatCounts.getOrDefault(meat, 0) + 1);
            return this;
        }

        public Builder addCheese(Inventory.cheese cheese) {
            cheeseCounts.put(cheese, cheeseCounts.getOrDefault(cheese, 0) + 1);
            return this;
        }

        public Builder addTopping(Inventory.toppings topping) {
            toppings.add(topping);
            return this;
        }

        public Builder addSauce(Inventory.sauces sauce) {
            sauces.add(sauce);
            return this;
        }

        public Inventory.bread getBread() {
            return bread;
        }

        public int getSize() {
            return size;
        }

        public boolean isToasted() {
            return isToasted;
        }

        public Map<Inventory.meats, Integer> getMeatCounts() {
            return meatCounts;
        }

        public Map<Inventory.cheese, Integer> getCheeseCounts() {
            return cheeseCounts;
        }

        public List<Inventory.toppings> getToppings() {
            return toppings;
        }

        public List<Inventory.sauces> getSauces() {
            return sauces;
        }

        public SandwichBuilder build() {
            return new SandwichBuilder(this);
        }
    }
}