package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.FileManager.getVehicles;
import static org.example.FileManager.writeVehicleToFile;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private BasicDataSource dataSource;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername("root");
        dataSource.setPassword("YUm15510n");
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String call = "{CALL dealership.VehiclesByPriceByDealership(?, ?, 1)}";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareCall(call)) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        String call = "{CALL dealership.VehiclesByMakeModelByDealership(?, ?, 1)}";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, make);
            cs.setString(2, model);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        }
        catch (SQLException ignored) {}
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYear(int year) {
        List<Vehicle> vehicles = new ArrayList<>();
        String call = "{CALL dealership.VehiclesByYearByDealership(?, 1)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, year);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {}

        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String call = "{CALL dealership.VehiclesByColorByDealership(?, 1)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, color);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {}

        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String call = "{CALL dealership.VehiclesByMileageByDealership(?, ?, 1)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, min);
            cs.setInt(2, max);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {}

        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String call = "{CALL dealership.VehiclesByTypeByDealership(?, 1)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, type);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {}

        return vehicles;
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getInventory() {
        String call = "{CALL dealership.CarsFromDealership(?, 1)}";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); CallableStatement cs = connection.prepareCall(call)) {
            cs.setString(1, name);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {
        }
        return vehicles;
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

            String query = "INSERT INTO vehicles (vin, year, make, model, type, color, odometer, price) " +
                    "VALUES (?,?,?,?,?,?,?,?); INSERT INTO inventory(dealership_id, vin) VALUES (1, ?)";

            try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, inputVin);
                ps.setInt(2, inputYear);
                ps.setString(3, inputMake);
                ps.setString(4, inputModel);
                ps.setString(5, inputType);
                ps.setString(6, inputColor);
                ps.setInt(7, inputOdometer);
                ps.setDouble(8, inputPrice);
                ps.setString(9, inputVin);
                ps.executeUpdate();
            } catch (SQLException ignored) {}

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
        String query = "REMOVE FROM vehicles WHERE vin = ?; REMOVE FROM inventory WHERE vin = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, vinToRemove);
            ps.setString(2, vinToRemove);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
    }

    private Vehicle getVehicleFromResultSet(ResultSet rs) throws SQLException {
        String vin = rs.getString("vin");
        int year = rs.getInt("year");
        String make = rs.getString("make");
        String model = rs.getString("model");
        String type = rs.getString("type");
        String color = rs.getString("color");
        int odometer = rs.getInt("odometer");
        double price = rs.getDouble("price");
        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }
}