package org.example;

import java.util.Scanner;

public class Screens {

    //Instance to access the book Inventory, Fun Starts here :)
    private BookInventory bookInventory = new BookInventory();

    //Method for home screen pretty self-explanatory
    public void storeHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        Book[] books = bookInventory.inventory();

        while (true) {
            System.out.println("\n\uD83D\uDCDA Welcome to Enchanted Pages Book Emporium! \uD83D\uDCDA");
            System.out.println("\nUnveil the Mysteries of Knowledge:");
            System.out.println("\n1. \uD83C\uDF1F Explore Our Tome Haven - Show Available Books \uD83C\uDF1F");
            System.out.println("2. \uD83E\uDDD9\u200D♂️ Journey into the Unknown - Show Checked Out Books \uD83E\uDDD9\u200D♀️");
            System.out.println("3. \uD83D\uDEAA Exit and Return to the Mundane Realm \uD83D\uDEAA");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    showAvailable(books);
                    break;
                case 2:
                    showCheckedOut(books);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nPlease enter a valid option!\n");
            }
        }
    }

    //Method displaying list of available books.
    public void showAvailable(Book[] books) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\uD83D\uDCDA Available Books \uD83D\uDCDA");
        System.out.println("\nDiscover Your Next Adventure:");

        boolean availableBooksExist = false;

        //Running the loop with the array
        for (Book book : books) {
            //This is asking if the book is not checked out it would display
            if (book.isCheckOut() == false) {
                System.out.println(book.getId() + " - " + book.getIsbn() + " - " + book.getTitle());
                //This goes back to the boolean on top saying if it ran this if statement once that means there is books available.
                availableBooksExist = true;
            }
        }
        //Only happens if there's no books avaiable.
        if (availableBooksExist == false) {
            System.out.println("No available books at the moment.");
            return;
        }


        while (true) {

            System.out.println("\nSelect a book to check out (Select by ID), or press 0 to return to the main menu:");
            int userInput = scanner.nextInt();
            //If user input 0 then will go back to main menu
            if (userInput == 0) {
                return;
            //If user inputs a valid number it'll run this
            } else if (userInput > 0 && userInput <= books.length) {
                //-1 so it chooses the right array
                int selectedIndex = userInput - 1;
                checkingOut(books[selectedIndex]);
                return;
            } else {
                System.out.println("Please Try again");
            }
        }
    }

    public void checkingOut(Book book) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n🛒 Checkout Process 🛒");
        System.out.println("\nLet's Secure Your Literary Treasure:");
        System.out.println("\nPlease enter your name for checkout, or press 0 to cancel:");
        String checkingName = scanner.nextLine();

        if (checkingName.equalsIgnoreCase("0")) {
            return;
        }
        if (!book.isCheckOut()) {
            book.setCheckOut(true);
            book.setCheckOutTo(checkingName);
            System.out.println("\uD83C\uDF89 Congratulations! You've Successfully Checked Out! \uD83C\uDF89");
            System.out.println("\uD83D\uDCDA Book: " + book.getTitle());
            System.out.println("\uD83D\uDC64 Checked Out By: " + checkingName);
        } else {
            System.out.println("This book is already checked out.");
        }
    }

    public void showCheckedOut(Book[] books) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n📚 Check-in Process 📚");
        System.out.println("\nWelcome back! Let's check in your borrowed book.");

        boolean checkedOutBooksExist = false;
        System.out.println("\n\uD83D\uDCDA Checked Out Books \uD83D\uDCDA");
        for (Book book : books) {
            if (book.isCheckOut()) {
                System.out.println(book.getId() + " - " + book.getIsbn() + " - " + book.getTitle() + " (Checked out by: " + book.getCheckOutTo() + ")");
                checkedOutBooksExist = true;
            }
        }

        if (!checkedOutBooksExist) {
            System.out.println("No books are currently checked out.");
            return;
        }

        System.out.println("\nEnter the ID of the book you want to check in, or press 0 to cancel:");
        int userInput = scanner.nextInt();
        if (userInput == 0) {
            return;
        } else if (userInput > 0 && userInput <= books.length) {
            int selectedIndex = userInput - 1;
            Book selectedBook = books[selectedIndex];
            if (selectedBook.isCheckOut()) {
                selectedBook.setCheckOut(false);
                selectedBook.setCheckOutTo(null);
                System.out.println("The book '" + selectedBook.getTitle() + "' has been successfully checked in.");
            } else {
                System.out.println("This book is not currently checked out.");
            }
        } else {
            System.out.println("Invalid book ID. Please try again.");
        }
    }
}