package com.example.vehicle_workshop.services;

import com.example.vehicle_workshop.exceptions.ResourceNotFoundException;
import com.example.vehicle_workshop.models.Dealership;
import com.example.vehicle_workshop.repositories.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealershipService {
    @Autowired
    private DealershipRepository dealershipRepository;

    public Dealership getDealershipById(long id) {
        return dealershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealership not found"));
    }

    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }

    public Dealership addDealership(Dealership dealership) {
        return dealershipRepository.save(dealership);
    }

    public Dealership updateDealership(Dealership dealership) {
        Dealership d = dealershipRepository.findById(dealership.getDealershipId())
                .orElseThrow(() -> new ResourceNotFoundException("Dealership not found"));
        d.setName(dealership.getName());
        d.setAddress(dealership.getAddress());
        d.setPhone(dealership.getPhone());
        return dealershipRepository.save(d);
    }

    public void deleteDealership(long id) {
        dealershipRepository.deleteById(id);
    }
}
