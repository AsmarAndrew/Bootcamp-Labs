package org.example;


public class Transactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double price;

    public Transactions(String date, String time, String description, String vendor, double price) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long fixedDate(){

            String[] timeInput = getTime().split(":");
            String hour = timeInput[0];
            String min = timeInput[1];
            String second = timeInput[2];

            String[] dateInput = getDate().split("-");
            String year = dateInput[0];
            String month = dateInput[1];
            String day = dateInput[2];

            String dateTime = year+month+day+hour+min+second;

            return Long.parseLong(dateTime);
    }



}
