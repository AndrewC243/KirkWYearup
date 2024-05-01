package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FunTimes {
    public static void rollForDexterity() {
        if (Math.random() <= 0.05) {
            System.out.println("Dexterity check failed :(");
            System.exit(1);
        }
    }

    public static boolean perceptionCheck() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {}
        return Math.random() <= 0.75;
    }

    public static void mathCaptcha() {
        Scanner sc = new Scanner(System.in);
        System.out.println("To prove that you're a human, please answer the following captcha:");
        int x = (int)(Math.random() * 10 + 1);
        int y = (int)(Math.random() * 10 + 1);
        int z = (int)(Math.random() * 10 + 1);
        System.out.printf("What is %d * %d / %d?\t", x, y, z);
        try {
            if (sc.nextDouble() != x * y / (double)z) {
                System.out.println("Incorrect. Generating new captcha.");
                mathCaptcha();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Generating new captcha.");
            mathCaptcha();
        }
    }
}
