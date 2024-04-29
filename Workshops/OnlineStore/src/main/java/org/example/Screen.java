package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Screen {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Product> products = new ArrayList<>();
    private List<Product> cart;

    public Screen() {
        cart = new ArrayList<>();
    }

    public void homeScreen() {
        while (true) {
            System.out.println("""
                    Welcome to the store. Please select an option:
                    1. Display products
                    2. Display cart
                    3. Exit
                    """);
            switch (scanner.nextInt()) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    displayCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void displayCart() {
        while (true) {
            for (Product p : cart) {
                System.out.println(p.toStringWithQuantity());
            }
            System.out.println("""
                       Please select an option:
                       1. Check out
                       2. Remove item from cart
                       3. Go back
                    """);
            switch (scanner.nextInt()) {
                case 1:
                    checkOut();
                    return;
                case 2:
                    removeItemFromCart();
                    break;
                case 3:
                    return;
            }
        }
    }

    private void checkOut() {
        System.out.println("Please enter the amount of money you will be paying with.");
        double payment = scanner.nextDouble();
        double totalCost = 0.0;
        for (Product p : cart)
            totalCost += p.getCost();
        if (payment < totalCost) {
            System.out.println("Not enough money.");
            return;
        }
        Receipt r = new Receipt(cart, totalCost, payment);
        System.out.println(r);
        saveReceipt(r);
        cart = new ArrayList<>();
        System.out.println("Thank you for shopping with us.");
    }

    private void displayProducts() {
        for (Product product : products)
            System.out.println(product);
        while (true) {
            System.out.println("""
                    Please select an option:
                    1. Search or filter products
                    2. Add a product to cart
                    3. Go back
                    """);
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    searchProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void searchProducts() {
        System.out.println("""
                Use the following commands to search/filter the products:
                sku <sku>
                name <name>
                price above <amount>
                price below <amount>
                department <department>
                """);
        String[] command = scanner.nextLine().split(" ");
        switch (command[0].toLowerCase()) {
            case "sku":
                for (Product product : products) {
                    if (product.getSKU().contains(command[1].toUpperCase()))
                        System.out.println(product);
                }
                break;
            case "name":
                for (Product product : products) {
                    if (product.getName().contains(command[1]))
                        System.out.println(product);
                }
                break;
            case "price":
                if (command[1].equals("above")) {
                    for (Product product : products) {
                        if (product.getPrice() > Double.parseDouble(command[2]))
                            System.out.println(product);
                    }
                } else if (command[1].equals("below")) {
                    for (Product product : products) {
                        if (product.getPrice() < Double.parseDouble(command[2]))
                            System.out.println(product);
                    }
                } else System.out.println("Invalid command");
                break;
            case "department":
                for (Product product : products) {
                    if (product.getDepartment().contains(command[1]))
                        System.out.println(product);
                }
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private void addProductToCart() {
        System.out.println("Please enter the SKU of the product you would like to add.");
        String sku = scanner.nextLine();
        for (Product product : products) {
            if (product.getSKU().equalsIgnoreCase(sku)) {
                for (Product pc : cart) {
                    if (pc.getSKU().equalsIgnoreCase(sku)) {
                        pc.addQuantity();
                        return;
                    }
                }
                cart.add(product);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private void removeItemFromCart() {
        System.out.println("What is the SKU of the product you would like to remove?");
        String sku = scanner.nextLine();
        for (Product product : cart) {
            if (product.getSKU().equals(sku)) {
                if (product.getQuantity() > 1) {
                    product.removeQuantity();
                    return;
                }
                cart.remove(product);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private void saveReceipt(Receipt r) {
        Date date = r.getDate();
        String filename =
                date.getYear() + "" + date.getMonth() + "" + date.getDay() + "" + date.getHours() + "" + date.getMinutes();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + filename + ".txt"))) {
            bw.write(r.toString());
        } catch (IOException e) {
            System.out.println("Could not save receipt.");
        }
    }

    public static void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                products.add(new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
            }
        } catch (IOException e) {
            System.out.println("Could not laod products.");
        }
    }
}
