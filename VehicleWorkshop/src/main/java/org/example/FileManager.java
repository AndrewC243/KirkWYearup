package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private BasicDataSource dataSource;

    public FileManager() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername("root");
        dataSource.setPassword("YUm15510n");
    }

    public List<Dealership> getDealership(){
        String query = "SELECT * FROM dealerships";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<Dealership> dealerships = new ArrayList<>();
                while (rs.next()) {
                    dealerships.add(new Dealership(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone")
                    ));
                }
                return dealerships;1

            }
        } catch (SQLException ignored) {}
        throw new RuntimeException("failed :(((");
    }

    public static void displayDealership(List<Dealership> dealerships){

        for (Dealership dealership : dealerships){
            System.out.printf("%s, %s, %s \n", dealership.getName(), dealership.getAddress(), dealership.getPhone());
        }

    }

    public List<Vehicle> getVehicles(Dealership dealership){
        String call = "{CALL dealership.CarsFromDealership(?)}";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); CallableStatement cs = connection.prepareCall(call)) {
            cs.setString(1, dealership.getName());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(getVehicleFromResultSet(rs));
                }
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return vehicles;
    }

    public static void displayVehicles(List<Vehicle> vehicles){

        for (Vehicle vehicle : vehicles){
            System.out.printf("%s | %d | %s | %s | %s | %s | %d | $%.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }
    }

    public void writeVehicleToFile(Vehicle vehicle){

        String query = "INSERT INTO vehicles (vin, year, make, model, type, color, odometer, price) " +
                "VALUES (?,?,?,?,?,?,?,?); INSERT INTO inventory(dealership_id, vin) VALUES (1, ?)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());
            ps.setString(9, vehicle.getVin());
            ps.executeUpdate();
        } catch (SQLException ignored) {}

    }

    public void saveDealership(Dealership dealership){
        String query = "UPDATE dealerships SET address=?,phone=? WHERE name=?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, dealership.getAddress());
            ps.setString(2, dealership.getPhone());
            ps.setString(3, dealership.getName());
            ps.executeUpdate();
        } catch (SQLException ignored) {}
    }

    public void removeVehicleByVIN(String vinToRemove) {
        String query = "REMOVE FROM vehicles WHERE vin = ?; REMOVE FROM inventory WHERE vin = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, vinToRemove);
            ps.setString(2, vinToRemove);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
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
