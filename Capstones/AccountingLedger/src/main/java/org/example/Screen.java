package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Screen {
    private static Scanner input = new Scanner(System.in);
    private TransactionHandler th = new TransactionHandler();

    public void homeScreen() {
        while (true) {
            System.out.println("""
                    Welcome to the accounting ledger! Please select an option:
                    D) Add deposit
                    P) Make payment
                    L) Show ledger
                    X) Exit
                    """);

            switch (input.nextLine().toUpperCase()) {
                case "D" -> addDeposit();
                case "P" -> addPayment();
                case "L" -> showLedger();
                case "X" -> {
                    return;
                }
            }
        }
    }

    public void addDeposit() {
        FunTimes.rollForDexterity();
        System.out.println("Please enter a description for the deposit:");
        String description = input.nextLine();
        System.out.println("Please enter who it came from:");
        String vendor = input.nextLine();
        System.out.println("Please enter the amount to deposit:");
        double amount = input.nextDouble();
        input.nextLine();
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Aborting.");
            return;
        }
        th.writeTransaction(new Transaction(
                new Date(),
                description,
                vendor,
                amount
        ));
    }

    public void addPayment() {
        FunTimes.rollForDexterity();
        System.out.println("Please enter a description for the payment:");
        String description = input.nextLine();
        System.out.println("Please enter who it is to:");
        String vendor = input.nextLine();
        System.out.println("Please enter the amount paid:");
        double amount = input.nextDouble();
        input.nextLine();
        if (amount <= 0) {
            System.out.println("Invalid payment amount. Aborting.");
            FunTimes.mathCaptcha();
            return;
        }
        th.writeTransaction(new Transaction(
                new Date(),
                description,
                vendor,
                amount * -1
        ));
    }

    public void showLedger() {
        while (true) {
            FunTimes.rollForDexterity();
            System.out.println("""
                    How would you like to view the ledger?
                    A) All
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    """);

            switch (input.nextLine().toUpperCase()) {
                case "A" -> displayList(th.getTransactionList());
                case "D" -> displayList(th.filter(false));
                case "P" -> displayList(th.filter(true));
                case "R" -> reports();
                case "H" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid option.");
                    FunTimes.mathCaptcha();
                }
            }
        }
    }

    public void reports() {
        while (true) {
            FunTimes.rollForDexterity();
            System.out.println("""
                    Use one of the following filters or search by vendor:
                    1) Month to date
                    2) Previous month
                    3) Year to date
                    4) Previous year
                    5) Search by vendor
                    6) Custom search
                    0) Back
                    """);

            try {
                switch (input.nextInt()) {
                    case 1 -> displayList(th.monthToDate());
                    case 2 -> displayList(th.previousMonth());
                    case 3 -> displayList(th.yearToDate());
                    case 4 -> displayList(th.previousYear());
                    case 5 -> {
                        input.nextLine();
                        searchByVendor();
                    }
                    case 6 -> {
                        input.nextLine();
                        customSearch();
                    }
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        FunTimes.mathCaptcha();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option.");
                FunTimes.mathCaptcha();
            }
        }
    }

    public void customSearch() {
        FunTimes.rollForDexterity();
        TransactionFilter tf = new TransactionFilter();
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Leave any field blank to exclude it as a searching criteria.");
        try {
            System.out.println("Start date (yyyy-mm-dd):");
            String in = input.nextLine();
            if (!in.isEmpty()) tf.setStartDate(sdf.parse(in));

            System.out.println("End date (yyyy-mm-dd):");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setEndDate(sdf.parse(in));

            System.out.println("Description:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setDescription(in);

            System.out.println("Vendor:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setVendor(in);

            System.out.println("Minimum amount:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setAmountMin(Double.parseDouble(in));

            System.out.println("Maximum amount:");
            in = input.nextLine();
            if (!in.isEmpty()) tf.setAmountMax(Double.parseDouble(in));
        } catch (ParseException | InputMismatchException e) {
            System.out.println("Invalid input. Aborting.");
            FunTimes.mathCaptcha();
            return;
        }
        displayList(th.filter(tf));
    }

    public void searchByVendor() {
        FunTimes.rollForDexterity();
        System.out.println("Enter a vendor name: ");
        String vendor = input.nextLine();
        displayList(th.filter(vendor));
    }

    public void displayList(List<Transaction> transactions) {
        FunTimes.rollForDexterity();
        for (Transaction transaction : transactions)
            if (FunTimes.perceptionCheck()) {
                System.out.println(transaction);
            } else System.out.println("Perception check failed.");
    }
}