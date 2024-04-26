package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Screens {

    //Store Home Screen for user
    public void storeHomeScreen(){

        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        //Store Home Screen

        while (true){
            System.out.println("Welcome to *THE Store*!");
            System.out.println("                ;'-.   \n" +
                    "   `;-._        )  '---.._     \n" +
                    "     >  `-.__.-'          `'.__   \n" +
                    "    /_.-'-._         _,   ^ ---)   \n" +
                    "    `       `'------/_.'----```   \n");

            System.out.println("Hey there, Shopaholic!");
            System.out.println("Are you ready for a shopping spree at *THE Store*?");
            System.out.println("We've got everything you need to turn your frown into a crown! 👑💫");

            System.out.println("1. Display Product");
            System.out.println("2. Display Cart");
            System.out.println("3. Exit");

            boolean launched = true;
            while (launched) {
                System.out.print("Enter your choice here: ");
                String userInput = scanner.nextLine();
                int choice = Integer.parseInt(userInput);
                switch (choice) {
                    case 1:
                        launched = false;
                        displayProducts(products, scanner);
                        break;
                    case 2:
                        launched = false;
                        displayCart(products, scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("\nOopsie! Looks like that wasn't quite right. Let's try again!\n");
                }
            }
        }
    }

    //Displaying Product Screen

    public void displayProducts(List<Product> products, Scanner scanner){

        String filePath = "src/main/resources/";
        String productFile = filePath + "products.csv";
        String shoppingCart = filePath + "shopping_cart.csv";

        System.out.println("\nExplore our fantastic range of products!");

        try(BufferedReader reader = new BufferedReader(new FileReader(productFile))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split("\\|");
                String sku = data[0];
                String productName = data[1];
                double price = Double.parseDouble(data[2]);
                String department = data[3];

                Product product = new Product(sku,productName,price,department);
                products.add(product);
            }
        }
        catch (IOException ex){
            System.out.println("Oops! It seems we couldn't find the file you're looking for.");
        }

        for (Product product : products){
            System.out.println("SKU: " + product.getSku() + " Name: "
                    + product.getProductName() + " Price: " + product.getPrice() + " Department: " + product.getDepartment());
        }

        //Asking user if they would like to search/filter or order their products in a certain way.

        System.out.println("\nWould you like to search for a specific product or apply filters?");
        System.out.println("1. Filter");
        System.out.println("2. Order");
        System.out.println("3. Continue without searching or filtering");

        while (true) {
            System.out.print("Enter your choice here: ");
            String userSearchFilter = scanner.nextLine();
            if (userSearchFilter.equalsIgnoreCase("1") || userSearchFilter.equalsIgnoreCase("Filter")) {
                searchFilter(products, userSearchFilter, scanner);
                break;
            } else if (userSearchFilter.equalsIgnoreCase("2") || userSearchFilter.equalsIgnoreCase("Order")) {
                searchFilter(products, userSearchFilter, scanner);
                break;
            } else if (userSearchFilter.equalsIgnoreCase("3") || userSearchFilter.equalsIgnoreCase("Continue")) {
                System.out.println("Continue");
                break;
            } else {
                System.out.println("\nOopsie! Looks like that wasn't quite right. Let's try again!\n");
            }
        }

        //Going to add products to the users cart.

        System.out.println("\nGreat! Let's add some items to your cart.");
        System.out.println();

        //Using a loop so that the user can add multiple products until they type 'X'

        while (true) {
            System.out.println("Enter 'X' once you've added all the products you want to your cart.\n");
            System.out.print("Enter the name of the product you want to add to your cart (or 'X' to exit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("x")) {
                break;
            } else {
                for (Product product : products) {
                    if (userInput.equalsIgnoreCase(product.getProductName())) {
                        try {
                            FileWriter writer = new FileWriter(shoppingCart, true);
                            writer.write(product.getSku() + "|" + product.getProductName() + "|" + product.getPrice() + "|" + product.getDepartment() + "\n");
                            writer.close();
                        } catch (IOException ex) {
                            System.out.println("Error adding the product to the cart.");
                        }
                    }
                }
            }
        }
    }

    //Branch of Display Product Screen just for search / filter or ordering the products.

    public void searchFilter(List<Product> products,String userInput, Scanner scanner){

        //This will be used if the user is asking to search / filter the products and order the products

        if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("Filter")){

            System.out.println("\nHow would you like to search for products?");
            System.out.println("1. Search by Price");
            System.out.println("2. Search by Department");
            System.out.println("3. Continue without searching");

            while (true) {

                System.out.print("(1-3) Enter your choice here: ");
                String filterInput = scanner.nextLine();

                if (filterInput.equalsIgnoreCase("1")) {

                    System.out.println("Please enter minimum price: ");
                    String min = scanner.nextLine();
                    double minPrice = Double.parseDouble(min);
                    System.out.println("Please enter maximum price: ");
                    String max = scanner.nextLine();
                    double maxPrice = Double.parseDouble(max);
                    boolean loopRan = false;

                    for (Product product : products){
                        if (minPrice <= product.getPrice() && maxPrice >= product.getPrice()){
                            System.out.println("SKU Number: " + product.getSku() + " Name: "
                                    + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                            loopRan = true;
                        }
                        else {
                            continue;
                        }
                    }
                    if (loopRan == false){
                        System.out.println("No Item was found within this price range!");
                    }
                    return;

                } else if (filterInput.equalsIgnoreCase("2")) {

                    System.out.println("Please enter Department");
                    String department = scanner.nextLine();
                    boolean loopRan = false;

                    for (Product product : products){
                        if (product.getDepartment().equalsIgnoreCase(department)){
                            System.out.println("SKU Number: " + product.getSku() + " Name: "
                                    + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                            loopRan = true;
                        } else  {
                            continue;
                        }
                    }
                    if (loopRan == false){
                        System.out.println("Your department wasn't found!");
                    }
                    return;

                } else if (filterInput.equalsIgnoreCase("3")) {

                    return;

                } else {

                }
            }
        }
        else if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("Order")) {

            System.out.println("\nHow would you like to filter the products?");
            System.out.println("1. Price: Low to High");
            System.out.println("2. Price: High to Low");
            System.out.println("3. Department: A to Z");
            System.out.println("4. Department: Z to A");
            System.out.println("5. No filtering, show all products");
            while (true) {
                System.out.print("(1 - 5) Enter your choice here: ");
                String orderInput = scanner.nextLine();
                if (orderInput.equalsIgnoreCase("1")) {

                    System.out.println("\nListing out Products Low to High");
                    products.sort(Comparator.comparingDouble(Product::getPrice));
                    for (Product product : products) {
                        System.out.println("SKU Number: " + product.getSku() + " Name: "
                                + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                    }
                    return;

                } else if (orderInput.equalsIgnoreCase("2")) {

                    System.out.println("\nListing out Products High to Low");
                    products.sort(Comparator.comparingDouble(Product::getPrice));
                    Collections.reverse(products);
                    for (Product product : products) {
                        System.out.println("SKU Number: " + product.getSku() + " Name: "
                                + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                    }
                    return;


                } else if (orderInput.equalsIgnoreCase("3")) {

                    System.out.println("\nListing out Products by Department A-Z");
                    products.sort(Comparator.comparing(Product::getDepartment));
                    for (Product product : products) {
                        System.out.println("SKU Number: " + product.getSku() + " Name: "
                                + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                    }
                    return;

                } else if (orderInput.equalsIgnoreCase("4")) {

                    System.out.println("\nListing out Products Z-A");
                    products.sort(Comparator.comparing(Product::getDepartment));
                    Collections.reverse(products);
                    for (Product product : products) {
                        System.out.println("SKU Number: " + product.getSku() + " Name: "
                                + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                    }
                    return;

                } else if (orderInput.equalsIgnoreCase("5")) {

                    System.out.println("\nListing out all Products!");
                    for (Product product : products) {
                        System.out.println("SKU Number: " + product.getSku() + " Name: "
                                + product.getProductName() + " Price " + product.getPrice() + " Department: " + product.getDepartment());
                    }
                    return;

                } else {
                    System.out.println("\nOopsie! Looks like that wasn't quite right. Let's try again!\n");
                }
            }
        }
    }

    //Displaying Cart Screen

    public void displayCart(List<Product> products, Scanner scanner){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String filePath = "src/main/resources/";
        String shoppingCart = filePath + "shopping_cart.csv";
        double sum = 0;

        System.out.println("\nHere's your shopping cart!\n");

        // Clear the products list to ensure it only contains items from shopping_cart.csv
        products.clear();


        try(BufferedReader reader = new BufferedReader(new FileReader(shoppingCart))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] cartDisplay = line.split("\\|");
                String sku = cartDisplay[0];
                String productName = cartDisplay[1];
                double price = Double.parseDouble(cartDisplay[2]);
                String department = cartDisplay[3];

                Product product = new Product(sku,productName,price,department);
                products.add(product);
            }
        }
        catch (IOException ex){
            System.out.println("Error reading shopping cart file.");
        }


        for (Product product : products){
            System.out.println("SKU: " + product.getSku() + " Name: "
                    + product.getProductName() + " Price: " + product.getPrice() + " Department: " + product.getDepartment());
            sum += product.getPrice();
        }


        System.out.println("\nTotal: $" + decimalFormat.format(sum));

        //Having user remove item from cart.

        System.out.println("Would you like to remove any products from your cart?");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes")){

            System.out.println("Please enter Product SKU to remove:");
            String userReturn = scanner.nextLine();

            // Create a new list for removing SKU
            List<Product> updatedProducts = new ArrayList<>();

            // This will run through the products and if it matches the SKU it doesn't add to the updated products.
            for (Product product : products) {
                if (!userReturn.equalsIgnoreCase(product.getSku())) {
                    updatedProducts.add(product);
                }else {
                    sum -= product.getPrice();
                }
            }

            // This will write out the new shopping_cart file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(shoppingCart))) {
                for (Product product : updatedProducts) {
                    writer.write(product.getSku() + "|" + product.getProductName() + "|" + product.getPrice() + "|" + product.getDepartment());
                    writer.newLine();
                }
                System.out.println("Product with SKU " + userReturn + " removed from the shopping cart.");
            } catch (IOException ex) {
                System.out.println("Error writing to the shopping cart file:");
            }
        }

        //This will take user to check out

        checkOut(products, scanner, sum);
    }

    //Displaying Check Out Screen

    public void checkOut(List<Product> products, Scanner scanner, double sum){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter receiptFmt = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String receiptDate = "src/main/receipts/"+today.format(receiptFmt)+".txt";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm");
        String todaysDate = today.format(fmt);
        String filePath = "src/main/resources/";
        String shoppingCart = filePath + "shopping_cart.csv";

        System.out.println("\nTotal: $" + decimalFormat.format(sum));

        System.out.println("Would you like to pay?");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes")){
            System.out.println("Good choice");
        } else if (userInput.equalsIgnoreCase("no")) {
            System.out.println("Well too bad you're paying");
        }else {
            System.out.println("Looks like you didn't type yes or no, but you're paying");
        }

        System.out.println("How much are you going to pay in cash?");
        String userCash = scanner.nextLine();
        double cash = Double.parseDouble(userCash);
        while (true){
            if (cash >= sum){
                System.out.println("Thank you $" + decimalFormat.format(cash - sum) + " is your change");
                break;
            } else if (cash < sum) {
                System.out.println("You still owe $" + decimalFormat.format(sum - cash));
                System.out.println("How much more would you like to pay?");
                String userExtraPay = scanner.nextLine();
                cash += Double.parseDouble(userExtraPay);
            }
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(shoppingCart));
            BufferedWriter writer = new BufferedWriter(new FileWriter(receiptDate))) {

            writer.write("==================================================\n");
            writer.write("Today's Date: " + todaysDate+"\n");

            products.clear();
            String line;
            while ((line = reader.readLine()) != null){
                String[] cartDisplay = line.split("\\|");
                String sku = cartDisplay[0];
                String productName = cartDisplay[1];
                double price = Double.parseDouble(cartDisplay[2]);
                String department = cartDisplay[3];

                Product product = new Product(sku,productName,price,department);
                products.add(product);

                writer.write(product.getSku()+"|"+product.getProductName()+"|"+product.getPrice()+"|"+product.getDepartment());
                writer.newLine();
            }

            writer.write("==================================================\n");
            writer.write("Sales Total $" + sum + "\n");
            writer.write("Amount payed $" + cash + "\n");
            writer.write("Change Given $" + decimalFormat.format(cash - sum) + "\n");
            writer.write("==================================================");

        } catch (IOException ex){
            System.out.println("Error writing to the receipt file.");
        }

    }
}