package org.example;

import java.util.Scanner;

public class UserInterface {
    private DeliShopOrder deliShopOrder;
    private Scanner scanner;

    public UserInterface(DeliShopOrder deliShopOrder){
        this.deliShopOrder = deliShopOrder;
        this.scanner = new Scanner(System.in);
    }

    public void homeScreen(){
        System.out.println("This is Home Screen");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                orderScreen();
                break;
            case 0:
                break;
        }


    }

    public void orderScreen(){
        int input = 9;
        while (input != 0) {
        System.out.println("This is Order Screen");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drinks");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    deliShopOrder.addSandwich();
                    break;
                case 2:
                    deliShopOrder.addDrink();
                    break;
                case 3:
                    deliShopOrder.addChips();
                    break;
                case 4:
                    deliShopOrder.checkout();
                    break;
                case 0:
                    deliShopOrder.cancelOrder();
                    homeScreen();
                    break;
            }
        }
    }
}
