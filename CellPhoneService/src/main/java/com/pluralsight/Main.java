package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the serial number?");
        long serial = scanner.nextLong();
        scanner.nextLine();
        System.out.println("What model is the phone?");
        String model = scanner.nextLine();
        System.out.println("Who is the carrier?");
        String carrier = scanner.nextLine();
        System.out.println("Who is the owner of the phone");
        String owner = scanner.nextLine();
        CellPhone phone = new CellPhone();
        phone.setSerialNumber(serial);
        phone.setModel(model);
        phone.setCarrier(carrier);
        phone.setOwner(owner);

        dispaly(phone);
    }

    public static void dispaly(CellPhone phone) {
        System.out.println(phone.getSerialNumber());
        System.out.println(phone.getModel());
        System.out.println(phone.getCarrier());
        System.out.println(phone.getOwner() + "\n");
    }
}