package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        while (true) {
            System.out.println("""
                    
                    What tool would you look to use today?
                        (M)ortgage Calculator
                        (F)uture CD Value
                        (A)nnuity Present Value
                        (Q)uit
                    """);
            switch (sc.next().toUpperCase()) {
                case "M":
                    System.out.println("What is the principal?");
                    double principal = sc.nextDouble();
                    System.out.println("What is the rate?");
                    double rate = sc.nextDouble();
                    System.out.println("How many years are on it?");
                    int years = sc.nextInt();
                    MortgageCalculator calculator = new MortgageCalculator(principal, rate, years);
                    System.out.println("Your monthly payment will be $" + df.format(calculator.monthlyPayment()));
                    System.out.println(
                            "The amount of interest you'll owe is $" + df.format(calculator.totalInterest())
                            + "\n"
                    );
                    break;
                case "F":
                    System.out.println("What is the deposit amount?");
                    int cdDeposit = sc.nextInt();
                    System.out.println("What is the interest rate?");
                    double cdRate = sc.nextDouble();
                    System.out.println("How many years are on it?");
                    int cdYears = sc.nextInt();
                    FutureValue f = new FutureValue(cdDeposit, cdRate, cdYears);
                    System.out.printf("Your CD will be worth $%s in %d years\n", df.format(f.futureValue()), cdYears);
                    System.out.printf("You will have earned $%s in interest\n", df.format(f.interestEarend()));
                    break;
                case "A":
                    System.out.println("What is the monthly payout?");
                    double monthlyPayout = sc.nextDouble();
                    System.out.println("What is the expected interest rate?");
                    double annuityRate = sc.nextDouble();
                    System.out.println("How many years will it pay out?");
                    int annuityYears = sc.nextInt();
                    AnnuityValue a = new AnnuityValue(monthlyPayout, annuityRate, annuityYears);
                    System.out.printf(
                            "The present value of your annuity is/would need to be $%s\n",
                            df.format(a.presentValue()));
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Please make a valid choice");
            }
        }
    }
}