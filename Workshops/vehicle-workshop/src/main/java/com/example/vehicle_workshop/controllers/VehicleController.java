package com.example.vehicle_workshop.controllers;

import com.example.vehicle_workshop.models.Vehicle;
import com.example.vehicle_workshop.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/search/price")
    public ResponseEntity<List<Vehicle>> searchByPrice(@RequestParam Optional<Double> priceMin, @RequestParam Optional<Double> priceMax) {
        return ResponseEntity.ok(vehicleService.getByPrice(priceMin.orElse(0.0), priceMax.orElse(Double.MAX_VALUE)));
    }
    @GetMapping("/search/make")
    public ResponseEntity<List<Vehicle>> searchByMake(@RequestParam String make) {
        return ResponseEntity.ok(vehicleService.getByMake(make));
    }
    @GetMapping("/search/model")
    public ResponseEntity<List<Vehicle>> searchByModel(@RequestParam String model) {
        return ResponseEntity.ok(vehicleService.getByModel(model));
    }
    @GetMapping("/search/year")
    public ResponseEntity<List<Vehicle>> searchByYear(@RequestParam Optional<Integer> yearMin, @RequestParam Optional<Integer> yearMax) {
        return ResponseEntity.ok(vehicleService.getByYear(yearMin.orElse(0), yearMax.orElse(Integer.MAX_VALUE)));
    }
    @GetMapping("/search/color")
    public ResponseEntity<List<Vehicle>> searchByColor(@RequestParam String color) {
        return ResponseEntity.ok(vehicleService.getByColor(color));
    }
    @GetMapping("/search/mileage")
    public ResponseEntity<List<Vehicle>> searchByOdometer(@RequestParam Optional<Integer> odometerMin, @RequestParam Optional<Integer> odometerMax) {
        return ResponseEntity.ok(vehicleService.getByOdometer(odometerMin.orElse(0), odometerMax.orElse(Integer.MAX_VALUE)));
    }
    @GetMapping("/search/type")
    public ResponseEntity<List<Vehicle>> searchByType(@RequestParam String type) {
        return ResponseEntity.ok(vehicleService.findByType(type));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
