package com.example.vehicle_workshop.services;

import com.example.vehicle_workshop.exceptions.ResourceNotFoundException;
import com.example.vehicle_workshop.models.LeaseContract;
import com.example.vehicle_workshop.repositories.LeaseContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseContractService {
    @Autowired
    private LeaseContractRepository leaseContractRepository;

    public List<LeaseContract> getLeaseContracts() {
        return leaseContractRepository.findAll();
    }

    public LeaseContract getLeaseContractById(long id) {
        return leaseContractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find lease contract with id " + id));
    }

    public LeaseContract createLeaseContract(LeaseContract leaseContract) {
        return leaseContractRepository.save(leaseContract);
    }

    public LeaseContract updateLeaseContract(long id, LeaseContract leaseContract) {
        LeaseContract old = getLeaseContractById(id);
        old.setDate(leaseContract.getDate());
        old.setCustomerEmail(leaseContract.getCustomerEmail());
        old.setCustomerName(leaseContract.getCustomerName());
        old.setVehicleSold(leaseContract.getVehicleSold());
        return leaseContractRepository.save(old);
    }

    public void deleteLeaseContract(long id) {
        leaseContractRepository.deleteById(id);
    }
}
