package com.example.vehicle_workshop.services;

import com.example.vehicle_workshop.exceptions.ResourceNotFoundException;
import com.example.vehicle_workshop.models.Contract;
import com.example.vehicle_workshop.models.SalesContract;
import com.example.vehicle_workshop.repositories.SalesContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class SalesContractService {
    @Autowired
    private SalesContractRepository salesContractRepository;

    public List<SalesContract> getAllSalesContracts() {
        return salesContractRepository.findAll();
    }

    public SalesContract getSalesContractById(long id) {
        return salesContractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find sales contract"));
    }

    public SalesContract createSalesContract(SalesContract salesContract) {
        return salesContractRepository.save(salesContract);
    }

    public SalesContract updateSalesContract(long id, SalesContract salesContract) {
        SalesContract salesContractToUpdate = getSalesContractById(id);
        salesContractToUpdate.setFinanced(salesContract.isFinanced());
        salesContractToUpdate.setDate(salesContract.getDate());
        salesContractToUpdate.setCustomerName(salesContract.getCustomerName());
        salesContractToUpdate.setCustoemrEmail(salesContract.getCustoemrEmail());
        return salesContractRepository.save(salesContractToUpdate);
    }

    public void deleteSalesContract(long id) {
        salesContractRepository.deleteById(id);
    }
}
