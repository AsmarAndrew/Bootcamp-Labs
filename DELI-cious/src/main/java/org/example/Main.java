package org.example;

public class Main {
    public static void main(String[] args) {
        DeliShopOrder deliShopOrder = new DeliShopOrder();
        UserInterface userInterface = new UserInterface(deliShopOrder);
        userInterface.homeScreen();

    }
}