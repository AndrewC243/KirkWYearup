package com.pluralsight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Which bedtime story would you like to read?");
        while (true) {
            try (Scanner in = new Scanner(System.in)) {
                Scanner fileReader = new Scanner(new File(
                        "src/main/resources/" + in.nextLine().toLowerCase().replace(' ', '_') + ".txt"));
                int lineNum = 1;
                while (fileReader.hasNextLine())
                    System.out.println(lineNum++ + "  " + fileReader.nextLine());
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file");
            }
        }
    }
}