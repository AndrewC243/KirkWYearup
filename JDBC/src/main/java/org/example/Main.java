package org.example;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CategoryRepository cr = new CategoryRepository("jdbc:mysql://localhost:3306/northwind", args[0], args[1]);
        System.out.println(cr.getCategoryById(10).orElseThrow());
    }
}