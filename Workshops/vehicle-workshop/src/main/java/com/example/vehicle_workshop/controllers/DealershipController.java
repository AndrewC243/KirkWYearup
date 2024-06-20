package com.example.vehicle_workshop.controllers;

import com.example.vehicle_workshop.models.Dealership;
import com.example.vehicle_workshop.services.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipController {
    @Autowired
    private DealershipService dealershipService;

    @GetMapping
    public ResponseEntity<List<Dealership>> getAllDealerships() {
        return ResponseEntity.ok(dealershipService.getAllDealerships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealership> getDealershipById(@PathVariable int id) {
        return ResponseEntity.ok(dealershipService.getDealershipById(id));
    }

    @PostMapping
    public ResponseEntity<Dealership> createDealership(@RequestBody Dealership dealership) {
        return new ResponseEntity<>(dealershipService.addDealership(dealership), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Dealership> updateDealership(@RequestBody Dealership dealership) {
        return ResponseEntity.ok(dealershipService.updateDealership(dealership));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable long id) {
        dealershipService.deleteDealership(id);
        return ResponseEntity.noContent().build();
    }
}
