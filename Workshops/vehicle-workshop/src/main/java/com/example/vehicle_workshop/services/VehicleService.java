package com.example.vehicle_workshop.services;

import com.example.vehicle_workshop.exceptions.ResourceNotFoundException;
import com.example.vehicle_workshop.models.Vehicle;
import com.example.vehicle_workshop.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }

    public List<Vehicle> getByPrice(double min, double max) {
        return vehicleRepository.findByPriceBetween(min,max);
    }

    public List<Vehicle> getByMake(String make) {
        return vehicleRepository.findByMake(make);
    }

    public List<Vehicle> getByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> getByYear(int min, int max) {
        return vehicleRepository.findByYearBetween(min,max);
    }

    public List<Vehicle> getByColor(String color) {
        return vehicleRepository.findByColor(color);
    }

    public List<Vehicle> getByOdometer(int min, int max) {
        return vehicleRepository.findByOdometerBetween(min,max);
    }

    public List<Vehicle> findByType(String type) {
        return vehicleRepository.findByVehicleType(type);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle oldVehicle = vehicleRepository.findById(vehicle.getVin())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        oldVehicle.setColor(vehicle.getColor());
        oldVehicle.setModel(vehicle.getModel());
        oldVehicle.setMake(vehicle.getMake());
        oldVehicle.setYear(vehicle.getYear());
        oldVehicle.setPrice(vehicle.getPrice());
        oldVehicle.setOdometer(vehicle.getOdometer());
        oldVehicle.setVehicleType(vehicle.getVehicleType());
        return vehicleRepository.save(oldVehicle);
    }

    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }
}
