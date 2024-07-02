package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NorthwindProcedures np = new NorthwindProcedures("jdbc:mysql://localhost:3306/mysql", args[0], args[1]);
        System.out.println("""
                Pick a stored procedure:
                1) Customer Order History
                2) Sales by Year
                3) Sales by Category
                """);
        int input = sc.nextInt();
        sc.nextLine();
        switch (input) {
            case 1 -> {
                System.out.println("Please enter the customer ID");
                System.out.println(Arrays.toString(np.customerOrderHistory(sc.nextLine())));;
            }
            case 2 -> {
                System.out.println("Please enter the start date in yyyy-mm-dd format");
                String startDate = sc.nextLine();
                System.out.println("Please enter the end date in yyyy-mm-dd format");
                String endDate = sc.nextLine();
                System.out.println(Arrays.toString(np.salesByYear(startDate, endDate)));;
            }
            case 3 -> {
                System.out.println("Please enter the category name");
                String category = sc.nextLine();
                System.out.println("Please enter the year you wish to search through");
                int year = sc.nextInt();
                System.out.println(Arrays.toString(np.salesByCategory(category, year)));;
            }
        }
    }
}