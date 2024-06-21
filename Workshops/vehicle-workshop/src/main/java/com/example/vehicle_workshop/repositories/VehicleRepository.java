package com.example.vehicle_workshop.repositories;

import com.example.vehicle_workshop.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByPriceBetween(double min, double max);
    List<Vehicle> findByMake(String make);
    List<Vehicle> findByModel(String model);
    List<Vehicle> findByYearBetween(int min, int max);
    List<Vehicle> findByColor(String color);
    List<Vehicle> findByOdometerBetween(int min, int max);
    List<Vehicle> findByVehicleType(String type);
}
