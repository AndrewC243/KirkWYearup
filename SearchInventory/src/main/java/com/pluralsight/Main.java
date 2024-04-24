package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getProducts());
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                products.add(new Product(
                        Integer.parseInt(parts[0].substring(2)),
                        parts[1],
                        Float.parseFloat(parts[2])
                ));
            }
            return products;
        } catch (IOException e) {
            return null;
        }
    }
}