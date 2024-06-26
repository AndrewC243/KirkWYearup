package org.example;

import java.util.Scanner;

/*
- Declare pickup date, amount of days that the car will be rented for, age, and the toll tag, GPS, and roadside assistance add-ons
- Create scanner
- Ask user for each of the above variables
- Assign variables to inputs using scanner
- Declare calculation variables
- Assign them their calculated values
- Print all calculated values & pickup date

  Extra:
- Validate user input
 */

public class Main {
    public static void main(String[] args) {
        String pickupDate;
        int daysRented;
        boolean tollTag;
        boolean GPS;
        boolean roadsideAssistance;
        int age;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the pickup date: ");
        pickupDate = input.nextLine();
        System.out.println("Please enter the number of days you will rent for: ");
        daysRented = input.nextInt();
        System.out.println("Would you like a toll tag? ($3.95/day)\n\t(Y)es\n\t(N)o");
        tollTag = input.next().equalsIgnoreCase("y");
        System.out.println("Would you like a GPS? ($2.95/day)\n\t(Y)es\n\t(N)o");
        GPS = input.next().equalsIgnoreCase("y");
        System.out.println("Would you like roadside assistance? ($3.95/day)\n\t(Y)es\n\t(N)o");
        roadsideAssistance = input.next().equalsIgnoreCase("y");
        System.out.println("What is your age?");
        age = input.nextInt();

        double basicRentalCost = 29.99 * daysRented;

        double optionsCost = 0.0;
        if (tollTag) optionsCost += 3.95 * daysRented;
        if (GPS) optionsCost += 2.95 * daysRented;
        if (roadsideAssistance) optionsCost += 3.95 * daysRented;

        double surcharge = age < 25
                ? basicRentalCost * 1.3
                : 0.0;
        double totalCost = basicRentalCost + optionsCost + surcharge;

        System.out.printf("""
                
                Pickup date: %s
                
                Basic rental cost: %.2f
                Cost of additional options: %.2f
                Age surcharge: %.2f
                
                Total cost: %.2f
                """, pickupDate, basicRentalCost, optionsCost, surcharge, totalCost);
    }
}

