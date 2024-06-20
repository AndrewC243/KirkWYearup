package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// If you're checking here for the June 20 workshop, it is in the workshop folder
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private final FileManager fm;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        fm = new FileManager();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return fm.getVehiclesByPrice(min, max);
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return fm.getVehiclesByMakeModel(make, model);
    }

    public List<Vehicle> getVehiclesByYear(int year) {
        return fm.getVehiclesByYear(year);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return fm.getVehiclesByColor(color);
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return fm.getVehiclesByMileage(min, max);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return fm.getVehiclesByType(type);
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getInventory() {
        return fm.getVehicles(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public void addVehicle(String addNewVehicle) {
//        //vin(int), year(int), make(String), model(String), VehicleType(String), Color(String), odometer(int), price(double)
//        Scanner scanner = new Scanner(System.in);
//        try {
//            System.out.println("Enter VIN: ");
//            int inputVin = scanner.nextInt();
//            System.out.println("Enter Year of Vehicle: ");
//            int inputYear = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Enter Make of the Vehicle: ");
//            String inputMake = scanner.nextLine();
//            System.out.println("Enter Model of the Vehicle: ");
//            String inputModel = scanner.nextLine();
//            System.out.println("Enter the Vehicle Type: Example: Car, Truck, SUV, Van. ");
//            String inputType = scanner.nextLine();
//            System.out.println("Enter the Color of the Vehicle: ");
//            String inputColor = scanner.nextLine();
//            System.out.println("Enter the odometer of the vehicle: ");
//            int inputOdometer = scanner.nextInt();
//            System.out.println("Enter the Price of the vehicle: ");
//            double inputPrice = scanner.nextDouble();
//            Vehicle newVehicle = new Vehicle(inputVin, inputYear, inputMake, inputModel, inputType, inputColor, inputOdometer, inputPrice);
//
//            writeVehicleToFile(newVehicle);
//
//            System.out.println("Vehicle has been successfully added to inventory! ");
//        } catch (Exception ex) {
//            System.out.println("Sorry, there was an error when adding the vehicle. Please try again. ");
//        }
//    }

    public void addVehicle(String addNewVehicle) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter VIN (5 digits): ");
            String inputVin = scanner.next();
            if (inputVin.length() != 5) {
                throw new IllegalArgumentException("VIN must be 5 digits long.");
            }

            System.out.println("Enter Year of Vehicle (4 digits): ");
            int inputYear = scanner.nextInt();
            if (String.valueOf(inputYear).length() != 4) {
                throw new IllegalArgumentException("Year must be 4 digits long.");
            }

            scanner.nextLine();
            System.out.println("Enter Make of the Vehicle: ");
            String inputMake = scanner.nextLine();

            System.out.println("Enter Model of the Vehicle: ");
            String inputModel = scanner.nextLine();

            System.out.println("Enter the Vehicle Type: Example: Car, Truck, SUV, Van. ");
            String inputType = scanner.nextLine();

            System.out.println("Enter the Color of the Vehicle: ");
            String inputColor = scanner.nextLine();

            System.out.println("Enter the odometer of the vehicle (6 digits): ");
            String inputOdometerStr = scanner.nextLine();
            int inputOdometer = Integer.parseInt(inputOdometerStr);
            if (inputOdometerStr.length() != 6) {
                throw new IllegalArgumentException("Odometer reading must be 6 digits long.");
            }

            System.out.println("Enter the Price of the vehicle: ");
            double inputPrice = scanner.nextDouble();

            fm.writeVehicleToFile(new Vehicle(inputVin, inputYear, inputMake, inputModel, inputType, inputColor, inputOdometer, inputPrice));

            System.out.println("Vehicle has been successfully added to inventory! ");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input for odometer. Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Input error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Sorry, there was an error when adding the vehicle. Please try again. ");
        } finally {
            scanner.nextLine();
        }
    }


    public void removeVehicleByVIN(String vinToRemove) {
        fm.removeVehicleByVIN(vinToRemove);
    }
}