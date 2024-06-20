package com.example.vehicle_workshop.controllers;

import com.example.vehicle_workshop.exceptions.ResourceNotFoundException;
import com.example.vehicle_workshop.models.Inventory;
import com.example.vehicle_workshop.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping
    public ResponseEntity<List<Inventory>> getInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Inventory> getInventoryByVin(@PathVariable String vin) {
        return ResponseEntity.ok(inventoryRepository.findById(vin).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory, @PathVariable String vin) {
        Inventory toUpdate = inventoryRepository.findById(inventory.getVin())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find VIN"));
        toUpdate.setDealershipId(inventory.getDealershipId());
        return ResponseEntity.ok(inventoryRepository.save(toUpdate));
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteInventory(@PathVariable String vin) {
        inventoryRepository.deleteById(vin);
        return ResponseEntity.noContent().build();
    }
}
