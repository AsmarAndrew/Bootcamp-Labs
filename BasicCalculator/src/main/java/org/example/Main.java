package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        float firstNumber = scanner.nextFloat();


        System.out.print("Enter the second number: ");
        float secondNumber = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("\nPossible calculations: ");
        System.out.println("\t(A)dd\n\t(S)ubtract\n\t(M)ultiply\n\t(D)ivide");
        System.out.print("Please select an option: ");
        String operatorChosen = scanner.nextLine();
        System.out.println("");

        if (operatorChosen.equalsIgnoreCase("A") || operatorChosen.equalsIgnoreCase("Add")){
            System.out.println(firstNumber + " + " + secondNumber + " = " + addTwoNumbers(firstNumber, secondNumber));
        } else if (operatorChosen.equalsIgnoreCase("S") || operatorChosen.equalsIgnoreCase("Subtract")){
            System.out.println(firstNumber + " - " + secondNumber + " = " + subTwoNumbers(firstNumber, secondNumber));
        } else if (operatorChosen.equalsIgnoreCase("M") || operatorChosen.equalsIgnoreCase("Multiply")) {
            System.out.println(firstNumber + " * " + secondNumber + " = " + multTwoNumbers(firstNumber, secondNumber));
        } else if (operatorChosen.equalsIgnoreCase("D") || operatorChosen.equalsIgnoreCase("Divide")) {
            System.out.println(firstNumber + " / " + secondNumber + " = " + divTwoNumbers(firstNumber, secondNumber));
        }else{
            System.out.println("Please Try Again!");
        }
        //add

    }

    public static float addTwoNumbers(float firstNumber, float secondNumber){
        float sum = firstNumber + secondNumber;
        return sum;
    }

    public static float subTwoNumbers(float firstNumber, float secondNumber){
        float sum = firstNumber - secondNumber;
        return sum;
    }

    public static float multTwoNumbers(float firstNumber, float secondNumber){
        float sum = firstNumber * secondNumber;
        return sum;
    }

    public static float divTwoNumbers(float firstNumber, float secondNumber){
        float sum = firstNumber / secondNumber;
        return sum;
    }
}