package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the first number ");
        Scanner sc = new Scanner(System.in);
        float firstNum = sc.nextFloat();
        System.out.println("Enter the second number ");
        float secondNum = sc.nextFloat();

//        System.out.println("Possible calculations:");
//        System.out.println("   (A)dd");
//        System.out.println("   (S)ubtract");
//        System.out.println("   (M)ultiply");
//        System.out.println("   (D)ivide");
//        System.out.println("Please select an operation: ");
        System.out.println("""
                Possible calculations:
                    (A)dd
                    (S)subtract
                    (M)ultiply
                    (D)ivide
                Please select an operation: """);

        String operation = sc.next();
        if (operation.equalsIgnoreCase("A")) {
            System.out.printf("%f + %f = %f\n", firstNum, secondNum, add(firstNum, secondNum));
        }
        else if (operation.equalsIgnoreCase("S")) {
            System.out.printf("%f - %f = %f\n", firstNum, secondNum, subtract(firstNum, secondNum));
        }
        else if (operation.equalsIgnoreCase("M")) {
            System.out.printf("%f * %f = %f\n", firstNum, secondNum, multiply(firstNum, secondNum));
        }
        else if (operation.equalsIgnoreCase("D")) {

            if (secondNum == 0) {
                System.out.println("Please make a valid input");
                return;
            }
            System.out.printf("%f / %f = %f\n", firstNum, secondNum, divide(firstNum, secondNum));
        }
        else {
            System.out.println("Please make a valid input");
        }
    }
    public static float add (float firstNum, float secondNum) {
        return firstNum + secondNum;
    }
    public static float subtract (float firstNum, float secondNum) {
        return firstNum - secondNum;
    }
    public static float multiply (float firstNum, float secondNum) {
        return firstNum * secondNum;
    }
    public static float divide (float firstNum, float secondNum) {
        return firstNum / secondNum;
    }
}