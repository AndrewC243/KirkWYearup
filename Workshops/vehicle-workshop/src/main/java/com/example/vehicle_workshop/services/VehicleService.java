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
