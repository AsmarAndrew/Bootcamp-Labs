package org.example;

//Class just for custom search.

public class TransactionCustom {
    private long dateMin;
    private long dateMax;
    private String description;
    private String vendor;
    private double minPrice;
    private double maxPrice;

    public TransactionCustom() {
        this.dateMin = Long.MIN_VALUE;
        this.dateMax = Long.MAX_VALUE;
        this.description = "";
        this.vendor = "";
        this.minPrice = Double.MIN_VALUE;
        this.maxPrice = Double.MAX_VALUE;
    }

    public long getDateMin() {
        return dateMin;
    }

    public void setDateMin(long dateMin) {
        this.dateMin = dateMin;
    }

    public long getDateMax() {
        return dateMax;
    }

    public void setDateMax(long dateMax) {
        this.dateMax = dateMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
