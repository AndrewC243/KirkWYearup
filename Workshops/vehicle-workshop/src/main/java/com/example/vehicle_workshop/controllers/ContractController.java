package com.example.vehicle_workshop.controllers;

import com.example.vehicle_workshop.models.LeaseContract;
import com.example.vehicle_workshop.models.SalesContract;
import com.example.vehicle_workshop.services.LeaseContractService;
import com.example.vehicle_workshop.services.SalesContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    private SalesContractService salesContractService;
    @Autowired
    private LeaseContractService leaseContractService;

    @GetMapping("/sales")
    public ResponseEntity<List<SalesContract>> getSalesContracts() {
        return ResponseEntity.ok(salesContractService.getAllSalesContracts());
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SalesContract> getSalesContractById(@PathVariable long id) {
        return ResponseEntity.ok(salesContractService.getSalesContractById(id));
    }

    @PostMapping("/sales")
    public ResponseEntity<SalesContract> createSalesContract(@RequestBody SalesContract salesContract) {
        return ResponseEntity.ok(salesContractService.createSalesContract(salesContract));
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<SalesContract> updateSalesContract(@PathVariable long id, @RequestBody SalesContract salesContract) {
        return ResponseEntity.ok(salesContractService.updateSalesContract(id, salesContract));
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Void> deleteSalesContract(@PathVariable long id) {
        salesContractService.deleteSalesContract(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/leases")
    public ResponseEntity<List<LeaseContract>> getLeaseContracts() {
        return ResponseEntity.ok(leaseContractService.getLeaseContracts());
    }

    @GetMapping("/leases/{id}")
    public ResponseEntity<LeaseContract> getLeaseContractById(@PathVariable long id) {
        return ResponseEntity.ok(leaseContractService.getLeaseContractById(id));
    }

    @PostMapping("/leases")
    public ResponseEntity<LeaseContract> createLeaseContract(@RequestBody LeaseContract leaseContract) {
        return ResponseEntity.ok(leaseContractService.createLeaseContract(leaseContract));
    }

    @PutMapping("/leases/{id}")
    public ResponseEntity<LeaseContract> updateLeaseContract(@PathVariable long id, @RequestBody LeaseContract leaseContract) {
        return ResponseEntity.ok(leaseContractService.updateLeaseContract(id, leaseContract));
    }

    @DeleteMapping("/leases/{id}")
    public ResponseEntity<Void> deleteLeaseContract(@PathVariable long id) {
        leaseContractService.deleteLeaseContract(id);
        return ResponseEntity.noContent().build();
    }
}
